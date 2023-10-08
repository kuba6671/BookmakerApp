package com.bookmakerApp.webclient.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PaymentStatusDto {
    @JsonProperty("orders")
    private List<PaymentOrderDto> payments;
    @JsonProperty("properties")
    private List<PaymentStatusProperties> properties;
}
