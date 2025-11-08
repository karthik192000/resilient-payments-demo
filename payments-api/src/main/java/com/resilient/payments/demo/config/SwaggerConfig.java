package com.resilient.payments.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** * Swagger Configuration Class for API Documentation */
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI(SpecVersion.V30)
        .info(
            new Info()
                .title("Resilient Payments API")
                .version("1.0")
                .description("API documentation for the Resilient Payments Demo application"));
  }
}
