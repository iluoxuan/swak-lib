package com.swak.lib.common.httpclient;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
@RequiredArgsConstructor
@EnableConfigurationProperties(HttpclientProperties.class)
@Configuration
public class HttpClientConfig {

    private final HttpclientProperties httpclientProperties;

    @Bean("swakHttpClient")
    public CloseableHttpClient swakHttpClient() {

        // 创建连接池管理器
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(httpclientProperties.getMaxTotal());       // 设置最大连接数
        connectionManager.setDefaultMaxPerRoute(httpclientProperties.getDefaultMaxPerRoute()); // 设置每个路由的最大连接数

        // 创建 HttpClient
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(httpclientProperties.getConnectTimeout())  // 连接超时时间
                        .setSocketTimeout(httpclientProperties.getSocketTimeout())   // 读取超时时间
                        .setConnectionRequestTimeout(httpclientProperties.getConnectionRequestTimeout()) // 连接请求超时时间
                        .build())
                .evictIdleConnections(httpclientProperties.getMaxIdleTime(), TimeUnit.SECONDS)
                .build();
    }
}
