package com.swak.lib.common.httpclient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "swak.http-client")
public class HttpclientProperties {


    private Integer maxTotal = 500;
    private Integer defaultMaxPerRoute = 50;
    private Integer connectionRequestTimeout = 3 * 1000;
    private Integer connectTimeout = 3 * 1000;
    private Integer socketTimeout = 30 * 1000;
    private int maxIdleTime = 30;

    private Map<String, Param> config = new HashMap<>();

    @Data
    public class Param {

        private String url;

        private Integer defaultMaxPerRoute;

        private Integer maxTotal;

        private Integer connectionRequestTimeout;

        private Integer connectTimeout;

        private Integer socketTimeout;
    }

    public Param getParam(HttpReqName name) {
        return config.get(name.name());
    }


}
