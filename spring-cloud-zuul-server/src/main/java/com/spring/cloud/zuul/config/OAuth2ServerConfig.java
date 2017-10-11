package com.spring.cloud.zuul.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.util.StringUtils;

/**
 * OAuth2 服务配置
 *
 * @author Travel Hu
 */
//@EnableResourceServer
//@Configuration
public class OAuth2ServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${oauth2.serverUrl}")
    private String serverUrl;
    @Value("${oauth2.clientId}")
    private String clientId;
    @Value("${oauth2.secret}")
    private String secret;
    @Value("${oauth2.resourceId}")
    private String resourceId;
    @Value("${oauth2.apiUrls}")
    private String apiUrls;
    @Value("${oauth2.scopes}")
    private String scopes;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        StringBuilder serverUrlBuilder = new StringBuilder();
        remoteTokenServices.setCheckTokenEndpointUrl(serverUrlBuilder.append(serverUrl).append("/check_token").toString());
        remoteTokenServices.setClientId(clientId);
        remoteTokenServices.setClientSecret(secret);
        resources.tokenServices(remoteTokenServices);
        resources.resourceId(resourceId);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (StringUtils.isEmpty(apiUrls) || StringUtils.isEmpty(scopes)) {
            throw new RuntimeException("apiUrls and scopes not empty");
        }

        //这块可以灵活配置 demo是 一个资源可以配多个scope 多个scope用逗号隔开
        //这块配置数据目前是读取配置文件 做成可视化数据拉去也是非常棒的主意
        String[] urlArray = apiUrls.split("[,]");
        String[] scopeArray = scopes.split("[|]");

        if (urlArray.length != scopeArray.length) {
            throw new RuntimeException("apiUrls and scopes mismatching");
        }

        for (int i = 0; i < urlArray.length; i++) {
            http.authorizeRequests().antMatchers(urlArray[i]).access("#oauth2.hasScope('" + scopeArray[i] + "')");
        }
    }
}