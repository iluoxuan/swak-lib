package com.swak.lib.common.aspect;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author: ljq
 * @date: 2024/10/31
 */
@Getter
@Configuration
@ConfigurationProperties(prefix = "swak.log-trace")
public class LogTraceProperties {

    /**
     * 记录的headers
     */
    private Set<String> headers = new HashSet<>();

    /**
     * http的header头 大小写不敏感 【服务器不同】
     *
     * @param headers
     */
    public void setHeaders(Set<String> headers) {
        this.headers = headers.stream().map(String::toLowerCase).collect(Collectors.toSet());
    }

    public Map<String, String> filterHeaders(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            if (headers.contains(headerName.toLowerCase()) || AUTHORIZATION.equalsIgnoreCase(headerName)) {
                result.put(headerName, headerValue);
            }
        }
        return result;

    }

}
