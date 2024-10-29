package com.swak.lib.common.annotion;

import java.lang.annotation.*;

/**
 * 日志拦截
 *
 * @author: ljq
 * @date: 2024/10/29
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogTrace {


    boolean ignore() default false;


}
