package com.resilient.payments.demo.rest.api;

import com.resilient.payments.demo.rest.api.request.PaymentRequest;
import com.resilient.payments.demo.rest.api.response.PaymentResponse;
import com.resilient.payments.demo.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/v1/payments")
public class PaymentsController {

  @Autowired PaymentsService paymentsService;

  @PostMapping(path = "/execute")
  public ResponseEntity<PaymentResponse> executePayment(
      @RequestBody PaymentRequest request,
      @RequestHeader(value = "x-correlation-Id", required = false) String correlationId,
      @RequestHeader(value = "x-partner-id", required = true) String partnerId) {
    PaymentResponse response = paymentsService.execute(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping(path = "/retrieve/{paymentReference}")
  public ResponseEntity<PaymentResponse> retrievePayment(
      @PathVariable(name = "paymentReference") String paymentReference,
      @RequestHeader(value = "x-correlation-Id", required = false) String correlationId,
      @RequestHeader(value = "x-partner-id", required = true) String partnerId) {
    PaymentResponse response = paymentsService.retrieve(paymentReference);
    return ResponseEntity.ok(response);
  }
}
