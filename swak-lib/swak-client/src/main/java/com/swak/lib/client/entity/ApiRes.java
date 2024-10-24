package com.swak.lib.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ApiRes<T> {

    @ApiModelProperty("msg:")
    private String msg;

    @ApiModelProperty("code:")
    private String code;

    @ApiModelProperty("data: 业务数据")
    private T data;

    @ApiModelProperty("traceId")
    private String traceId;

    @JsonIgnore
    public boolean isSuccess() {
        return SysBizError.SUCCESS.getCode().equals(code);
    }

    @JsonIgnore
    public boolean isSuccessNoData() {
        return isSuccess() && Objects.isNull(data);
    }

    public static ApiRes<Void> success() {
        return success(null);
    }

    public static <T> ApiRes<T> success(T data) {
        ApiRes<T> apiResult = new ApiRes<>();
        apiResult.setCode(SysBizError.SUCCESS.getCode());
        apiResult.setMsg(SysBizError.SUCCESS.getMsg());
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiRes<Void> sysError() {
        ApiRes<Void> apiResult = new ApiRes<>();
        apiResult.setCode(SysBizError.SYS_ERROR.getCode());
        apiResult.setMsg(SysBizError.SYS_ERROR.getMsg());
        return apiResult;
    }

    public static ApiRes<PageRes<Void>> emptyPage() {

        return success(new PageRes<>());
    }


}
