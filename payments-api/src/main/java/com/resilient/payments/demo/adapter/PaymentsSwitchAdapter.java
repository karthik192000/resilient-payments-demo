package com.resilient.payments.demo.adapter;


import com.resilient.payments.demo.rest.api.request.PaymentsSwitchRequest;
import com.resilient.payments.demo.rest.api.response.PaymentsSwitchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@Slf4j
public class PaymentsSwitchAdapter {



    @Autowired
    @Qualifier("defaultRestTemplate")
    private RestTemplate restTemplate;



    @Value("${api.payments.switch}")
    private String paymentsSwitchAPI;



    public PaymentsSwitchResponse callPaymentsSwitch(PaymentsSwitchRequest paymentsSwitchRequest){
        PaymentsSwitchResponse paymentsSwitchResponse = null;
        HttpEntity<?> entity = new HttpEntity<>(paymentsSwitchRequest);
        try{
           ResponseEntity<PaymentsSwitchResponse> response =  restTemplate.exchange(paymentsSwitchAPI, HttpMethod.POST,entity, PaymentsSwitchResponse.class);
           if(response.getStatusCode().is2xxSuccessful()){
               paymentsSwitchResponse = response.getBody();
           }
        }
        catch (HttpClientErrorException | HttpServerErrorException ex) {
            log.info("HttpClientErrorException in PaymentsSwitchAdapter.callPaymentsSwitch: ", ex);
            HttpHeaders headers = ex.getResponseHeaders();
            String switchReference = headers.getFirst("switch-reference");
            paymentsSwitchRequest.setSwitchReference(switchReference);
        }
        catch (Exception ex){
            log.info("Exception in PaymentsSwitchAdapter.callPaymentsSwitch: ", ex);
        }
        return paymentsSwitchResponse;
    }



}
