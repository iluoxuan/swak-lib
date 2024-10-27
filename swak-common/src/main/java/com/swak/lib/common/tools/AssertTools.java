package com.swak.lib.common.tools;

import com.swak.lib.client.exception.SwakBizException;
import com.swak.lib.client.exception.SwakError;
import org.springframework.util.Assert;

/**
 * 断言相关扩展
 *
 * @author: ljq
 * @date: 2024/9/22
 */
public class AssertTools extends Assert {


    /**
     * 需要精确到code的异常
     * 1. 有些情况下前需要根据code处理业务
     */
    public static void state(boolean expression, SwakError swakError) {
        if (!expression) {
            throw new SwakBizException(swakError);
        }
    }
}
