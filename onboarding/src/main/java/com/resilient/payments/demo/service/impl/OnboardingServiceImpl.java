package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.adapter.GoogleOauthAdapter;
import com.resilient.payments.demo.rest.api.response.UserAuthorizationResponse;
import com.resilient.payments.demo.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class OnboardingServiceImpl implements OnboardingService {

  @Autowired UserDetailsService userDetailsService;

  @Autowired GoogleOauthAdapter googleOauthAdapter;

  @Override
  public UserAuthorizationResponse authorizeUser(String authCode) {

    String accessToken = googleOauthAdapter.exchangeAuthCodeForAccessToken(authCode);

    return null;
  }
}
