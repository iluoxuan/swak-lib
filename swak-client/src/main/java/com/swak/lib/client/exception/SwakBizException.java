package com.swak.lib.client.exception;

import lombok.Getter;

/**
 * 业务系统异常
 *
 * @author: ljq
 * @date: 2024/9/22
 */
@Getter
public class SwakBizException extends RuntimeException {

    private SwakError swakError;

    public SwakBizException(SwakError swakError) {
        super(swakError.getMsg());
        this.swakError = swakError;
    }

    public SwakBizException(SwakError swakError, Throwable cause) {
        super(swakError.getMsg(), cause);
        this.swakError = swakError;
    }

    public SwakBizException(SwakError swakError, String msg) {
        super(msg);
        this.swakError = swakError;
    }

    public static SwakBizException argumentError(String msg) {
        return new SwakBizException(SysBizError.ARGUMENT_ERROR, msg);
    }
}
