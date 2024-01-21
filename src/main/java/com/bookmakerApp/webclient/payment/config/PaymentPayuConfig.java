package com.bookmakerApp.webclient.payment.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "payments.payu")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentPayuConfig {
    public static String grantType;
    public static String clientId;
    public static String clientSecret;
    public static String continueUrl;
    public static String customerIp;
    public static String description;
}
