package com.resilient.payments.demo.adapter;


import com.resilient.payments.demo.rest.api.request.PaymentsSwitchRequest;
import com.resilient.payments.demo.rest.api.response.PaymentsSwitchResponse;
import io.github.resilience4j.retry.annotation.Retry;
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


    @Retry(name = "payments-switch",fallbackMethod = "fallbackOnRetryFailure")
    public PaymentsSwitchResponse executePayment(PaymentsSwitchRequest paymentsSwitchRequest){
        PaymentsSwitchResponse paymentsSwitchResponse = null;
        HttpEntity<?> entity = new HttpEntity<>(paymentsSwitchRequest);
        try{
           ResponseEntity<PaymentsSwitchResponse> response =  restTemplate.exchange(paymentsSwitchAPI, HttpMethod.POST,entity, PaymentsSwitchResponse.class);
           if(response.getStatusCode().is2xxSuccessful()){
               paymentsSwitchResponse = response.getBody();
           }
        }
        catch (HttpClientErrorException | HttpServerErrorException ex) {
            log.error("HttpClientErrorException in PaymentsSwitchAdapter.callPaymentsSwitch: ", ex);
            HttpHeaders headers = ex.getResponseHeaders();
            String switchReference = headers.getFirst("switch-reference");
            paymentsSwitchRequest.setSwitchReference(switchReference);
            throw ex;
        }
        catch (Exception ex){
            log.error("Exception in PaymentsSwitchAdapter.callPaymentsSwitch: ", ex);
            throw ex;
        }
        return paymentsSwitchResponse;
    }


    protected PaymentsSwitchResponse fallbackOnRetryFailure(PaymentsSwitchRequest paymentsSwitchRequest, Throwable t) {
        log.error("All retry attempts exhausted for PaymentsSwitchAdapter.callPaymentsSwitch with switchReference: {}. Exception: {}", paymentsSwitchRequest.getSwitchReference(), t.getMessage());
        PaymentsSwitchResponse paymentsSwitchResponse = new PaymentsSwitchResponse();
        paymentsSwitchResponse.setStatus("FAILED");
        paymentsSwitchResponse.setSwitchReference(paymentsSwitchRequest.getSwitchReference());
        paymentsSwitchResponse.setRecon(true);
        return paymentsSwitchResponse;
    }



    public PaymentsSwitchResponse fetchFinalPaymentStatus(String switchReference){

        return null;

    }


}
