package com.bookmakerApp.webclient.payment.dto;

import lombok.Getter;

@Getter
public class PaymentOrderDto {

    private String orderId;
    private String currencyCode;
    private String totalAmount;
    private String status;
}
