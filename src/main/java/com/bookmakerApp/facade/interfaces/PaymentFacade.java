package com.bookmakerApp.facade.interfaces;

import com.bookmakerApp.facade.dtos.payment.MakePaymentDto;
import com.bookmakerApp.facade.dtos.payment.MakePaymentResponseDto;
import com.bookmakerApp.facade.dtos.payment.PaymentDto;

import java.util.List;

public interface PaymentFacade {

    MakePaymentResponseDto makePayment(MakePaymentDto payment);

    List<PaymentDto> getPaymentsForUser(int pageNumber);
}
