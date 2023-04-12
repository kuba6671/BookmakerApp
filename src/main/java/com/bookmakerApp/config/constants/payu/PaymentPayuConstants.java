package com.bookmakerApp.config.constants.payu;

public final class PaymentPayuConstants {

    private PaymentPayuConstants() {
    }

    public final static String BASE_URL = "https://secure.snd.payu.com/";
    public final static String AUTH_TOKEN_URL = "pl/standard/user/oauth/authorize";
    public final static String AVAILABLE_PAYMENT_METHODS_URL = "api/v2_1/paymethods";
    public final static String MAKE_PAYMENT_URL = "api/v2_1/orders";
    public final static String CHECK_PAYMENT_STATUS_URL = "api/v2_1/orders/";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String CONTINUE_URL = "continueUrl";
    public static final String CUSTOMER_IP = "customerIp";
    public static final String MERCHANT_POS_ID = "merchantPosId";
    public static final String DESCRIPTION = "description";
    public static final String CURRENCY_CODE = "currencyCode";
    public static final String TOTAL_AMOUNT = "totalAmount";
    public static final String STATUS_NEW = "NEW";
    public static final String STATUS_COMPLETED = "COMPLETED";

}
