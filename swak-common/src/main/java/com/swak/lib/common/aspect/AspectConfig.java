package com.swak.lib.common.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ljq
 * @date: 2024/10/29
 */
@Configuration
public class AspectConfig {

    @Bean
    public LogTraceAspect logTraceAspect() {
        return new LogTraceAspect();
    }
}
