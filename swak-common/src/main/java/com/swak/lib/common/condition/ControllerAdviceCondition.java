package com.swak.lib.common.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;

/**
 * 加载ControllerAdvice注解条件
 *
 * @author: ljq
 * @date: 2024/10/27
 */
public class ControllerAdviceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 获取所有带有 @ControllerAdvice 注解的 Bean
        Map<String, Object> controllerAdviceBeans = beanFactory.getBeansWithAnnotation(ControllerAdvice.class);

        // 如果存在任何带有 @ControllerAdvice 注解的 Bean，则不加载基础组件中的 @ControllerAdvice
        return controllerAdviceBeans.isEmpty();
    }
}