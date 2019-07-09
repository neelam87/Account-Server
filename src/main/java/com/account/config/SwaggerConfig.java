package com.account.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {

    @Autowired
    private Environment env;

    private ArrayList<Parameter> addGlobalOperationParameter() {
        ArrayList<Parameter> operationParameters = new ArrayList<>();
        operationParameters.add(new ParameterBuilder()
                .name("Authorization")
                .description(
                        "Authorization token (e.g \"Bearer eyJ0X.eyJpc3MiOi.S5TgsLEK_mLyLN04005z\")")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build());
        return operationParameters;
    }

    @Bean
    public Docket postaApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("private-api")
                .apiInfo(getApiInfo())
                .select()
                .paths(regex("/v1.*"))
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework")))
                .build()
                .globalOperationParameters(addGlobalOperationParameter());
    }


    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title(env.getProperty("title"))
                .description(env.getProperty("description"))
                .termsOfServiceUrl("http://leverage-ration.com")
                .version("1.0").build();
    }
}