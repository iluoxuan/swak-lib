package com.swak.lib.common.trace;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.swak.lib.common.constants.HttpHeaderKey;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: ljq
 * @date: 2024/11/10
 */
public class TraceTools {

    /**
     * 从web中获取traceId
     *
     * @param request
     * @return
     */
    public static String getWebTraceId(HttpServletRequest request) {
        // 先从skywalking中context获取
        String traceId = TraceContext.traceId();
        // 再从header头中获取
        if (StrUtil.isBlank(traceId)) {
            traceId = request.getHeader(HttpHeaderKey.TRACE_ID_HEADER);
        }

        // 从mdc中获取traceId
        if (StrUtil.isBlank(traceId)) {
            traceId = MDC.get(HttpHeaderKey.TRACE_ID_HEADER);
        }
        if (StrUtil.isBlank(traceId)) {
            traceId = IdUtil.fastSimpleUUID();
        }
        return traceId;
    }

    public static void main(String[] args) {

        System.out.println(IdUtil.fastSimpleUUID());
    }
}
