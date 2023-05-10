package com.bookmakerApp.facade.dtos.payment;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MakePaymentDto {
    private BigDecimal totalAmount;
    private String currencyCode;

}
