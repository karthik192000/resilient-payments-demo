package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.adapter.GoogleOauthAdapter;
import com.resilient.payments.demo.dao.OnboardingDao;
import com.resilient.payments.demo.entity.User;
import com.resilient.payments.demo.rest.api.response.UserAuthorizationResponse;
import com.resilient.payments.demo.service.GoogleOauthService;
import com.resilient.payments.demo.util.JwtUtil;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoogleOauthServiceImpl implements GoogleOauthService {

  @Autowired UserDetailsService userDetailsService;

  @Autowired GoogleOauthAdapter googleOauthAdapter;

  @Autowired OnboardingDao onboardingDao;

  @Autowired PasswordEncoder passwordEncoder;

  @Autowired JwtUtil jwtUtil;

  @Override
  public UserAuthorizationResponse authorizeUser(String authCode) {
    UserAuthorizationResponse userAuthorizationResponse = null;

    try {
      String accessToken = googleOauthAdapter.exchangeAuthCodeForAccessToken(authCode);
      String email = googleOauthAdapter.getUserInfoFromGoogle(accessToken);
      validateOrRegisterUser(email);
      String onboardingAccessToken = jwtUtil.generateToken(email);
      userAuthorizationResponse = new UserAuthorizationResponse();
      userAuthorizationResponse.setUserName(email);
      userAuthorizationResponse.setAccessToken(onboardingAccessToken);

    } catch (Exception ex) {
      log.error("Exception occurred while authorizing user: {}", ex.getMessage());
    }

    return userAuthorizationResponse;
  }

  private void validateOrRegisterUser(String email) {

    try {
      userDetailsService.loadUserByUsername(email);
    } catch (UsernameNotFoundException ex) {
      User user = new User();
      user.setUserEmail(email);
      user.setUserName(email);
      user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
      onboardingDao.saveUser(user);
    }
  }
}
