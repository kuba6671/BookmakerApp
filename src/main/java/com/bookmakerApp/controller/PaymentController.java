package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.payment.MakePaymentDto;
import com.bookmakerApp.facade.dtos.payment.MakePaymentResponseDto;
import com.bookmakerApp.facade.dtos.payment.PaymentDto;
import com.bookmakerApp.facade.impl.PaymentFacade;
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
    public List<PaymentDto> getPaymentsForUser(@RequestParam(defaultValue = "0") int pageNumber) {
        return paymentFacade.getPaymentsForUser(pageNumber);
    }

}
