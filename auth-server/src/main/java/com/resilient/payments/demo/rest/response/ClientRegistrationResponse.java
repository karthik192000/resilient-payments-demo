package com.resilient.payments.demo.rest.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegistrationResponse {

  private String clientId;

  private String clientName;

  private String clientIssueDateTime;

  private List<String> scopes;
}
