package com.wisdom.annotation;

import java.lang.annotation.*;

/**
 * 记录方法运行参数
 *
 * @author ted
 * @create 2019-03-07 上午11:47
 **/
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogExecuteParamsRecord {

}
