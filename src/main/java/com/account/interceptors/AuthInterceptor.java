package com.account.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.account.exception.UnAuthorizedException;
import com.account.util.AccountConstants;

//import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

//@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Value("${urls.auth-url}")
    private String authUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return isValidToken(request).getBody();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {

    }

    private <T> ResponseEntity<Boolean> isValidToken(HttpServletRequest request) {
    	ResponseEntity<Boolean> responseEntity = null;
    	try{
    		responseEntity = restTemplate.exchange(authUrl, HttpMethod.POST,
                new HttpEntity<Void>(null, exposeRequestHeaders(request)), Boolean.class);
    	}catch(Exception ex){
    		 if(responseEntity==null){
    	        	throw new UnAuthorizedException(AccountConstants.UNAUTHORIZED_ERROR_MSG, new Exception("Not authorized")); 
    	        }	
    	}
       
        return responseEntity;
    }

    private HttpHeaders exposeRequestHeaders(final HttpServletRequest aHttpServletRequest) {
        final HttpHeaders requestHeaders = new HttpHeaders();
        final Enumeration<String> headerNames = aHttpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String key = headerNames.nextElement();
            final String value = aHttpServletRequest.getHeader(key);
            
            requestHeaders.set(key, value);
        }
        return requestHeaders;
    }
}