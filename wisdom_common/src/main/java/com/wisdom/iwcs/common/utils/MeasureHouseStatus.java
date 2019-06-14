/*
 * MeasureHouseStatus.java
 *
 * Created Date: 2017年3月21日
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


/**
 * @author Anne.Zhang
 * @version <br>
 * <p>ProjectOrder中measureHouseStatus的状态枚举</p>
 */

public enum MeasureHouseStatus {

    NOT_ALLOW_NOTICE(-3),
    ALLOW_NOTICE(-2),
    HAS_NOTICED(-1),
    SAVING(0),
    HAS_SUBMITTED(1),
    HAS_PASSED(2),
    REJECTED(3);

    private Integer status;

    MeasureHouseStatus(Integer status) {
        this.status = status;
    }

    // 枚举对象获取status的方法
    public Integer getStatus() {
        return this.status;
    }
}
