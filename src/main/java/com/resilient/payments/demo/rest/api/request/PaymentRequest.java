package com.resilient.payments.demo.rest.api.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    private String senderName;

    private Integer senderAccountNumber;

    private String receiverName;

    private Integer receiverAccountNumber;

    private Double amount;


}
