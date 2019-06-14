/*
 * BudgetMakingStatus.java
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
 * <p>ProjectOrder中budgetMakingStatus的状态枚举</p>
 */

public enum BudgetMakingStatus {
    NOT_ALLOW_MAKING(-2),
    ALLOW_MAKING(-1),
    SAVING(0),
    HAS_SUBMITTED(1);

    private Integer status;

    BudgetMakingStatus(Integer status) {
        this.status = status;
    }

    // 枚举对象获取status的方法
    public Integer getStatus() {
        return this.status;
    }
}
