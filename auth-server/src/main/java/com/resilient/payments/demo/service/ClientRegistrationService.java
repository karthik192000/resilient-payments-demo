package com.resilient.payments.demo.service;

import com.resilient.payments.demo.rest.request.ClientRegistrationRequest;

public interface ClientRegistrationService {


    void registerClient(ClientRegistrationRequest clientRegistrationRequest);
}
