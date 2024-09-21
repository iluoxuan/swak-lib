package com.swak.lib.client.entity;

import lombok.Data;

/**
 * 接口通用返回报文
 *
 * @author ljq
 */
@Data
public class ApiResult<T> {

    private String msg;

    private String code;

    private T data;

}
