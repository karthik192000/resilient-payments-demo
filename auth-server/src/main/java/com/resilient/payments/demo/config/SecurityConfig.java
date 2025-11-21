package com.resilient.payments.demo.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

  @Bean
  @Order(1)
  public SecurityFilterChain authServerSecurity(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
        .with(OAuth2AuthorizationServerConfigurer.authorizationServer(), Customizer.withDefaults())
        .authorizeHttpRequests(
            auth -> {
              auth.requestMatchers("/oauth2/**").permitAll();
              auth.requestMatchers(new AntPathRequestMatcher("/clients/")).permitAll();
              auth.requestMatchers(new AntPathRequestMatcher("/.well-known/**")).permitAll();
              auth.anyRequest().authenticated();
            })
        .csrf(csrf -> csrf.ignoringRequestMatchers("/.well-known/**","/clients/"))
        .formLogin(Customizer.withDefaults());
    httpSecurity
        .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
        .oidc(Customizer.withDefaults());

    return httpSecurity.build();
  }

  @Bean
  public RegisteredClientRepository registeredClientRepository(DataSource dataSource) {
    return new JdbcRegisteredClientRepository(new JdbcTemplate(dataSource));
  }

  @Bean
  AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder()
        .issuer("https://auth-server:9090") // must match issuer-uri in your Resource API
        .build();
  }

  @Bean
  JWKSource<SecurityContext> jwkSource() {
    var key = generateRsaKey();
    var jwkSet = new JWKSet(key);
    return (selector, context) -> selector.select(jwkSet);
  }

  private RSAKey generateRsaKey() {
    try {
      var kpg = KeyPairGenerator.getInstance("RSA");
      kpg.initialize(2048);
      var kp = kpg.generateKeyPair();
      return new RSAKey.Builder((RSAPublicKey) kp.getPublic())
          .privateKey(kp.getPrivate())
          .keyID(UUID.randomUUID().toString())
          .build();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  @Bean
  UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("admin").password("{noop}password").roles("ADMIN").build();
    return new InMemoryUserDetailsManager(user);
  }
}
