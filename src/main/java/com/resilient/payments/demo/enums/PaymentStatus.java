package com.resilient.payments.demo.enums;


import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    private final String status;


    PaymentStatus(String status){
        this.status = status;
    }




}
