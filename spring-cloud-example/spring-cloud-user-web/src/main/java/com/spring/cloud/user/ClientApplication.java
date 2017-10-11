package com.spring.cloud.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Travel Hu
 */
@EnableOAuth2Sso
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApplication.class).run(args);
    }
}
