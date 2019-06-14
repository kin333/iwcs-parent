package com.wisdom.config;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Edwin on 7/13/16.
 * <p>
 * This class is used for Spring AOP.
 */

@Aspect
@Component
public class AspectConfiguration {

    Logger log = LoggerFactory.getLogger(AspectConfiguration.class);

    /**
     *
     * @param joinPoint
     * @throws Throwable
     */
//    @Around(" execution (* com.wisdom.service.impl.*.*(..))")
//    public void exampleAround(ProceedingJoinPoint joinPoint) throws Throwable{
//        try{
//            //do something before the method executes.
//            //You can also get the method name or method parameters from jointPoint.
//            joinPoint.proceed();
//            //do something after the method executed.
//        }catch (Exception e){
//            log.info(e.getStackTrace().toString());
//        }
//    }
}
