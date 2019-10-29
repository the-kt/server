package com.kumquat.common.interceptor;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 13260 on 2019/9/2.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CacheManager cacheManager;
    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new CrossInterceptor(cacheManager)).addPathPatterns("/**");
    }

}
