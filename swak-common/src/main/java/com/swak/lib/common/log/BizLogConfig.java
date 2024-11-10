package com.swak.lib.common.log;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static com.swak.lib.common.spring.SpringPropertiesKey.*;

/**
 * 义务日志配置
 *
 * @author: ljq
 * @date: 2024/11/10
 */
@AllArgsConstructor
@Configuration
public class BizLogConfig {

    private final Environment environment;

    /**
     * 先这样吧 后面在优化
     *
     * @return
     */
    @ConditionalOnMissingBean(Logs.class)
    @Bean
    public Logs logs() {
        Logs logs = new Logs();
        Logs.setAppName(environment.getProperty(appName, defaultAppName));
        logs.setGrey(environment.getProperty(grey, Boolean.class, false));
        return logs;
    }

}
