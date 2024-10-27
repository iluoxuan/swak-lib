package com.swak.lib.client.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: ljq
 * @date: 2024/9/22
 */
@Getter
@Setter
public class PageReq extends Req {

    private int pageSize = 20;

    private int pageNo = 1;
}
