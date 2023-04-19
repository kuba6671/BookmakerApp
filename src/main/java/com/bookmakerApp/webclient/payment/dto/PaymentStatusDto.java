package com.bookmakerApp.webclient.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class PaymentStatusDto {

    @JsonProperty("orders")
    private List<PaymentOrderDto> payments;
    @JsonProperty("properties")
    private List<PaymentStatusProperties> properties;
}
