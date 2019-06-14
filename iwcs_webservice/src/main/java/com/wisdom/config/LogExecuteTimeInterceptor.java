package com.wisdom.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 执行时间记录过滤器
 *
 * @author ted
 * @create 2019-03-07 上午11:53
 **/
@Aspect
@Component
public class LogExecuteTimeInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LogExecuteTimeInterceptor.class);

    @Pointcut("@annotation(com.wisdom.annotation.LogExecuteTimeRecord)||@annotation(com.wisdom.annotation.LogExecuteParamsAndTimeRecord)||@within(com.wisdom.annotation.LogExecuteParamsAndTimeRecord)||@within(com.wisdom.annotation.LogExecuteTimeRecord)")
    public void logTimeMethodPointcut() {

    }

    @Around("logTimeMethodPointcut()")
    public Object interceptor(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        logger.info(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() + " spend " + (System.currentTimeMillis() - startTime) + "ms");

        return result;
    }


}
