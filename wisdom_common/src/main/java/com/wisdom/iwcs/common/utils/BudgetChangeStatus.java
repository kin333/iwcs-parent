/*
 * BudgetChangeStatus.java
 *
 * Created Date: 2017年3月29日
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
 * <p>类的描述</p>
 */

public enum BudgetChangeStatus {
    NOT_ALLOW_CHANGE(-2),
    ALLOW_CHANGE(-1),
    HAS_CHANGED(0);

    private Integer status;

    BudgetChangeStatus(Integer status) {
        this.status = status;
    }

    // 枚举对象获取status的方法
    public Integer getStatus() {
        return this.status;
    }
}
