package com.resilient.payments.demo.util;

import java.util.UUID;

import static com.resilient.payments.demo.constants.PaymentConstants.PAYMENT_REFERENCE_PREFIX;

public class PaymentsUtil {



    public static String generatePaymentReference(){
        return PAYMENT_REFERENCE_PREFIX + System.currentTimeMillis();
    }
}
