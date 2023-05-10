package com.bookmakerApp.facade.dtos.payment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MakePaymentResponseDto {
    private String statusCode;
    private String redirectUri;
    private String orderId;
}
