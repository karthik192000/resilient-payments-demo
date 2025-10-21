package com.resilient.payments.demo.resilience;

import java.util.function.Predicate;

import static com.resilient.payments.demo.constants.PaymentConstants.RETRYABLE_EXCEPTIONS;

public  class PaymentsRetryPredicate implements Predicate<Throwable> {


    @Override
    public boolean test(Throwable throwable) {
        return RETRYABLE_EXCEPTIONS.contains(throwable.getClass());
    }
}
