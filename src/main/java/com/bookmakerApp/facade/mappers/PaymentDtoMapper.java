package com.bookmakerApp.facade.mappers;

import com.bookmakerApp.facade.dtos.MakePaymentResponseDto;
import com.bookmakerApp.facade.dtos.PaymentDto;
import com.bookmakerApp.model.PaymentModel;
import com.bookmakerApp.webclient.payment.dto.PaymentResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

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

    public static List<PaymentDto> mapToPaymentDtos(Page<PaymentModel> payments) {
        int numberOfPages = payments.getTotalPages();
        return payments.stream()
                .map(payment -> mapToPaymentDto(payment, numberOfPages))
                .toList();
    }

    private static PaymentDto mapToPaymentDto(PaymentModel payment, int numberOfPages) {
        return PaymentDto.builder()
                .statusCode(payment.getStatus())
                .redirectUri(payment.getRedirectUri())
                .orderId(payment.getOrderId())
                .currencyCode(payment.getCurrencyCode())
                .totalAmount(payment.getTotalAmount())
                .numberOfPages(numberOfPages)
                .build();
    }

}
