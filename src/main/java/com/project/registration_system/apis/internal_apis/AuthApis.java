package com.project.registration_system.apis.internal_apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthApis {

    @Autowired
    private final RestTemplate restTemplate;
    @Value("${AUTH_GATEWAY_URL}")
    private String url;

    public AuthApis(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> authenticationServiceApis(String parameter, String path) {
        String fullUrl = url + path + parameter;
        ResponseEntity<?> response = restTemplate.getForEntity(fullUrl, String.class);
        return response;

    }
}
