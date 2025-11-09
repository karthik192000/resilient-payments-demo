package com.resilient.payments.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for setting up the JWT decoder. This class defines a bean for the
 * `JwtDecoder` that uses a custom `RestTemplate` to handle self-signed certificates when
 * communicating with the authorization server.
 */
@Configuration
public class JwtDecoderConfig {

  @Value("${auth.server.jwks-set-url}")
  String authServerJwksSetUrl;

  @Bean
  public JwtDecoder jwtDecoder(@Qualifier("httpsRestTemplate") RestTemplate restTemplate) {
    return NimbusJwtDecoder.withJwkSetUri(authServerJwksSetUrl)
        .restOperations(
            restTemplate) // customize RestOperations to trust self-signed certs if necessary
        .build();
  }
}
