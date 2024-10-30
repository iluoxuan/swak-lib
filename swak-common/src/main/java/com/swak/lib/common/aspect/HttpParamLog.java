package com.swak.lib.common.aspect;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/10/30
 */
@Data
public class HttpParamLog {

    private Object body;

    private Map<String, Object> param;

    private Map<String, String> headers;

    public void addParam(String key, Object value) {

        if (Objects.isNull(param)) {
            param = new HashMap<>();
        }
        param.put(key, value);
    }

}
