package com.resilient.payments.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {

  private final String errorCode;

  private final String errorMessage;

  public ErrorDetails(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public ErrorDetails(String errorMessage) {
    this.errorCode = "0";
    this.errorMessage = errorMessage;
  }
}
