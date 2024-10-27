package com.swak.lib.dao.mybatis.plus.gen;

import lombok.Data;

/**
 * @author: ljq
 * @date: 2024/10/26
 */
@Data
public class GenInfo {

    private String jdbcUrl;

    private String userName;

    private String password;

    private String author = "MybatisPlusAutoGen";

    /**
     * 包名： com.swak.demo.dao
     */
    private String packageName;

    /**
     * 当前所在的模块 比如 swak-dao
     */
    private String modelName;

    /**
     * 要生成的表
     */
    private String genTableName;

}
