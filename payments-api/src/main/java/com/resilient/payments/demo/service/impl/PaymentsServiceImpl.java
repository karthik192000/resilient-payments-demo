package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.dao.PaymentsDao;
import com.resilient.payments.demo.entity.Payment;
import com.resilient.payments.demo.enums.PaymentStatus;
import com.resilient.payments.demo.mappers.PaymentsMapper;
import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;
import com.resilient.payments.demo.service.PaymentsService;
import com.resilient.payments.demo.util.PaymentsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Service
@Slf4j
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    PaymentsDao paymentsDao;

    @Autowired
    PaymentsMapper paymentsMapper;

    @Override
    public PaymentResponse execute(PaymentRequest paymentRequest) {
        log.info("PaymentsServiceImpl.execute called with request: {}", paymentRequest);
        PaymentResponse paymentResponse = null;
        try {
            String paymentReference = PaymentsUtil.generatePaymentReference();
            Payment payment = paymentsMapper.map(paymentRequest);
            if(Objects.nonNull(payment)){
                payment.setPaymentReference(paymentReference);
                payment.setStatus(PaymentStatus.RECEIVED.getStatus());
                payment.setCreatedDt(Timestamp.from(Instant.now()));
                payment.setRetries(0);
                Payment savedPayment = paymentsDao.createPayment(payment);
                if(Objects.nonNull(savedPayment)){
                    paymentResponse =  paymentsMapper.map(savedPayment);
                }
            }

        }
        catch (Exception ex){
            log.error("Exception in PaymentsServiceImpl.execute: ", ex);
        }
        return paymentResponse;
    }
}
