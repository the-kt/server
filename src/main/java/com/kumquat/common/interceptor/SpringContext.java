package com.kumquat.common.interceptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by 13260 on 2019/9/2.
 */
public class SpringContext implements ApplicationContextAware,DisposableBean {
    private static ApplicationContext applicationContext;
    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        try {
            applicationContext=context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }
    public static String[] getBeanNamesForType(Class<?> type){
        return applicationContext.getBeanNamesForType(type);
    }
    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        applicationContext = null;
    }
}
