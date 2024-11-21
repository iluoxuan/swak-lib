package com.swak.lib.common.tools;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.time.format.DateTimeFormatter;

/**
 * @author: ljq
 * @date: 2024/9/22
 */
public class DateTools extends DateUtil {

    public static final String timeZone = "GMT+8";

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);

    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN);

    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN);
}
