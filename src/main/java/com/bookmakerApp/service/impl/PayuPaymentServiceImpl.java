package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.PaymentModel;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.PaymentRepository;
import com.bookmakerApp.service.interfaces.PaymentService;
import com.bookmakerApp.service.interfaces.UserService;
import com.bookmakerApp.webclient.payment.PaymentPayuClient;
import com.bookmakerApp.webclient.payment.dto.PaymentResponseDto;
import com.bookmakerApp.webclient.payment.dto.PaymentStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static com.bookmakerApp.config.constants.payu.PaymentPayuConstants.STATUS_NEW;

@Service
@RequiredArgsConstructor
public class PayuPaymentServiceImpl implements PaymentService {

    private final PaymentPayuClient paymentClient;
    private final PaymentRepository paymentRepository;
    private final UserService userService;


    @Override
    public String getAvailablePaymentMethods() {
        return paymentClient.getAvailablePaymentMethods();
    }

    @Override
    @Transactional
    public PaymentResponseDto makePayment(BigDecimal totalAmount, String currencyCode) {
        PaymentResponseDto paymentResponseDto = paymentClient.makePayment(totalAmount, currencyCode);

        PaymentModel payment = populatePaymentAttributes(paymentResponseDto, totalAmount, currencyCode);
        paymentRepository.save(payment);

        return paymentResponseDto;
    }

    @Override
    public PaymentStatusDto getPaymentStatus(String orderId) {
        return paymentClient.getPaymentStatus(orderId);
    }

    @Override
    @Transactional
    public List<PaymentModel> getPaymentsForUser() {
        UserModel user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return paymentRepository.getPaymentModelsByUser_IdUser(user.getIdUser());
    }

    private PaymentModel populatePaymentAttributes(PaymentResponseDto paymentResponseDto,
                                                   BigDecimal totalAmount,
                                                   String currencyCode) {
        PaymentModel payment = new PaymentModel();
        UserModel user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        payment.setOrderId(paymentResponseDto.getOrderId());
        BigDecimal divisor = new BigDecimal(100);
        BigDecimal paymentAmount = totalAmount.divide(divisor);
        payment.setTotalAmount(paymentAmount);
        payment.setStatus(STATUS_NEW);
        payment.setCurrencyCode(currencyCode);
        payment.setRedirectUri(paymentResponseDto.getRedirectUri());
        payment.setUser(user);
        return payment;
    }
}
