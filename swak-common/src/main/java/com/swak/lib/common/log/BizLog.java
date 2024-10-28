package com.swak.lib.common.log;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.swak.lib.common.jackson.JacksonTools;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/10/27
 */
@Getter
@Slf4j
public class BizLog {

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


    private BizLog() {
    }

    public static class Builder {

        private long startTm;

        private long endTm;

        private Object reqMsg;

        private Object resMsg;

        private Exception error;

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

        public Builder startTm() {
            this.startTm = System.currentTimeMillis();
            return this;
        }

        public Builder reqMsg(Object reqMsg) {
            this.reqMsg = reqMsg;
            return this;
        }

        public Builder resMsg(Object resMsg) {
            this.resMsg = resMsg;
            return this;
        }

        public Builder error(Exception error) {
            this.error = error;
            return this;
        }

        public Builder traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public Builder bizIdentify(String bizIdentify) {
            this.bizIdentify = bizIdentify;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public void log() {

            BizLog bizLog = new BizLog();
            bizLog.startTm = this.startTm;
            bizLog.endTm = this.endTm;
            bizLog.bizCode = this.bizCode;
            bizLog.reqMsg = this.reqMsg;
            bizLog.resMsg = this.resMsg;

            if (Objects.nonNull(error)) {
                bizLog.error = ExceptionUtil.stacktraceToString(this.error);
            }

            bizLog.logType = this.logType;
            bizLog.url = this.url;
            bizLog.traceId = this.traceId;
            bizLog.bizIdentify = this.bizIdentify;
            bizLog.bizCode = this.bizCode;
            bizLog.endTm = System.currentTimeMillis();
            bizLog.duration = bizLog.endTm - this.startTm;
            String logMsg = JacksonTools.toJson(bizLog);
            if (Objects.nonNull(this.error)) {
                log.error(logMsg);
                return;
            }
            log.info(logMsg);
        }

    }

    public static Builder build(String logType) {

        Builder builder = new BizLog.Builder().startTm();
        builder.logType = logType;
        return builder;
    }

    public static void main(String[] args) {

        BizLog.Builder log = BizLog.build("testLog");
        log.reqMsg("cesh");
        log.log();
    }

}
