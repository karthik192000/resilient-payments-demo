package com.resilient.payments.demo.constants;

import java.util.Set;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

/** * * Payment Constants Class */
public class PaymentConstants {

  public static final String PAYMENT_REFERENCE_PREFIX = "pay_";

  public static final String SWITCH_REFERENCE = "switch-reference";

  public static final String SUCCESS = "SUCCESS";

  public static final String CORRELATION_ID_PREFIX = "corr-id-";

  public static final String CORRELATION_ID_HEADER = "x-correlation-Id";

  public static final String CORRELATION_ID_LOG_KEY = "correlation-Id";
  public static final Set<Class<? extends Exception>> RETRYABLE_EXCEPTIONS =
      Set.of(
          ResourceAccessException.class,
          HttpServerErrorException.InternalServerError.class,
          HttpServerErrorException.ServiceUnavailable.class,
          HttpClientErrorException.TooManyRequests.class);
}
