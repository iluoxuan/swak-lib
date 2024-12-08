package com.swak.lib.dao.mybatis.plus.ext;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author: ljq
 * @date: 2024/12/8
 */
@NoArgsConstructor
@AllArgsConstructor
public class LimitSql {

    private final static String SQL_LIMIT_SUFFIX = " limit ";

    private int offset;

    private int limit;


    public static LimitSql of(int offset, int limit) {
        return new LimitSql(offset, limit);
    }

    public static LimitSql of(int limit) {
        return new LimitSql(0, limit);
    }

    public String toStr() {

        if (offset <= 0) {
            return SQL_LIMIT_SUFFIX + limit;
        }
        return SQL_LIMIT_SUFFIX + offset + "," + limit;
    }


}
