package com.bookmakerApp.webclient.payment.dto;

import lombok.Getter;

@Getter
public class PaymentResponseDto {
    private PaymentStatusResponseDto status;
    private String redirectUri;
    private String orderId;
}
