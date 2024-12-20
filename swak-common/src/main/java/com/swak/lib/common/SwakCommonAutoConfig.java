package com.swak.lib.common;

import com.swak.lib.common.aspect.AspectConfig;
import com.swak.lib.common.httpclient.HttpClientConfig;
import com.swak.lib.common.jackson.JacksonConfig;
import com.swak.lib.common.log.BizLogConfig;
import com.swak.lib.common.spring.SpringBeanFactory;
import com.swak.lib.common.web.WebConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: ljq
 * @date: 2024/10/25
 */
@Import({JacksonConfig.class, WebConfig.class, AspectConfig.class,
        BizLogConfig.class, HttpClientConfig.class})
@Configuration
public class SwakCommonAutoConfig {

    @ConditionalOnMissingBean(SpringBeanFactory.class)
    @Bean
    public SpringBeanFactory springBeanFactory() {
        return new SpringBeanFactory();
    }
}
