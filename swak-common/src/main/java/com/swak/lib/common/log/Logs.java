package com.swak.lib.common.log;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

import java.util.Objects;

/**
 * 静态日志类
 *
 * @author: ljq
 * @date: 2024/10/29
 */
@Slf4j
public class Logs {

    @Setter
    private static String appName;

    @Setter
    private static boolean grey;

    public static void info(String logType, String msg, Object... args) {

        BizLogger.build(logType).appName(appName).reqMsg(MessageFormatter.arrayFormat(msg, args).getMessage()).log();
    }

    public static void error(String logType, Exception e) {

        BizLogger.build(logType).appName(appName).error(e).log();
    }

    public static void error(String logType, Exception e, String msg, Object... args) {

        BizLogger.build(logType)
                .appName(appName)
                .reqMsg(MessageFormatter.arrayFormat(msg, args).getMessage())
                .error(e)
                .log();
    }

    public static void log(BizLogger logger) {
        if (Objects.nonNull(logger)) {
            logger.appName(appName).log();
        }
    }

    public static void main(String[] args) {
        info("testLog", "code={}", 1111);

        error("testLogError", new NullPointerException());
    }


}
