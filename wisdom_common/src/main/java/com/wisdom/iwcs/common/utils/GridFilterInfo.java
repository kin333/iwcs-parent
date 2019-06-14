/*
 * GridFliter.java
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
 * <p>用于定义前段grid 筛选信息</p>
 */

public class GridFilterInfo {
    /**
     * 筛选的列名称
     */
    private String filterKey;
    /**
     * 筛选关键字
     */
    private String filterValue;

    public GridFilterInfo() {
    }

    public GridFilterInfo(String filterKey, String filterValue) {
        this.filterKey = filterKey;
        this.filterValue = filterValue;
    }

    public String getFilterKey() {

        return filterKey;
    }

    public void setFilterKey(String filterKey) {

        this.filterKey = filterKey;
    }

    public String getFilterValue() {

        return filterValue;
    }

    public void setFilterValue(String filterValue) {

        this.filterValue = filterValue;
    }


}
