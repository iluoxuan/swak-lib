package com.swak.lib.common;

import com.swak.lib.common.jackson.JacksonConfig;
import com.swak.lib.common.spring.SpringBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: ljq
 * @date: 2024/10/25
 */
@Import({JacksonConfig.class})
@Configuration
public class SwakCommonAutoConfig {

    @Bean
    public SpringBeanFactory springBeanFactory() {
        return new SpringBeanFactory();
    }
}
