package com.spring.cloud.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 *
 * @author Travel Hu
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.spring.cloud"})
public class Application implements DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static ConfigurableApplicationContext ctx;

    public static void main(String[] args) throws Exception {
        ctx = SpringApplication.run(Application.class, args);

        logger.info("spring.profiles.active:");
        for (String str : ctx.getEnvironment().getActiveProfiles()) {
            logger.info(str);
        }
        logger.info("Boot Server started.");
    }

    @Override
    public void destroy() throws Exception {
        if (null != ctx && ctx.isRunning()) {
            ctx.close();
        }
    }
}