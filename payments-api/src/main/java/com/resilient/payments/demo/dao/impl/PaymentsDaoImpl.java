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

  @Autowired PaymentsRepository paymentsRepository;

  /** {@inheritDoc} */
  @Override
  public Payment getPayment(Long paymentId) {
    log.debug("Fetching payment with id: {}", paymentId);
    return paymentsRepository.findById(paymentId).orElse(null);
  }

  /** {@inheritDoc} */
  @Override
  public Payment getPayment(String paymentReference) {
    log.debug("Fetching payment with reference: {}", paymentReference);
    return paymentsRepository.findByPaymentReference(paymentReference);
  }

  /** {@inheritDoc} */
  @Override
  public Payment createPayment(Payment payment) {
    log.debug("Creating payment with reference: {}", payment.getPaymentReference());
    return paymentsRepository.save(payment);
  }

  /** {@inheritDoc} */
  @Override
  public Payment updatePayment(Payment payment) {
    log.debug("Updating payment with id: {}", payment.getPaymentId());
    return paymentsRepository.save(payment);
  }
}
