package com.swak.lib.common.web;

import com.swak.lib.client.entity.ApiRes;
import com.swak.lib.client.exception.SwakBizException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
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
public abstract class AbstractWebExceptionHandler {

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SwakBizException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiRes<Void> bizException(SwakBizException e) {

        return ApiRes.error(e.getSwakError());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiRes<Void> bindException(BindException e) {

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
    @ResponseStatus(HttpStatus.OK)
    public ApiRes<Void> constraintViolationException(ConstraintViolationException e) {

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
    @ResponseStatus(HttpStatus.OK)
    ApiRes<Void> handleMissingServletRequestParameterException(Exception e) {

        return ApiRes.argumentError("请求参数异常");
    }

    /**
     * http meida erro
     */
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(HttpStatus.OK)
    ApiRes<Void> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {

        return ApiRes.argumentError("请求类型contentType不支持");
    }

    @ExceptionHandler(org.springframework.beans.TypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    ApiRes<Void> typeMismatchException(Exception e) {


        return ApiRes.argumentError("请求类型typeMismatch异常");
    }

    /**
     * 断言 相关
     *
     * @param e
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.OK)
    Object handleArgumentBizException(Exception e) {

        return ApiRes.argumentError(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        return ApiRes.argumentError(errorMsg.toString());
    }
}
