package com.account.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.account.domainobject.AuthToken;
import com.bsc.rfg.portfolio.domain.model.AuthRequest;



@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${urls.token-url}")
    private String get_token;

    public ResponseEntity<AuthToken> getToken(AuthRequest authRequest) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        logger.info("Fetching Token");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("client_id", authRequest.getClientId()); 
        map.add("username", authRequest.getUsername()); 
        map.add("password", authRequest.getPassword()); 
        map.add("grant_type", authRequest.getGrantType()); 

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        restTemplate.getInterceptors().add(
        		  new BasicAuthorizationInterceptor("client", "clientpassword"));
        ResponseEntity<AuthToken> response = restTemplate.postForEntity(get_token, request , AuthToken.class);
        return response;
    }
}