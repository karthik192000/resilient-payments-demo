package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.rest.request.ClientRegistrationRequest;
import com.resilient.payments.demo.service.ClientRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;


@Service
public class ClientRegistrationServiceImpl implements ClientRegistrationService {

    @Autowired
    RegisteredClientRepository registeredClientRepository;

    @Override
    public void registerClient(ClientRegistrationRequest clientRegistrationRequest) {


    }
}
