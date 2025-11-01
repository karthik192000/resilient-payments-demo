package com.resilient.payments.demo.util;

import com.resilient.payments.demo.model.ErrorDetails;

public class PaymentsUtil {

  public static ErrorDetails prepareErrorDetails(String errorCode, String errorMessage) {
    return new ErrorDetails(errorCode, errorMessage);
  }
}
