package com.swak.lib.common.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

/**
 * 静态日志类
 *
 * @author: ljq
 * @date: 2024/10/29
 */
@Slf4j
public class Logs {

    public static void info(String logType, String msg, Object... args) {

        BizLogger.build(logType).reqMsg(MessageFormatter.arrayFormat(msg, args).getMessage()).log();
    }

    public static void error(String logType, Exception e) {

        BizLogger.build(logType).error(e).log();
    }

    public static void error(String logType, Exception e, String msg, Object... args) {

        BizLogger.build(logType).reqMsg(MessageFormatter.arrayFormat(msg, args).getMessage()).error(e).log();
    }

    public static void main(String[] args) {
        info("testLog", "code={}", 1111);

        error("testLogError", new NullPointerException());
    }
}
