package com.spring.cloud.user.config;

import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * mybatis 配置
 *
 * @author Travel Hu
 */
@Configuration
@MapperScan(basePackages = { "com.spring.cloud.user.dao"})
public class MybatisConfig {

    /**
     * 分页拦截器配置
     *
     * @return
     */
    @Bean
    public PageInterceptor[] pageInterceptors() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "count=check");
        properties.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(properties);

        return new PageInterceptor[] { pageInterceptor };
    }
}
