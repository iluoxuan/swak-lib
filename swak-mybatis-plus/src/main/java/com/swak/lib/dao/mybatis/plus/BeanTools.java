package com.swak.lib.dao.mybatis.plus;

import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 优化 beanUtils 新增复制方法 -- 不让引用
 *
 * @author: ljq
 * @date: 2024/9/22
 */
class BeanTools extends BeanUtils {


    /**
     * 复制对象 注意：
     * String --》 Integer 复制不了，就算同名
     */
    public static <T> T copy(Object source, Class<T> tClass) {

        T target = instantiateClass(tClass);
        copyProperties(source, target);
        return target;
    }

    public static <T> List<T> copyList(Collection<T> sources, Class<T> tClass) {

        return sources.stream().map(source -> copy(source, tClass)).collect(Collectors.toList());
    }

    /**
     * 复制处理转换相关业务
     */
    public static <T, R> List<T> copyList(List<R> sources, Function<R, T> function) {

        return sources.stream()
                .map(source -> function.apply(source))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
