package com.resilient.payments.demo.rest.api;

import com.resilient.payments.demo.rest.api.response.UserAuthorizationResponse;
import com.resilient.payments.demo.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

  @Autowired OnboardingService onboardingService;

  @GetMapping(value = "/authorize", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserAuthorizationResponse> authorizeUser(
      @RequestParam(name = "auth_code", required = true) String authCode) {
    return new ResponseEntity<>(onboardingService.authorizeUser(authCode), HttpStatus.OK);
  }
}
