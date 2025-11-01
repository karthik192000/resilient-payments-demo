package com.resilient.payments.demo.rest.api.request;

import lombok.Getter;
import lombok.Setter;

/** * Payments Switch Request DTO */
@Setter
@Getter
public class PaymentsSwitchRequest {

  private String senderName;

  private Integer senderAccountNumber;

  private String receiverName;

  private Integer receiverAccountNumber;

  private Double amount;

  private String switchReference;
}
