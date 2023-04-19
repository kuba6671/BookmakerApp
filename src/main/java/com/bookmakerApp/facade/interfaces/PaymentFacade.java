package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.MakePaymentDto;
import com.bookmakerApp.facade.dtos.MakePaymentResponseDto;
import com.bookmakerApp.facade.dtos.PaymentDto;

import java.util.List;

public interface PaymentFacade {

    MakePaymentResponseDto makePayment(MakePaymentDto payment);

    List<PaymentDto> getPaymentsForUser();
}
