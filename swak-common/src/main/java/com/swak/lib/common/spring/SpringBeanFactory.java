package com.swak.lib.common.spring;

import com.swak.lib.common.tools.AssertTools;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author: ljq
 * @date: 2024/10/26
 */
public class SpringBeanFactory implements ApplicationContextAware, EnvironmentAware {

    private static ApplicationContext applicationContext;

    private static Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    @Override
    public void setEnvironment(Environment env) {
        environment = env;
    }

    public static <T> T getBean(Class<T> requiredType) {
        AssertTools.notNull(applicationContext, "getBean Application context not initialized");
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name) {
        AssertTools.notNull(applicationContext, "getBean Application context not initialized");
        return (T) applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        AssertTools.notNull(environment, "getProperty environment not initialized");
        return environment.getProperty(key);
    }
}
