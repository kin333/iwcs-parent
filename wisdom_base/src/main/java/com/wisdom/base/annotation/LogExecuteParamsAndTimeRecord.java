package com.wisdom.base.annotation;

import java.lang.annotation.*;

/**
 * 记录执行时间及参数
 *
 * @author ted
 * @create 2019-03-07 下午2:07
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@LogExecuteParamsRecord
@LogExecuteTimeRecord
public @interface LogExecuteParamsAndTimeRecord {
}
