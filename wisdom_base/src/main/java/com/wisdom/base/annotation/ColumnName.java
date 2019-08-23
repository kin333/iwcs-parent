package com.wisdom.base.annotation;

import java.lang.annotation.*;

/**
 * 用于标注Context列的json值对应的DTO类的列名对应的属性
 * @author han
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnName {
    /**
     * 对应的列名
     * @return
     */
    String value();
}
