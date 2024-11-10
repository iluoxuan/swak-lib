package com.swak.lib.common.spring;

/**
 * 环境配置
 *
 * @author: ljq
 * @date: 2024/11/10
 */
public class EnvProperties {

    private String appName;

    /**
     * dev test uat prod
     */
    private String profile;

    /**
     * 是否灰度环境
     */
    private boolean grey;

}
