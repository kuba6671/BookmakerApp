package com.bookmakerApp.facade.dtos;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PaymentDto {
    private String statusCode;
    private String redirectUri;
    private String orderId;
    private String currencyCode;
    private BigDecimal totalAmount;
}
