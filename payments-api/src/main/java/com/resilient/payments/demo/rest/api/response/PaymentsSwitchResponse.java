package com.resilient.payments.demo.rest.api.response;


import lombok.Getter;
import lombok.Setter;


/**
 * * * Payments Switch Response DTO
 */
@Getter
@Setter
public class PaymentsSwitchResponse {


    private String switchReference;

    private String message;

    private String status;

    private String timestamp;

    /**
     * This recon flag determines if the payment needs to go for reconciliation
     * This is not returned by the switch but is set internally based on the exception status (whether it is retriable or not)
     */
    private boolean recon;
}
