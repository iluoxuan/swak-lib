package com.swak.lib.client.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

/**
 * 分页返回
 *
 * @author: ljq
 * @date: 2024/9/21
 */
@Setter
@Getter
public class PageRes<T> {

    private Collection<T> items;

    private int totalCnt = 0;

}
