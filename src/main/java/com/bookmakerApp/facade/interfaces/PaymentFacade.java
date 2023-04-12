package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.PaymentDto;

import java.math.BigDecimal;

public interface PaymentFacade {

    PaymentDto makePayment(BigDecimal totalAmount, String currencyCode);
}
