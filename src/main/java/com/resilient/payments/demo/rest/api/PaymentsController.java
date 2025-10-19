package com.resilient.payments.demo.rest.api;


import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1/payments")
public class PaymentsController {



    public ResponseEntity<PaymentResponse> executePayment(@RequestBody  PaymentRequest request) {
        return ResponseEntity.ok().build();
    }


}
