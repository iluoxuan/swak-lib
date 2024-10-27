package com.swak.lib.client.exception;

import lombok.Getter;

/**
 * 复杂点可以 系统-业务-code
 *
 * @author: ljq
 * @date: 2024/9/22
 */
@Getter
public enum SysBizError implements SwakError {

    SUCCESS("00000", "success"),

    ARGUMENT_ERROR("00001", "argument error"),


    /**
     * 本系统异常：A开头
     */
    SYS_ERROR("A99999", "current sys error"),

    /**
     * 内部系统异常
     * 内部微服务异常： I开头
     */
    INNER_SYS_ERROR("I99999", "internal system error"),

    /**
     * 第三方外部系统异常： T开头
     */
    THIRD_SYS_ERROR("T99999", "third system error"),

    ;


    private String code;

    private String msg;

    SysBizError(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
