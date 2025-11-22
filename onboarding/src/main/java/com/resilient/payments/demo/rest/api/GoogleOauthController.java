package com.resilient.payments.demo.rest.api;

import com.resilient.payments.demo.rest.api.response.UserAuthorizationResponse;
import com.resilient.payments.demo.service.GoogleOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/google-oauth")
public class GoogleOauthController {

  @Autowired GoogleOauthService googleOauthService;

  @GetMapping(value = "/authorize", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserAuthorizationResponse> authorizeUser(
      @RequestParam(name = "auth_code", required = true) String authCode) {
    return new ResponseEntity<>(googleOauthService.authorizeUser(authCode), HttpStatus.OK);
  }
}
