package com.resilient.payments.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * * * RestTemplate Configuration Class
 */

@Configuration
public class RestTemplateConfiguration {


    private static RestTemplate restTemplate;


    /**
     * Provides RestTemplate Instance
     * @return RestTemplate
     */

    @Bean("defaultRestTemplate")
    public RestTemplate getRestTemplateInstance() {
        if(restTemplate != null){
            return restTemplate;
        }
        return new RestTemplate();
    }
}
