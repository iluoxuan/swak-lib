package com.swak.lib.client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ljq
 * @date: 2024/9/21
 */
@ApiModel
@Data
public class Req {

    @ApiModelProperty("请求id")
    private String reqId;

}
