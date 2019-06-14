/*
 * NonFilterable.java
 *
 * Created Date: 2017年3月22日
 *
 * Copyright (c)  Centling Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Centling Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Centling Technologies Co., Ltd.
 */

package com.wisdom.iwcs.common.utils;

import java.lang.annotation.*;

/**
 * @author Ted.Ma
 * @version <br>
 * <p>不可过滤，用于字段权限控制时，出于业务考虑，不可将该值设空</p>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NonFilterable {

}
