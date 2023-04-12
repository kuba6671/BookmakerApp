package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.PaymentDto;
import com.bookmakerApp.webclient.payment.dto.PaymentResponseDto;

public class PaymentDtoMapper {

    private PaymentDtoMapper() {
    }

    public static PaymentDto mapToPaymentDto(PaymentResponseDto paymentResponseDto) {
        return PaymentDto.builder()
                .statusCode(paymentResponseDto.getStatus().getStatusCode())
                .redirectUri(paymentResponseDto.getRedirectUri())
                .orderId(paymentResponseDto.getOrderId())
                .build();
    }

}
