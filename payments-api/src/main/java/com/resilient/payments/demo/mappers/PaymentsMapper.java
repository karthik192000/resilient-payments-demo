package com.resilient.payments.demo.mappers;


import com.resilient.payments.demo.entity.Payment;
import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.request.PaymentsSwitchRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentsMapper {


    public abstract Payment map(PaymentRequest paymentRequest);

    public abstract PaymentResponse map(Payment payment);

}
