package com.resilient.payments.demo.adapter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentsSwitchAdapter {



    @Autowired
    @Qualifier("defaultRestTemplate")
    private RestTemplate restTemplate;



    @Value("${api.payments.switch}")
    private String paymentsSwitchAPI;



    public ResponseEntity<?> callPaymentsSwitch(){
        return null;
    }



}
