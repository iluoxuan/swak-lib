package com.swak.lib.common.log;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.swak.lib.common.jackson.JacksonTools;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/10/27
 */
@Getter
@Slf4j
public class BizLogger {

    private long startTm;

    private long endTm;

    /**
     * 时长 毫秒
     */
    private long duration;

    private Object reqMsg;

    private Object resMsg;

    private String error;

    /**
     * 日志类型
     */
    private String logType;

    private String url;

    private String traceId;

    /**
     * 业务标识
     */
    private String bizIdentify;

    /**
     * 业务编码
     */
    private String bizCode;

    /**
     * 请求结果 code
     */
    private String code;

    private String appName;


    private BizLogger() {
    }

    public BizLogger startTm() {
        this.startTm = System.currentTimeMillis();
        return this;
    }

    public BizLogger reqMsg(Object reqMsg) {
        this.reqMsg = reqMsg;
        return this;
    }

    public BizLogger appName(String appName) {
        this.appName = appName;
        return this;
    }


    public BizLogger resMsg(Object resMsg) {
        this.resMsg = resMsg;
        return this;
    }

    public BizLogger error(Throwable error) {
        if (Objects.nonNull(error)) {
            this.error = ExceptionUtil.stacktraceToString(error);
        }
        return this;
    }

    public BizLogger error(String error) {
        this.error = error;
        return this;
    }

    public BizLogger traceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public BizLogger bizIdentify(String bizIdentify) {
        this.bizIdentify = bizIdentify;
        return this;
    }

    public BizLogger url(String url) {
        this.url = url;
        return this;
    }

    public void log() {

        // 判断traceId是否为空
        if (StrUtil.isBlank(traceId)) {
            // 从skywalking上下文中获取traceId，没获取到就是空的
            traceId = TraceContext.traceId();
        }

        this.endTm = System.currentTimeMillis();
        this.duration = this.endTm - this.startTm;
        String logMsg = JacksonTools.toJson(this);
        if (Objects.nonNull(this.error)) {
            log.error(logMsg);
            return;
        }
        log.info(logMsg);
    }

    public static BizLogger build(String logType) {

        BizLogger bizLog = new BizLogger();
        bizLog.logType = logType;
        bizLog.startTm();
        return bizLog;
    }

    public static BizLogger buildContext(String logType) {
        BizLogger bizLog = BizLogger.build(logType);
        bizLog.bizIdentify = BizLogContext.getInstance().getBizIdentify();
        bizLog.traceId = BizLogContext.getInstance().getTraceId();
        return bizLog;
    }

    public static void main(String[] args) {

        BizLogger.build("testLog").traceId(IdUtil.fastUUID()).log();

        // RPC test

        BizLogger bizLog = BizLogger.build("testLog");

        //
        bizLog.reqMsg("reqmsg");

        bizLog.resMsg("resmsg");

        bizLog.log();

    }

}
