package com.resilient.payments.demo.enums;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    TRANSACTION_FAILED("ERROR_001", "Transaction Failed"),
    FAILED_TO_RETRIEVE_PAYMENT_DETAILS("ERROR_002", "Failed to retrieve payment details"),
    PAYMENT_DETAILS_NOT_FOUND("ERROR_003", "Payment Details not found"),
    UNEXPECTED_ERROR_OCCURRED("ERROR_004", "An unexpected error occurred. Please try again later.");




    private final String errorCode;

    private final String errorMessage;


     ErrorMessages(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }






}
