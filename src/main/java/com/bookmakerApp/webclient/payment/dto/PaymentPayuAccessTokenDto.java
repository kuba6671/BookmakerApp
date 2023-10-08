package com.bookmakerApp.webclient.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentPayuAccessTokenDto {
    @JsonProperty("access_token")
    private String accessToken;
}
