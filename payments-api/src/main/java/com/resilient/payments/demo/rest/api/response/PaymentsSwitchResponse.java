package com.resilient.payments.demo.rest.api.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentsSwitchResponse {


    private String switchReference;

    private String message;

    private String status;

    private String timestamp;

    private boolean recon;
}
