package com.resilient.payments.demo.rest.api;

import com.resilient.payments.demo.rest.request.ClientRegistrationRequest;
import com.resilient.payments.demo.rest.response.ClientRegistrationResponse;
import com.resilient.payments.demo.service.ClientRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientRegistrationController {

  @Autowired ClientRegistrationService clientRegistrationService;

  @PostMapping(
      path = "/",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClientRegistrationResponse> registerClient(
      @RequestBody ClientRegistrationRequest clientRegistrationRequest) {
    return new ResponseEntity<>(
        clientRegistrationService.registerClient(clientRegistrationRequest), HttpStatus.CREATED);
  }

  @PutMapping(
      path = "/",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClientRegistrationResponse> updateRegisteredClient(
      @RequestBody ClientRegistrationRequest clientRegistrationRequest) {
    return new ResponseEntity<>(
        clientRegistrationService.updateRegisteredClient(clientRegistrationRequest), HttpStatus.OK);
  }
}
