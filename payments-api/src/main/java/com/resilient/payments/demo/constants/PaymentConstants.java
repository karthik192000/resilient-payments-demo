package com.resilient.payments.demo.constants;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Set;

public class PaymentConstants {


    public static final String PAYMENT_REFERENCE_PREFIX = "pay_";

    public static final String SWITCH_REFERENCE = "switch-reference";
    public static final Set<Class<? extends Exception>> RETRYABLE_EXCEPTIONS = Set.of(HttpServerErrorException.InternalServerError.class, HttpServerErrorException.ServiceUnavailable.class, HttpClientErrorException.TooManyRequests.class);
}
