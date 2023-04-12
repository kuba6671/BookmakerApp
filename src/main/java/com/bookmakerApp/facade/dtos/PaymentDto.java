package com.bookmakerApp.facade.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentDto {
    private String statusCode;
    private String redirectUri;
    private String orderId;
}
