package com.bookmakerApp.service.interfaces;

import com.bookmakerApp.model.PaymentModel;
import com.bookmakerApp.webclient.payment.dto.PaymentResponseDto;
import com.bookmakerApp.webclient.payment.dto.PaymentStatusDto;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {

    String getAvailablePaymentMethods();

    PaymentResponseDto makePayment(BigDecimal totalAmount, String currencyCode);

    PaymentStatusDto getPaymentStatus(String orderId);

    List<PaymentModel> getPaymentsForUser();
}
