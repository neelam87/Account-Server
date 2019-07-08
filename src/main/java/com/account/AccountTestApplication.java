package com.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.account.util.LoggingInterceptor;

@SpringBootApplication
@EnableEurekaClient
public class AccountTestApplication
//extends WebMvcConfigurerAdapter
{

    public static void main(String[] args)
    {
        SpringApplication.run(AccountTestApplication.class, args);
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry)
//    {
//        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
//    }


   
}
