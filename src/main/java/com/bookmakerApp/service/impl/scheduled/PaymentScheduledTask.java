package com.bookmakerApp.service.impl.scheduled;

import com.bookmakerApp.model.AccountModel;
import com.bookmakerApp.model.PaymentModel;
import com.bookmakerApp.repository.AccountRepository;
import com.bookmakerApp.repository.PaymentRepository;
import com.bookmakerApp.service.interfaces.payment.PaymentService;
import com.bookmakerApp.webclient.payment.dto.PaymentStatusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static com.bookmakerApp.config.constants.payu.PaymentPayuConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentScheduledTask {

    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;
    private final AccountRepository accountRepository;

    @Transactional
    @Scheduled(cron = "0 0/5 * * * ?")
    public void checkPaymentStatus() {
        List<PaymentModel> payments = paymentRepository
                .getPaymentModelsByIsCheckedAndStatusIsNotIn(Boolean.FALSE, List.of(STATUS_COMPLETED, STATUS_CANCELED));

        log.info("received [{}] unchecked payments", payments.size());
        payments.forEach(this::updatePaymentAttributes);
    }

    private void updatePaymentAttributes(PaymentModel payment) {
        PaymentStatusDto paymentStatus = paymentService.getPaymentStatus(payment.getOrderId());
        if (STATUS_NEW.equalsIgnoreCase(paymentStatus.getPayments().get(0).getStatus()))
            return;
        else if (STATUS_COMPLETED.equalsIgnoreCase(paymentStatus.getPayments().get(0).getStatus())) {
            AccountModel account = accountRepository.getAccountModelByUser_IdUser(payment.getUser().getIdUser());
            BigDecimal newBankBalance = account.getBankBalance().add(payment.getTotalAmount());
            account.setBankBalance(newBankBalance);
        }
        payment.setChecked(Boolean.TRUE);
        payment.setStatus(paymentStatus.getPayments().get(0).getStatus());
    }
}
