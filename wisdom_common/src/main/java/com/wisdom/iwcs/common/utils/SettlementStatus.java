/*
 * SettlementStatus.java
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
 * <p>ProjectOrder中settlementStatus的状态枚举</p>
 */

public enum SettlementStatus {
    NOT_ALLOW_SETTLE(-2),
    ALLOW_SETTLE(-1),
    IS_SETTLING(0),
    FINISH(1);


    private Integer status;

    SettlementStatus(Integer status) {
        this.status = status;
    }

    // 枚举对象获取status的方法
    public Integer getStatus() {
        return this.status;
    }
}
