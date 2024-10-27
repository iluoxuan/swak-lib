package com.swak.lib.common.web;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * api接受多date日期格式的字符串
 *
 * @author: ljq
 * @date: 2024/10/27
 */
public class DefaultDateFormatConvert implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        return null;
    }
}
