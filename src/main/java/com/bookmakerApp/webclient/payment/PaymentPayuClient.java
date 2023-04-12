package com.bookmakerApp.webclient.payment;

import com.bookmakerApp.webclient.payment.dto.PaymentPayuAccessTokenDto;
import com.bookmakerApp.webclient.payment.dto.PaymentResponseDto;
import com.bookmakerApp.webclient.payment.dto.PaymentStatusDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.bookmakerApp.config.constants.payu.PaymentPayuConstants.*;


@Component
@Slf4j
public class PaymentPayuClient {

    private WebClient webClient;

    @Value("${grant_type}")
    private String grantType;
    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;
    @Value("${continueUrl}")
    private String continueUrl;
    @Value("${customerIp}")
    private String customerIp;
    @Value("${description}")
    private String description;

    public PaymentPayuClient() {
        this.webClient = WebClient.create(BASE_URL);
    }

    public PaymentStatusDto getPaymentStatus(String orderId) {
        String accessToken = getAuthorizationToken();
        PaymentStatusDto paymentStatus = webClient.get()
                .uri(CHECK_PAYMENT_STATUS_URL + orderId)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(PaymentStatusDto.class)
                .block();

        log.info("received payment status [{}] for order [{}]",
                paymentStatus.getPayments().get(0).getStatus(), paymentStatus.getPayments().get(0).getOrderId());
        return paymentStatus;
    }

    public PaymentResponseDto makePayment(BigDecimal totalAmount, String currencyCode) {
        String accessToken = getAuthorizationToken();
        Map<String, String> queryParams = prepareQueryParamsForPayment(totalAmount, currencyCode);

        PaymentResponseDto payment = webClient.post()
                .uri(MAKE_PAYMENT_URL)
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(queryParams))
                .retrieve()
                .bodyToMono(PaymentResponseDto.class)
                .block();
        log.info("created payment with orderId [{}]", payment.getOrderId());
        return payment;
    }

    public String getAvailablePaymentMethods() {
        String accessToken = getAuthorizationToken();
        return webClient.get()
                .uri(AVAILABLE_PAYMENT_METHODS_URL)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private String getAuthorizationToken() {
        MultiValueMap<String, String> queryParams = prepareQueryParamsForAuthorization();
        PaymentPayuAccessTokenDto accessToken = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(AUTH_TOKEN_URL)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(PaymentPayuAccessTokenDto.class)
                .block();

        if (ObjectUtils.isNotEmpty(accessToken) && StringUtils.isNotBlank(accessToken.getAccessToken()))
            return accessToken.getAccessToken();
        else
            return StringUtils.EMPTY;
    }

    private MultiValueMap<String, String> prepareQueryParamsForAuthorization() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add(GRANT_TYPE, grantType);
        queryParams.add(CLIENT_ID, clientId);
        queryParams.add(CLIENT_SECRET, clientSecret);

        return queryParams;
    }

    private Map<String, String> prepareQueryParamsForPayment(BigDecimal totalAmount, String currencyCode) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(CONTINUE_URL, continueUrl);
        queryParams.put(CUSTOMER_IP, customerIp);
        queryParams.put(MERCHANT_POS_ID, clientId);
        queryParams.put(DESCRIPTION, description);
        queryParams.put(CURRENCY_CODE, currencyCode);
        queryParams.put(TOTAL_AMOUNT, totalAmount.toString());

        return queryParams;
    }
}
