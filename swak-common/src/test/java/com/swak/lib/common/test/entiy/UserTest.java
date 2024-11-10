package com.swak.lib.common.test.entiy;

import lombok.Data;

import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/10
 */
@Data
public class UserTest {

    public static final String APP_NAME = "MyApp";

    private int id;

    private String name;


    private List<UserTest> sub;
}
