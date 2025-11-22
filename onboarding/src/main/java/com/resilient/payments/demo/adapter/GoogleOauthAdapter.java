package com.resilient.payments.demo.adapter;

import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
public class GoogleOauthAdapter {

  @Autowired
  @Qualifier("defaultRestTemplate")
  private RestTemplate restTemplate;

  @Value("${google.oauth.token.endpoint}")
  private String googleTokenEndpoint;

  @Value("${google.oauth.user-info.endpoint}")
  private String googleUserInfoEndpoint;

  @Value("${google.oauth.client-id}")
  private String googleOauthClientId;

  @Value("${google.oauth.client-secret}")
  private String googleOauthClientSecret;

  @Value("${google.oauth.redirect-uri}")
  private String googleOauthRedirectUri;

  public String exchangeAuthCodeForAccessToken(String authCode) {
    try {
      HttpEntity<MultiValueMap<String, String>> requestEntity = createTokenRequestEntity(authCode);
      ResponseEntity<Map> tokenAPIResponseResponseEntity =
          restTemplate.postForEntity(googleTokenEndpoint, requestEntity, Map.class);
      if (tokenAPIResponseResponseEntity.getStatusCode().is2xxSuccessful()) {
        return (String) tokenAPIResponseResponseEntity.getBody().get("id_token");
      }
    } catch (Exception exception) {
      log.error(
          "Exception occurred while exchanging auth code for access token: {}",
          exception.getMessage());
    }

    return null;
  }

  private HttpEntity<MultiValueMap<String, String>> createTokenRequestEntity(String authCode) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("code", authCode);
    params.add("client_id", googleOauthClientId);
    params.add("client_secret", googleOauthClientSecret);
    params.add("redirect_uri", googleOauthRedirectUri);
    params.add("grant_type", "authorization_code");
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/x-www-form-urlencoded");
    return new HttpEntity<>(params, headers);
  }

  public String getUserInfoFromGoogle(String accessToken) {
    Objects.requireNonNull(accessToken, "Access Token cannot be null");
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(googleUserInfoEndpoint);
    uriBuilder.queryParam("id_token", accessToken);
    ResponseEntity<Map> responseEntity =
        restTemplate.getForEntity(uriBuilder.toUriString(), Map.class);
    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      return responseEntity.getBody().get("email").toString();
    }

    return null;
  }
}
