package com.resilient.payments.demo.service;

import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;

public interface PaymentsService {

    public PaymentResponse execute(PaymentRequest paymentRequest);
}
