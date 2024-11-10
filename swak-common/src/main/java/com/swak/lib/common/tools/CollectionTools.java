package com.swak.lib.common.tools;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: ljq
 * @date: 2024/11/10
 */
public class CollectionTools extends CollectionUtil {

    public static Set<String> newToLowerCaseHashSet(String... args) {

        HashSet<String> set = newHashSet();
        if (ArrayUtil.isEmpty(args)) {
            return set;
        }
        return Arrays.stream(args).map(String::toLowerCase).collect(Collectors.toSet());
    }

    public static Set<String> newToLowerCaseHashSet(Set<String> args) {

        HashSet<String> set = newHashSet();
        if (isEmpty(args)) {
            return set;
        }
        return args.stream().map(String::toLowerCase).collect(Collectors.toSet());
    }
}
