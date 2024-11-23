package com.swak.lib.common.log;

import lombok.Getter;

/**
 * @author: ljq
 * @date: 2024/10/27
 */
@Getter
public class BizLogContext {

    private final static ThreadLocal<BizLogContext> instance = ThreadLocal.withInitial(BizLogContext::new);

    private String traceId;

    private String bizIdentify;

    public static BizLogContext getInstance() {
        return instance.get();
    }

    public BizLogContext traceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public BizLogContext bizIdentify(String bizIdentify) {
        this.bizIdentify = bizIdentify;
        return this;
    }


    public static void remove() {
        instance.remove();
    }
}
