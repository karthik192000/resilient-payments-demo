package com.resilient.payments.demo.dao.impl;

import com.resilient.payments.demo.dao.PaymentsDao;
import com.resilient.payments.demo.entity.Payment;
import com.resilient.payments.demo.repository.PaymentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PaymentsDaoImpl implements PaymentsDao {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Override
    public Payment getPayment(Long paymentId) {
        log.info("Fetching payment with id: {}", paymentId);
        return paymentsRepository.findById(paymentId).orElse(null);
    }

    @Override
    public Payment createPayment(Payment payment) {
        log.info("Creating payment with reference: {}", payment.getPaymentReference());
        return paymentsRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        log.info("Updating payment with id: {}", payment.getPaymentId());
        return paymentsRepository.save(payment);
    }
}
