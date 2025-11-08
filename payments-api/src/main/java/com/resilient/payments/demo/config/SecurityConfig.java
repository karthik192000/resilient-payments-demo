package com.resilient.payments.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for setting up application security. This class defines the security filter
 * chain for the application, enabling OAuth2 resource server support with JWT authentication.
 */
@Configuration
@Slf4j
public class SecurityConfig {

  @Bean
  public SecurityFilterChain applicationSecurity(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
    return httpSecurity.build();
  }
}
