package com.swak.lib.client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口通用返回报文
 *
 * @author ljq
 */
@ApiModel("api返回结果")
@Data
public class ApiResult<T> {

    @ApiModelProperty("msg:")
    private String msg;

    @ApiModelProperty("code:")
    private String code;

    @ApiModelProperty("data: 业务数据")
    private T data;

}
