package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;
import com.resilient.payments.demo.service.PaymentsService;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService {
    @Override
    public PaymentResponse execute(PaymentRequest paymentRequest) {
        return null;
    }
}
