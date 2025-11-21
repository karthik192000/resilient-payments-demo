package com.resilient.payments.demo.service;

import com.resilient.payments.demo.rest.request.ClientRegistrationRequest;
import com.resilient.payments.demo.rest.response.ClientRegistrationResponse;

public interface ClientRegistrationService {

  ClientRegistrationResponse registerClient(ClientRegistrationRequest clientRegistrationRequest);

  ClientRegistrationResponse updateRegisteredClient(
      ClientRegistrationRequest clientRegistrationRequest);
}
