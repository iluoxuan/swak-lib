package com.swak.lib.common.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ljq
 * @date: 2024/10/27
 */
@Configuration
public class WebConfig {

    @Bean
    public DefaultAdviceControllerHandler defaultExceptionHandler() {
        return new DefaultAdviceControllerHandler();
    }
}

