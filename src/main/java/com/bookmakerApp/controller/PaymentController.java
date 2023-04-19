package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.MakePaymentDto;
import com.bookmakerApp.facade.dtos.MakePaymentResponseDto;
import com.bookmakerApp.facade.dtos.PaymentDto;
import com.bookmakerApp.facade.interfaces.PaymentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @PostMapping("/makePayment")
    public MakePaymentResponseDto makePayment(@RequestBody MakePaymentDto payment)
            throws IllegalArgumentException {
        return paymentFacade.makePayment(payment);
    }

    @GetMapping("/payments")
    public List<PaymentDto> getPaymentsForUser() {
        return paymentFacade.getPaymentsForUser();
    }

}
