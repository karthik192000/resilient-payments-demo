package com.resilient.payments.demo.rest.api.request;


import lombok.Getter;
import lombok.Setter;

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
