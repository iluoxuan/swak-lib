package com.swak.lib.common.web;

import com.swak.lib.common.condition.ControllerAdviceCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author: ljq
 * @date: 2024/10/27
 */
@Order
@Conditional(ControllerAdviceCondition.class)
@ControllerAdvice
public class DefaultWebExceptionHandler extends AbstractWebExceptionHandler{


}
