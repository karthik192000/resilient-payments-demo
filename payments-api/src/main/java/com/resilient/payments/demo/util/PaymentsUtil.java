package com.resilient.payments.demo.util;

import java.util.UUID;

import static com.resilient.payments.demo.constants.PaymentConstants.PAYMENT_REFERENCE_PREFIX;

public class PaymentsUtil {


    /**
     * Generate a unique payment reference
     * @return
     */

    public static String generatePaymentReference(){
        return PAYMENT_REFERENCE_PREFIX + System.currentTimeMillis();
    }
}
