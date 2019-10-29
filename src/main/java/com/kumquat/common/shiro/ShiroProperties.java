package com.kumquat.common.shiro;

/**
 * @Author:liuhao
 * @Date:2019/4/29 10:05
 * @Description:shiro的基本配置类
 **/
public class ShiroProperties {

    /**
     * shiro redis缓存时长,默认1800秒
     */
    private int expireIn = 1800;

    /**
     * session 超时时间,默认1800000毫秒
     */
    private long sessionTimeout = 1800000L;

    /**
     * rememberMe cookie有效时长，默认86400秒，即一天
     */
    private int cookieTimeout = 86400;

    /**
     * 免认证的路径配置，如静态资源，druid监控页面,注册页面,验证码请求等
     */
    private String annoUrl;

    /**
     * 登录url
     */
    private String loginUrl = "/login";

    /**
     * 登录成功后跳转的url
     */
    private String successUrl = "/index";

    /**
     * 登出 url
     */
    private String logoutUrl = "/logout";

    /**
     * 未授权跳转 url
     */
    private String unauthorizedUrl;

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public long getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public int getCookieTimeout() {
        return cookieTimeout;
    }

    public void setCookieTimeout(int cookieTimeout) {
        this.cookieTimeout = cookieTimeout;
    }

    public String getAnnoUrl() {
        return annoUrl;
    }

    public void setAnnoUrl(String annoUrl) {
        this.annoUrl = annoUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }
}