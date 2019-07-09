package com.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.account.config.SwaggerConfig;
import com.account.util.LoggingInterceptor;

@SpringBootApplication
@Import({SwaggerConfig.class})
//@EnableEurekaClient
public class AccountTestApplication extends WebMvcConfigurerAdapter
{

    public static void main(String[] args)
    {
        SpringApplication.run(AccountTestApplication.class, args);
    }

//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry)
//    {
//        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/v1/**");
//    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder.build();

    }
}
