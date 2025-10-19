package com.resilient.payments.demo.rest.api.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    private String senderName;

    private String senderAccountNumber;

    private String receiverName;

    private String receiverAccountNumber;

    private Double amount;


}
