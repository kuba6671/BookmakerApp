package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.payment.MakePaymentDto;
import com.bookmakerApp.facade.dtos.payment.MakePaymentResponseDto;
import com.bookmakerApp.facade.dtos.payment.PaymentDto;
import com.bookmakerApp.facade.mappers.PaymentDtoMapper;
import com.bookmakerApp.service.interfaces.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PaymentFacade {

    private final PaymentService paymentService;
    private static final String PLN_CURRENCY = "PLN";

    public MakePaymentResponseDto makePayment(MakePaymentDto payment) {
        if (ObjectUtils.isEmpty(payment.getTotalAmount()) && payment.getTotalAmount().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("Total amount mus be greater than 0");
        } else if (!PLN_CURRENCY.equalsIgnoreCase(payment.getCurrencyCode())) {
            throw new IllegalStateException("Received wrong currency code");
        }
        return PaymentDtoMapper.mapToPaymentResponseDto
                (paymentService.makePayment(payment.getTotalAmount(), payment.getCurrencyCode()));
    }

    public List<PaymentDto> getPaymentsForUser(int pageNumber) {
        return PaymentDtoMapper.mapToPaymentDtos(
                paymentService.getPaymentsForUser(pageNumber));
    }
}
