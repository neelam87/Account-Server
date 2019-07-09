package com.account.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class AuthSwaggerConfig {

    @Bean
    public Docket AuthApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("auth-api")
                .apiInfo(getApiInfo())
                .select()
                .paths(regex("/oauth/token"))
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework")))
                .build();
    }


    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title("Auth Service")
                .description("Auth Service to get Access token")
                .termsOfServiceUrl("http://leverage-ration.com")
                .version("1.0").build();
    }
}
