package com.spring.cloud.zipkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 *
 * @author Travel Hu
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinStreamServer
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