package com.resilient.payments.demo.exceptions;

import com.resilient.payments.demo.model.ErrorDetails;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PaymentsException extends RuntimeException {

  private HttpStatus httpStatus;

  private String message;

  private ErrorDetails errorDetails;

  public PaymentsException(HttpStatus httpStatus, ErrorDetails errorDetails) {
    super(errorDetails.getErrorMessage());
    this.httpStatus = httpStatus;
    this.errorDetails = errorDetails;
  }

  public PaymentsException(String message) {
    super(message);
    this.message = message;
  }
}
