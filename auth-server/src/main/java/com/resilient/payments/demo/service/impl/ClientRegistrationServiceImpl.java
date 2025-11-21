package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.mappers.RegisteredClientMapper;
import com.resilient.payments.demo.rest.request.ClientRegistrationRequest;
import com.resilient.payments.demo.rest.response.ClientRegistrationResponse;
import com.resilient.payments.demo.service.ClientRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientRegistrationServiceImpl implements ClientRegistrationService {

  @Autowired RegisteredClientRepository registeredClientRepository;

  @Autowired RegisteredClientMapper registeredClientMapper;

  @Override
  public ClientRegistrationResponse registerClient(
      ClientRegistrationRequest clientRegistrationRequest) {

    RegisteredClient registeredClient = registeredClientMapper.map(clientRegistrationRequest);

    registeredClientRepository.save(registeredClient);

    return registeredClientMapper.map(registeredClient);
  }

  @Override
  public ClientRegistrationResponse updateRegisteredClient(
      ClientRegistrationRequest clientRegistrationRequest) {
    String clientId = clientRegistrationRequest.getClientId();
    RegisteredClient existingClient = registeredClientRepository.findByClientId(clientId);
    RegisteredClient updatedRegisteredClient =
        registeredClientMapper.map(existingClient, clientRegistrationRequest);
    registeredClientRepository.save(updatedRegisteredClient);
    return registeredClientMapper.map(updatedRegisteredClient);
  }
}
