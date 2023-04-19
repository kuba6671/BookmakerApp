package com.bookmakerApp.facade.dtos;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MakePaymentDto {
    private BigDecimal totalAmount;
    private String currencyCode;

}
