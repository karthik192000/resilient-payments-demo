package com.resilient.payments.demo.mappers;

import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.request.PaymentsSwitchRequest;
import org.mapstruct.Mapper;

/** * * PaymentsSwitch Mapper to map between PaymentRequest and PaymentsSwitchRequest */
@Mapper(componentModel = "spring")
public abstract class PaymentsSwitchMapper {

  /**
   * Map PaymentRequest to PaymentsSwitchRequest
   *
   * @param paymentRequest
   * @return
   */
  public abstract PaymentsSwitchRequest map(PaymentRequest paymentRequest);
}
