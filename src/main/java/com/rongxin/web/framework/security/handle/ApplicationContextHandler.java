package com.rongxin.web.framework.security.handle;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHandler implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHandler.applicationContext = applicationContext;
    }

    //此种方式在使用taskService名字获取bean时找不到，可自己试试其他名字。
    public static Object getBean(String name) {
        return ApplicationContextHandler.applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextHandler.applicationContext;
    }
}