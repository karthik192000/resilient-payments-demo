package com.resilient.payments.demo.rest.api;

import com.resilient.payments.demo.enums.ErrorMessages;
import com.resilient.payments.demo.exceptions.PaymentsException;
import com.resilient.payments.demo.util.PaymentsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class PaymentsControllerAdvice {


  @ExceptionHandler(exception = {PaymentsException.class})
  public ResponseEntity<?> handlePaymentException(Exception exception) {
    log.error("PaymentsControllerAdvice.handlePaymentException called with exception: ", exception);
    PaymentsException paymentsException = (PaymentsException) exception;
    return new ResponseEntity<>(
        paymentsException.getErrorDetails(), paymentsException.getHttpStatus());
  }

  @ExceptionHandler(exception = {Exception.class})
  public ResponseEntity<?> handleException(Exception exception) {
    log.error("PaymentsControllerAdvice.handlePaymentException called with exception: ", exception);
    return new ResponseEntity<>(
            PaymentsUtil.prepareErrorDetails(ErrorMessages.UNEXPECTED_ERROR_OCCURRED),
        org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
