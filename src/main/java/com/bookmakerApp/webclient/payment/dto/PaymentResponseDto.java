package com.bookmakerApp.webclient.payment.dto;

import lombok.Data;

@Data
public class PaymentResponseDto {
    private PaymentStatusResponseDto status;
    private String redirectUri;
    private String orderId;
}
