package com.wisdom.config;

import net.sf.json.JSONArray;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 执行参数记录过滤器
 *
 * @author ted
 * @create 2019-03-07 上午11:53
 **/
@Aspect
@Component
public class LogExecuteParamsInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LogExecuteParamsInterceptor.class);

    @Pointcut("@annotation(com.wisdom.base.annotation.LogExecuteParamsRecord)||@annotation(com.wisdom.base.annotation.LogExecuteParamsAndTimeRecord)||@within(com.wisdom.base.annotation.LogExecuteParamsAndTimeRecord)||@within(com.wisdom.base.annotation.LogExecuteParamsRecord)")
    public void logParamsMethodPointcut() {

    }

    @Around("logParamsMethodPointcut()")
    public Object interceptor(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        if (args != null) {
            logger.info(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() + " params: {}", JSONArray.fromObject(pjp.getArgs()).toString());
        }
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            logger.error(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() + " return : {}", e.getMessage());

            throw new RuntimeException(e);
        }
        logger.info(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() + " return : {}", JSONArray.fromObject(result));
        return result;
    }


}
