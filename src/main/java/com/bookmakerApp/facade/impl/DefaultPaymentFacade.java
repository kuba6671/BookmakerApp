package com.bookmakerApp.facade.impl;

import com.bookmakerApp.facade.dtos.PaymentDto;
import com.bookmakerApp.facade.interfaces.PaymentFacade;
import com.bookmakerApp.facade.mappers.PaymentDtoMapper;
import com.bookmakerApp.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class DefaultPaymentFacade implements PaymentFacade {

    private final PaymentService paymentService;

    @Override
    public PaymentDto makePayment(BigDecimal totalAmount, String currencyCode) {
        return PaymentDtoMapper.mapToPaymentDto
                (paymentService.makePayment(totalAmount, currencyCode));
    }
}
