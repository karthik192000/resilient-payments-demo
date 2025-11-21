package com.resilient.payments.demo.mappers;

import com.resilient.payments.demo.rest.request.ClientRegistrationRequest;
import com.resilient.payments.demo.rest.response.ClientRegistrationResponse;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

@Mapper(componentModel = "spring")
public abstract class RegisteredClientMapper {

  public RegisteredClient map(ClientRegistrationRequest clientRegistrationRequest) {

    return RegisteredClient.withId(java.util.UUID.randomUUID().toString())
        .clientId(clientRegistrationRequest.getClientId())
        .clientName(clientRegistrationRequest.getClientName())
        .scopes(scopes -> scopes.addAll(clientRegistrationRequest.getScopes()))
        .clientSettings(
            ClientSettings.builder()
                .jwkSetUrl(clientRegistrationRequest.getJwkSetUrl())
                .tokenEndpointAuthenticationSigningAlgorithm(
                    org.springframework.security.oauth2.jose.jws.SignatureAlgorithm.RS256)
                .build())
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .clientAuthenticationMethod(ClientAuthenticationMethod.PRIVATE_KEY_JWT)
        .build();
  }

  public RegisteredClient map(
      RegisteredClient registeredClient, ClientRegistrationRequest clientRegistrationRequest) {
    return RegisteredClient.from(registeredClient)
        .clientName(clientRegistrationRequest.getClientName())
        .scopes(
            scopes -> {
              scopes.clear();
              scopes.addAll(clientRegistrationRequest.getScopes());
            })
        .clientSettings(
            ClientSettings.builder()
                .jwkSetUrl(clientRegistrationRequest.getJwkSetUrl())
                .tokenEndpointAuthenticationSigningAlgorithm(
                    org.springframework.security.oauth2.jose.jws.SignatureAlgorithm.RS256)
                .build())
        .build();
  }

  @Mapping(target = "clientIssueDateTime", expression = "java(mapIssueDateTime(registeredClient))")
  @Mapping(target = "scopes", expression = "java(mapClientScopes(registeredClient))")
  public abstract ClientRegistrationResponse map(RegisteredClient registeredClient);

  protected List<String> mapClientScopes(RegisteredClient registeredClient) {
    return new ArrayList<>(registeredClient.getScopes());
  }

  protected String mapIssueDateTime(RegisteredClient registeredClient) {
    return registeredClient.getClientIdIssuedAt().toString();
  }
}
