package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.PaymentModel;
import com.bookmakerApp.webclient.payment.dto.PaymentResponseDto;
import com.bookmakerApp.webclient.payment.dto.PaymentStatusDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {

    String getAvailablePaymentMethods();

    PaymentResponseDto makePayment(BigDecimal totalAmount, String currencyCode);

    PaymentStatusDto getPaymentStatus(String orderId);

    Page<PaymentModel> getPaymentsForUser(int pageNumber);
}
