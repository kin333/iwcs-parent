/*
 * GridSortInfo.java
 *
 * Created Date: 2016年12月30日
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
 * <p>高级grid排序信息</p>
 */

public class GridSortInfo {
    /**
     * 排序列
     */
    private String sortKey;
    /**
     * 排序方式，desc/asc
     */
    private String sortValue;

    public String getSortKey() {

        return sortKey;
    }

    public void setSortKey(String sortKey) {

        this.sortKey = sortKey;
    }

    public String getSortValue() {

        return sortValue;
    }

    public void setSortValue(String sortValue) {

        this.sortValue = sortValue;
    }


    public String getMybatisStr() {
        return this.sortKey + " " + this.sortValue;
    }
}
