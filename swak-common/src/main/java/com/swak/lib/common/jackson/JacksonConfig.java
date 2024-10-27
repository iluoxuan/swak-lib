package com.swak.lib.common.jackson;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 统一jackson配置
 *
 * @author: ljq
 * @date: 2024/10/25
 */
@Configuration
public class JacksonConfig {


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return JacksonTools.CUSTOMIZER;
    }

}
