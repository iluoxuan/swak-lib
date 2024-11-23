package com.swak.lib.common.httpclient;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
public class SwakHttpClientException extends RuntimeException {

    private Integer httpStatusCode;


    public SwakHttpClientException(Integer httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public SwakHttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
