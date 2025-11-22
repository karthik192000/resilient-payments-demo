package com.resilient.payments.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

  private RestTemplate restTemplate;

  @Bean("defaultRestTemplate")
  public RestTemplate getRestTemplate() {
    if (restTemplate == null) {
      restTemplate = new RestTemplate();
    }
    return restTemplate;
  }
}
