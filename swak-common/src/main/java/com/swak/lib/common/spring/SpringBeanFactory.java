package com.swak.lib.common.spring;

import com.swak.lib.common.tools.AssertTools;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: ljq
 * @date: 2024/10/26
 */
public class SpringBeanFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T getBean(Class<T> requiredType) {
        AssertTools.notNull(applicationContext, "getBean Application context not initialized");
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name) {
        AssertTools.notNull(applicationContext, "getBean Application context not initialized");
        return (T)applicationContext.getBean(name);
    }
}
