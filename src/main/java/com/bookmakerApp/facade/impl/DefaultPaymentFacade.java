package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.MakePaymentDto;
import com.bookmakerApp.facade.dtos.MakePaymentResponseDto;
import com.bookmakerApp.facade.dtos.PaymentDto;
import com.bookmakerApp.facade.interfaces.PaymentFacade;
import com.bookmakerApp.facade.mappers.PaymentDtoMapper;
import com.bookmakerApp.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DefaultPaymentFacade implements PaymentFacade {

    private final PaymentService paymentService;
    private final String PLN_CURRENCY = "PLN";

    @Override
    public MakePaymentResponseDto makePayment(MakePaymentDto payment) {
        if (ObjectUtils.isEmpty(payment.getTotalAmount()) && payment.getTotalAmount().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("Total amount mus be greater than 0");
        } else if (!PLN_CURRENCY.equalsIgnoreCase(payment.getCurrencyCode())) {
            throw new IllegalStateException("Received wrong currency code");
        }
        return PaymentDtoMapper.mapToPaymentResponseDto
                (paymentService.makePayment(payment.getTotalAmount(), payment.getCurrencyCode()));
    }

    @Override
    public List<PaymentDto> getPaymentsForUser() {
        return PaymentDtoMapper.mapToPaymentDtos(
                paymentService.getPaymentsForUser());
    }
}
