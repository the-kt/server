package com.kumquat.common.shiro;

import org.springframework.context.annotation.Configuration;

/**
 * @Author:deepblue
 * @Date:2019/4/30 11:29
 * @Description:
 **/
@Configuration
public class ShiroConfig {
/*
    @Autowired
    private RainbowProperties rainbowProperties;

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

    *//*    Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();//获取filters
        filters.put("user", new CustomUserFilter());*//*

        // 设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录的 url
        shiroFilterFactoryBean.setLoginUrl(rainbowProperties.getShiro().getLoginUrl());
        // 登录成功后跳转的 url
        shiroFilterFactoryBean.setSuccessUrl(rainbowProperties.getShiro().getSuccessUrl());
        // 未授权 url
        shiroFilterFactoryBean.setUnauthorizedUrl(rainbowProperties.getShiro().getUnauthorizedUrl());

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 设置免认证 url
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(rainbowProperties.getShiro().getAnnoUrl(), ",");
        for (String url : anonUrls) {
            filterChainDefinitionMap.put(url, "anon");
        }
        // 配置退出过滤器，其中具体的退出代码 Shiro已经替我们实现了
        filterChainDefinitionMap.put(rainbowProperties.getShiro().getLogoutUrl(), "logout");
        // 除上以外所有 url都必须认证通过才可以访问，未通过认证自动访问 LoginUrl
        filterChainDefinitionMap.put("/**", "user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置 SecurityManager，并注入 shiroRealm
        securityManager.setRealm(shiroRealm());
        // 配置 rememberMeCookie
       // securityManager.setRememberMeManager(rememberMeManager());
        // 配置 缓存管理类 cacheManager
       // securityManager.setCacheManager(getEhCacheManager());
       // securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        // 配置 Realm，需自己实现，见 cc.mrbird.common.shiro.ShiroRealm
        return new ShiroRealm();
    }


    private CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie 加密的密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    *//**
    * @Description: 增加Ehcache的缓存 
    * @Param: [] 
    * @return: org.apache.shiro.cache.ehcache.EhCacheManager 
    * @Author: deepblue
    * @Date: 2019/4/30 
    *//*
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:config/shiro-ehcache.xml");
        return em;
    }

    *//**
     * rememberMe cookie 效果是重开浏览器后无需重新登录
     *
     * @return SimpleCookie
     *//*
    private SimpleCookie rememberMeCookie() {
        // 设置 cookie 名称，对应 login.html 页面的 <input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置 cookie 的过期时间，单位为秒，这里为一天
        cookie.setMaxAge(rainbowProperties.getShiro().getCookieTimeout());
        return cookie;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }*/
}
