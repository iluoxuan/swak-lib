package com.swak.lib.common.tools;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: ljq
 * @date: 2024/9/22
 */
public class DateTools extends DateUtil {

    public static final String timeZone = "GMT+8";

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);

    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN);

    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN);


    public static DateTime parseWithEmpty(CharSequence dateStr, String format) {
        if (StringTools.isBlank(dateStr)) {
            return null;
        }
        return new DateTime(dateStr, format);
    }

    public static DateTime getPreviousFriday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        return DateTime.of(calendar.getTime());
    }

    public static void main(String[] args) {

        Date date = getPreviousFriday(new Date());
        System.out.println(date);
    }

}
