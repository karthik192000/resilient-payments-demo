package com.resilient.payments.demo.mappers;


import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.request.PaymentsSwitchRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentsSwitchMapper{


    public abstract PaymentsSwitchRequest map(PaymentRequest paymentRequest);

}
