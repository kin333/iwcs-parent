/*
 * GridFliteDataType.java
 *
 * Created Date: 2016年12月29日
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
 * @author Ted.Ma
 * @version <br>
 * <p>类的描述</p>
 */

public enum GridFliteDataType {

    SELECT("SELECT"), DATE("DATE"), DATETIME("DATETIME"), INPUT("INPUT");
    private String fliteDataType;

    GridFliteDataType(String fliteDataType) {
        this.fliteDataType = fliteDataType;
    }

    @Override
    public String toString() {

        return String.valueOf(this.fliteDataType);

    }

}
