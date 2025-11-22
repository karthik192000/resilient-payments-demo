package com.resilient.payments.demo.service;

import com.resilient.payments.demo.rest.api.response.UserAuthorizationResponse;

public interface GoogleOauthService {

  public UserAuthorizationResponse authorizeUser(String authCode);
}
