package com.swak.lib.common.web;

import com.swak.lib.client.entity.ApiRes;
import com.swak.lib.client.exception.SwakBizException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理
 *
 * @author: ljq
 * @date: 2024/10/27
 */
@Slf4j
public abstract class AbstractExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ApiRes<Void> exception(Exception e) {
        log.error("unKnowException ", e);
        return ApiRes.sysError();
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SwakBizException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ApiRes<Void> bizException(SwakBizException e) {
        log.error("bizException ", e);
        return ApiRes.error(e.getSwakError());
    }

    /**
     * 参数通过注解等验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ApiRes<Void> bindException(BindException e) {
        log.error("bindException message={}", e.getMessage());
        StringBuilder result = new StringBuilder();
        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
        result.append("参数错误[");
        for (FieldError fieldError : fieldErrorList) {
            result.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");

        return ApiRes.argumentError(result.toString());
    }

    /**
     * java bean validate for 业务层 异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ApiRes<Void> constraintViolationException(ConstraintViolationException e) {
        log.error("constraintViolationException message={}", e.getMessage());
        StringBuilder result = new StringBuilder();
        result.append("参数错误[");
        Set<ConstraintViolation<?>> sets = e.getConstraintViolations();
        for (ConstraintViolation constrainViolation : sets) {
            PathImpl path = (PathImpl) constrainViolation.getPropertyPath();
            result.append(path.getLeafNode().asString())
                    .append(":")
                    .append(constrainViolation.getMessage())
                    .append(",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return ApiRes.argumentError(result.toString());
    }

    /**
     * 参数异常处理
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, ServletRequestBindingException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    ApiRes<Void> handleMissingServletRequestParameterException(Exception e) {
        log.error("handleMissingServletRequestParameterException message={}", e.getMessage());
        return ApiRes.argumentError("请求参数异常");
    }

    /**
     * http meida erro
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    ApiRes<Void> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("handleHttpMediaTypeNotSupportedException message={}", e.getMessage());
        return ApiRes.argumentError("请求类型contentType不支持");
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    ApiRes<Void> typeMismatchException(Exception e) {
        log.error("typeMismatchException message={}", e.getMessage());
        return ApiRes.argumentError("请求类型typeMismatch异常");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    ApiRes<Void> messageConversionException(Exception e) {
        log.error("messageConversionException message={}", e.getMessage());
        return ApiRes.argumentError("请求体异常");
    }


    /**
     * 断言 相关
     *
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    ApiRes<Void> handleArgumentBizException(Exception e) {
        log.error("handleArgumentBizException errorMsg={}", e.getMessage());
        return ApiRes.argumentError(e.getMessage());
    }

}
