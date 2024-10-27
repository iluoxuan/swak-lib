package com.swak.lib.common.jackson;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @author: ljq
 * @date: 2024/10/27
 */
public class JacksonDateCovert {

    public static class DateSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

            gen.writeString(DateUtil.format(value, DatePattern.NORM_DATETIME_PATTERN));
        }
    }

    public static class DateDeserializer extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

            return DateUtil.parse(p.getValueAsString());

        }

    }
}
