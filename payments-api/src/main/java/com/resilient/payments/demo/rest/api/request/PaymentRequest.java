package com.resilient.payments.demo.rest.api.request;

import lombok.Getter;
import lombok.Setter;

/** * Payment Request DTO */
@Getter
@Setter
public class PaymentRequest {

  private String senderName;

  private Integer senderAccountNumber;

  private String receiverName;

  private Integer receiverAccountNumber;

  private String currency;

  private Double amount;

  private String paymentReference;
}
