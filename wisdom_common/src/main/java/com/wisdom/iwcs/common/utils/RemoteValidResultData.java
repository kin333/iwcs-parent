/*
 * RemoteValidResultData.java
 *
 * Created Date: 2017年3月27日
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

import java.io.Serializable;

/**
 * @author Ted.Ma
 * @version <br>
 * <p>用于返回远程校验结果</p>
 */

public class RemoteValidResultData implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -316435602204128757L;
    /**
     * 是否校验通过
     */
    private boolean isValid;
    /**
     * 校验值
     */
    private String value;

    /**
     * @return the isValid
     */
    public boolean getIsValid() {

        return isValid;
    }

    /**
     * @param isValid the isValid to set
     */
    public void setIsValid(boolean isValid) {

        this.isValid = isValid;
    }

    /**
     * @return the value
     */
    public String getValue() {

        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {

        this.value = value;
    }


}
