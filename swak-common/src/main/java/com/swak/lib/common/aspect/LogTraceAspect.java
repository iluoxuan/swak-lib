package com.swak.lib.common.aspect;

import cn.hutool.core.util.StrUtil;
import com.swak.lib.common.log.BizLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * 日志切面
 *
 * @author: ljq
 * @date: 2024/10/29
 */
@Slf4j
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
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        // 获取HttpServletRequest对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        BizLogger logger = BizLogger.build(method.getName()).url(request.getRequestURI());
        try {

            HttpParamLog httpParamLog = new HttpParamLog();

            Parameter[] parameters = method.getParameters();

            for (int i = 0; i < args.length; i++) {
                RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
                RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
                if (Objects.nonNull(requestBody)) {
                    httpParamLog.setBody(args[i]);
                }
                if (Objects.nonNull(requestParam)) {
                    String paramName = StrUtil.isBlank(requestParam.value()) ? parameters[i].getName() : requestParam.value();
                    httpParamLog.addParam(paramName, args[i]);
                }
            }

            logger.reqMsg(httpParamLog);

            // 执行连接点方法
            Object result = joinPoint.proceed();
            logger.resMsg(result);
            return result;
        } catch (Throwable e) {
            // 不记录详细日志，通用拦截中会打印
            logger.error(e.getMessage());
            throw e;
        } finally {
            logger.log();
        }
    }
}
