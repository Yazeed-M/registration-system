package com.project.registration_system.apis.internal_apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class AuthApis {
    @Autowired
    private final RestTemplate restTemplate;
    String url = "http://localhost:8082/";

    public AuthApis(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> authenticationServiceApis(String parameter, String path) {
        String fullUrl=url+path + parameter;
        // UriComponentsBuilder uriComponentsBuilder= new UriComponentsBuilder;
        // uriComponentsBuilder.
        ResponseEntity<?> response = restTemplate.getForEntity(fullUrl, String.class);
        return response;

    }
}
