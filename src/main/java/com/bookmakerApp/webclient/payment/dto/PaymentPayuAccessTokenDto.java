package com.bookmakerApp.webclient.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PaymentPayuAccessTokenDto {

    @JsonProperty("access_token")
    private String accessToken;
}
