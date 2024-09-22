package com.swak.lib.client.entity;

import com.swak.lib.client.exception.SysBizError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

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

    public boolean isSuccess() {
        return SysBizError.SUCCESS.getCode().equals(code);
    }

    public boolean isSuccessNoData() {
        return isSuccess() && Objects.isNull(data);
    }

    public static ApiResult<Void> success() {
        return success(null);
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(SysBizError.SUCCESS.getCode());
        apiResult.setMsg(SysBizError.SUCCESS.getMsg());
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult<Void> sysError() {
        ApiResult<Void> apiResult = new ApiResult<>();
        apiResult.setCode(SysBizError.SYS_ERROR.getCode());
        apiResult.setMsg(SysBizError.SYS_ERROR.getMsg());
        return apiResult;
    }



}
