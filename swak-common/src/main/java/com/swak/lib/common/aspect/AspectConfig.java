package com.swak.lib.common.aspect;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ljq
 * @date: 2024/10/29
 */
@EnableConfigurationProperties(LogTraceProperties.class)
@Configuration
public class AspectConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTraceProperties logTraceProperties) {
        return new LogTraceAspect(logTraceProperties);
    }
}
