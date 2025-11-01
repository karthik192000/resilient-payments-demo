package com.resilient.payments.demo.enums;

import lombok.Getter;

/** * * Payment Status Enum */
@Getter
public enum PaymentStatus {
  PENDING("PENDING"),
  COMPLETED("COMPLETED"),
  FAILED("FAILED"),
  RECEIVED("RECEIVED");

  private final String status;

  PaymentStatus(String status) {
    this.status = status;
  }
}
