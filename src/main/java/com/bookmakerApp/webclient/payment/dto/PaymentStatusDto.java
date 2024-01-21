package com.bookmakerApp.webclient.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class PaymentStatusDto {
    @JsonProperty("orders")
    private List<PaymentOrderDto> payments;
    @JsonProperty("properties")
    private List<PaymentStatusProperties> properties;

    public Optional<PaymentOrderDto> getFirstPayment() {
        return payments
                .stream()
                .findFirst();
    }
}
