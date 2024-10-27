package com.swak.lib.common.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: ljq
 * @date: 2024/10/25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private String name;

    private Date date;
}
