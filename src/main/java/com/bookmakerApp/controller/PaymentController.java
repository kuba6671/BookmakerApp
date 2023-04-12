package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.PaymentDto;
import com.bookmakerApp.facade.interfaces.PaymentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @PostMapping("/makePayment")
    public PaymentDto makePayment(BigDecimal totalAmount, String currencyCode) {
        return paymentFacade.makePayment(totalAmount, currencyCode);
    }

}
