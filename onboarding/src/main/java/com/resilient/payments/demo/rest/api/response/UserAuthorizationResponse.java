package com.resilient.payments.demo.rest.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthorizationResponse {

  private String userName;

  private String accessToken;
}
