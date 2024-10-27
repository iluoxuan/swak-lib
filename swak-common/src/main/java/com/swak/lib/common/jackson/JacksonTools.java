package com.swak.lib.common.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.swak.lib.common.tools.DateTools;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

/**
 * jackson方法封装
 *
 * @author: ljq
 * @date: 2024/10/25
 */
public class JacksonTools {

    public static final Jackson2ObjectMapperBuilderCustomizer CUSTOMIZER = jackson2ObjectMapperBuilderCustomizer();

    public static final ObjectMapper MAPPER = getObjectMapper();

    public static String toJson(Object obj) throws JsonProcessingException {
        return MAPPER.writeValueAsString(obj);
    }

    /**
     * 构建 Jackson 自定义配置
     *
     * @return
     */
    private static Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder
                    // 序列化时，对象为 null，是否抛异常
                    .failOnEmptyBeans(false)
                    // 反序列化时，json 中包含 pojo 不存在属性时，是否抛异常
                    .failOnUnknownProperties(false)
                    // 禁止将 java.util.Date、Calendar 序列化为数字(时间戳)
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    // 设置 java.util.Date, Calendar 序列化、反序列化的格式
                    .dateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN))
                    // 设置 java.util.Date, Calendar 序列化、反序列化的时区
                    .timeZone(TimeZone.getTimeZone(DateTools.timeZone));

            // Jackson 序列化 long类型为String，解决后端返回的Long类型在前端精度丢失的问题
            builder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);

            // 配置 Jackson 反序列化 LocalDateTime、LocalDate、LocalTime 时使用的格式
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTools.dateTimeFormatter));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTools.dateFormatter));
            builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTools.timeFormatter));

            // 配置 Jackson 序列化 LocalDateTime、LocalDate、LocalTime 时使用的格式
            builder.serializers(new LocalDateTimeSerializer(DateTools.dateTimeFormatter));
            builder.serializers(new LocalDateSerializer(DateTools.dateFormatter));
            builder.serializers(new LocalTimeSerializer(DateTools.timeFormatter));
        };
    }

    /**
     * 根据 Jackson2ObjectMapperBuilderCustomizer 构建 ObjectMapper
     *
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        CUSTOMIZER.customize(builder);
        return builder.build();
    }

    public static void main(String[] args) throws JsonProcessingException {
        String jsonString = MAPPER.writeValueAsString(new User("test"));
        System.out.println(jsonString);
    }
}