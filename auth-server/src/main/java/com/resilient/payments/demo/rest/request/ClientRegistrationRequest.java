package com.resilient.payments.demo.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegistrationRequest {


    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private String scope;




}
