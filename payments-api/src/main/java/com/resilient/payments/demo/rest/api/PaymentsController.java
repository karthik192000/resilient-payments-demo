package com.resilient.payments.demo.rest.api;


import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;
import com.resilient.payments.demo.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1/payments")
public class PaymentsController {


    @Autowired
    PaymentsService paymentsService;


    @PostMapping(path = "/execute")
    public ResponseEntity<PaymentResponse> executePayment(@RequestBody  PaymentRequest request) {
        PaymentResponse response = paymentsService.execute(request);
        return ResponseEntity.ok(response);
    }



    @GetMapping(path = "/retrieve")
    public ResponseEntity<PaymentResponse> retrievePayment(String paymentReference) {
        PaymentResponse response = paymentsService.retrieve(paymentReference);
        return ResponseEntity.ok(response);
    }


}
