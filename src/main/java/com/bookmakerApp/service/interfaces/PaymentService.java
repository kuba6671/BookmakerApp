package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.webclient.payment.dto.PaymentResponseDto;
import com.bookmakerApp.webclient.payment.dto.PaymentStatusDto;

import java.math.BigDecimal;

public interface PaymentService {

    String getAvailablePaymentMethods();

    PaymentResponseDto makePayment(BigDecimal totalAmount, String currencyCode);

    PaymentStatusDto getPaymentStatus(String orderId);
}
