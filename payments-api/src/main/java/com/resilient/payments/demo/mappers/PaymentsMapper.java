package com.resilient.payments.demo.mappers;

import com.resilient.payments.demo.entity.Payment;
import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;
import org.mapstruct.Mapper;

/** * Payments Mapper to map between PaymentRequest, Payment Entity and PaymentResponse */
@Mapper(componentModel = "spring")
public abstract class PaymentsMapper {

  /**
   * Map PaymentRequest to Payment Entity
   *
   * @param paymentRequest
   * @return
   */
  public abstract Payment map(PaymentRequest paymentRequest);

  /**
   * Map Payment Entity to PaymentsSwitchRequest
   *
   * @param payment
   * @return
   */
  public abstract PaymentResponse map(Payment payment);
}
