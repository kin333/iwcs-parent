package com.wisdom.base.context;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring AppContext 上下文
 *
 * @author wuhuapeng 2015年3月4日 下午4:22:34
 * @version V1.0
 * @modify: {原因} by wuhuapeng 2015年3月4日 下午4:22:34
 */
public class AppContext implements ApplicationContextAware {

    private static ApplicationContext context = null;

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量
     */
    @Override
    public void setApplicationContext(ApplicationContext context) {
        AppContext.setContext(context);
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getContext() {
        if (context == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义AppContext");
        }
        return context;
    }

    /**
     * 存储静态变量中的ApplicationContext.
     */
    public static void setContext(ApplicationContext context) {
        AppContext.context = context;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        if (context == null)
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义AppContext");
//			throw new IllegalStateException(LocaleHandler.getText("AppContext.applicaitonContext_not_inject_please_define_appContext_in_applicationContextxml"));
        try {
            return (T) context.getBean(name);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return (T) null;
    }
}