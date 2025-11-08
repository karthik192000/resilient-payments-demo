package com.resilient.payments.demo.rest.api.response;

import lombok.Getter;
import lombok.Setter;

/** * * Payment Response DTO */
@Getter
@Setter
public class PaymentResponse {

  String paymentId;

  String paymentReference;

  String status;

  String amount;
}
