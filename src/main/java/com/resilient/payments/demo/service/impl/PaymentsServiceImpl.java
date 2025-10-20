package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.dao.PaymentsDao;
import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;
import com.resilient.payments.demo.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    PaymentsDao paymentsDao;

    @Override
    public PaymentResponse execute(PaymentRequest paymentRequest) {
        return null;
    }
}
