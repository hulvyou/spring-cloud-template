package com.spring.cloud.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @author Travel Hu
 */
public class CustomZuulFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomZuulFilter.class);

    //filter类型 pre前置 post后置 error异常
    @Override
    public String filterType() {
        return "post";
    }

    //Filter的执行顺序 数字越大，优先级越低
    @Override
    public int filterOrder() {
        return 1;
    }

    //是否执行Filter
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        try {
            InputStream in = requestContext.getResponseDataStream();
            StringBuffer result = new StringBuffer();
            byte[] b = new byte[1024];
            for (int n; (n = in.read(b)) != -1; ) {
                result.append(new String(b, 0, n));
            }
            LOGGER.info("接口返回结果：{}", result.toString());
            requestContext.setSendZuulResponse(true);
            requestContext.setResponseStatusCode(200);
            requestContext.setResponseBody(result.toString());
        } catch (Exception e) {
            LOGGER.error("解析返回值异常：{}", e);
        }
        return null;
    }
}
