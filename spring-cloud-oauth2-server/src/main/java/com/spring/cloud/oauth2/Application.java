package com.spring.cloud.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 启动类
 *
 * @author Travel Hu
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.spring.cloud"})
@EnableAuthorizationServer
public class Application extends WebMvcConfigurerAdapter implements DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static ConfigurableApplicationContext ctx;

    public static void main(String[] args) throws Exception {
        ctx = SpringApplication.run(Application.class, args);

        LOGGER.info("spring.profiles.active:");
        for (String str : ctx.getEnvironment().getActiveProfiles()) {
            LOGGER.info(str);
        }
        LOGGER.info("Boot Server started.");
    }

    @Override
    public void destroy() throws Exception {
        if (null != ctx && ctx.isRunning()) {
            ctx.close();
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("login").setViewName("login");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/page").setViewName("page");
    }
}