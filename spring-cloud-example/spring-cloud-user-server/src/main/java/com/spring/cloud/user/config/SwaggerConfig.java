package com.spring.cloud.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置
 *
 * @author Travel Hu
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Autowired
    private ApiInfo apiInfo;

    @Bean
    public Docket userAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }


    @Bean
    public  ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("用户系统API文档").description("用户系统API文档")
                .termsOfServiceUrl("http://www.wiki.com")
                .contact(new Contact("Travel Hu", "http://www.wiki.com/",
                        "sjmyrmb@sina.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.wiki.com/")
                .version("1.0")
                .build();
    }
}
