package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.rest.api.response.UserAuthorizationResponse;
import com.resilient.payments.demo.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class OnboardingServiceImpl implements OnboardingService {

  @Autowired UserDetailsService userDetailsService;

  @Override
  public UserAuthorizationResponse authorizeUser(String authCode) {
    return null;
  }
}
