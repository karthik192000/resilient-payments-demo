package com.resilient.payments.demo.resilience;

import java.util.function.Predicate;

import static com.resilient.payments.demo.constants.PaymentConstants.RETRYABLE_EXCEPTIONS;

/**
 * Predicate to evaluate if an exception is retryable for Payments operations
 * Only exceptions listed in RETRYABLE_EXCEPTIONS are considered retryable
 */
public  class PaymentsRetryPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(Throwable throwable) {
        return RETRYABLE_EXCEPTIONS.contains(throwable.getClass());
    }
}
