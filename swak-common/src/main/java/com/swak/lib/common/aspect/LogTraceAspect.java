package com.swak.lib.common.aspect;

import cn.hutool.core.util.ArrayUtil;
import com.swak.lib.common.log.BizLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面
 *
 * @author: ljq
 * @date: 2024/10/29
 */
@Aspect
public class LogTraceAspect {


    /**
     * LogTrace注解的切点
     */
    @Pointcut("@annotation(com.swak.lib.common.annotion.LogTrace) || @within(com.swak.lib.common.annotion.LogTrace)")
    public void logPointcut() {
    }

    /**
     * web层注解的切点
     */
    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void controllerPointcut() {

    }

    @Around("logPointcut() && controllerPointcut()")
    public Object webLogPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        // 获取HttpServletRequest对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        BizLogger logger = BizLogger.build(methodName).url(request.getRequestURI());
        try {


            // 判断参数
            if (ArrayUtil.isEmpty(args)) {

            }

            // 执行连接点方法
            Object result = joinPoint.proceed();
            logger.resMsg(result);
            return result;
        } catch (Throwable e) {
            logger.error(e);
            throw e;
        } finally {
            logger.log();
        }
    }
}
