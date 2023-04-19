package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.MakePaymentResponseDto;
import com.bookmakerApp.facade.dtos.PaymentDto;
import com.bookmakerApp.model.PaymentModel;
import com.bookmakerApp.webclient.payment.dto.PaymentResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentDtoMapper {

    private PaymentDtoMapper() {
    }

    public static MakePaymentResponseDto mapToPaymentResponseDto(PaymentResponseDto paymentResponseDto) {
        return MakePaymentResponseDto.builder()
                .statusCode(paymentResponseDto.getStatus().getStatusCode())
                .redirectUri(paymentResponseDto.getRedirectUri())
                .orderId(paymentResponseDto.getOrderId())
                .build();
    }

    public static List<PaymentDto> mapToPaymentDtos(List<PaymentModel> payments) {
        return payments.stream()
                .map(PaymentDtoMapper::mapToPaymentDto)
                .collect(Collectors.toList());
    }

    private static PaymentDto mapToPaymentDto(PaymentModel payment) {
        return PaymentDto.builder()
                .statusCode(payment.getStatus())
                .redirectUri(payment.getRedirectUri())
                .orderId(payment.getOrderId())
                .currencyCode(payment.getCurrencyCode())
                .totalAmount(payment.getTotalAmount())
                .build();
    }

}
