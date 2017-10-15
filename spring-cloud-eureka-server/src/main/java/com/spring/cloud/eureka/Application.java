package com.spring.cloud.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 *
 * @author Travel Hu
 */
@SpringBootApplication
@EnableEurekaServer
public class Application implements DisposableBean {

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
}