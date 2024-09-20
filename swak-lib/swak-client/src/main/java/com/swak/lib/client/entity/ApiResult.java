package com.swak.lib.client.entity;

/**
 * 接口通用返回报文
 *
 * @author ljq
 */
public class ApiResult<T> {

    private String errMsg;

    private String code;

    private T data;



}
