package com.wisdom.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hik回调,切面加锁
 */
@Aspect
@Component
@Order(2)
public class HikCallBackAopConfig {

    /**
     * 锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 为了解决Action重复创建的问题,使用切面加锁机制来解决
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(public * com.wisdom.controller.hikcallback.HikCallBackController.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        lock.lock();
        Object ob;
        try {
            ob = pjp.proceed();
        } finally {
            lock.unlock();
        }
        return ob;
    }
}
