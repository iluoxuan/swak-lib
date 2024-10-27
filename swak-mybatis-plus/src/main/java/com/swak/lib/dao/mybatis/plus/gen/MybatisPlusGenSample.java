package com.swak.lib.dao.mybatis.plus.gen;

/**
 * 参数示例
 *
 * @author: ljq
 * @date: 2024/10/26
 */
public class MybatisPlusGenSample extends MybatisPlusBaseGen {


    public static void main(String[] args) {

        GenInfo genInfo = new GenInfo();
        genInfo.setAuthor("ljq");
        String url = "jdbc:mysql://localhost:3306/swak_demo";
        genInfo.setUserName("root");
        genInfo.setPassword("123456");
        genInfo.setJdbcUrl(url);
        genInfo.setModelName("swak-dao");
        genInfo.setPackageName("com.swak.demo.dao");
        new MybatisPlusGenSample().gen(genInfo);
    }
}
