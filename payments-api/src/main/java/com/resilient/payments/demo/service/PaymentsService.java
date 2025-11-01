package com.resilient.payments.demo.service;

import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;

/** * Payments Service Interface */
public interface PaymentsService {

  /**
   * Execute Payment based on PaymentRequest
   *
   * @param paymentRequest
   * @return
   */
  public PaymentResponse execute(PaymentRequest paymentRequest);

  /**
   * Retrieve Payment based on Payment Reference
   *
   * @param paymentReference
   * @return
   */
  public PaymentResponse retrieve(String paymentReference);
}
