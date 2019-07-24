package com.wisdom.iwcs.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanId, Class<T> clazz){
        Assert.notNull(applicationContext, "ApplicationContextAware未初始化完成");
        return applicationContext.getBean(beanId, clazz);
    }

    public static <T> T getBean(Class<T> clazz){
        Assert.notNull(applicationContext, "ApplicationContextAware未初始化完成");
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String beanId){
        Assert.notNull(applicationContext, "ApplicationContextAware未初始化完成");
        return applicationContext.getBean(beanId);
    }

    public static void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent((Object)event);
    }
}
