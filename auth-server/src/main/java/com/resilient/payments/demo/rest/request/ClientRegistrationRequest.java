package com.resilient.payments.demo.rest.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegistrationRequest {

  private String clientId;

  private String clientName;

  private List<String> scopes;

  private String jwkSetUrl;
}
