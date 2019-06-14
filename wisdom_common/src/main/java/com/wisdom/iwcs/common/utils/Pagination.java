/*
 * IPincheService.java
 *
 * Created Date: 2015年8月13日
 *
 * Copyright (c) Centling Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 * Centling Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Centling Technologies Co., Ltd.
 */

package com.wisdom.iwcs.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author derrick.yang
 * @version <br>
 * <p>
 * 类的描述
 * </p>
 */

public class Pagination<T> implements Serializable {

    private static final long serialVersionUID = -4410699101276791102L;
    private List<T> rows;
    private int page;
    private int size = YZConstants.CommonConstants.PAGE_DEFAULT_SIZE;

    public Pagination() {
    }

    /**
     * @param rows
     * @param page
     * @param size
     */
    public Pagination(List<T> rows, int page, int size) {
        super();
        this.rows = rows;
        this.page = page;
        this.size = size;
    }

    public List<T> getRows() {

        return rows;
    }

    public void setRows(List<T> rows) {

        this.rows = rows;
    }


    public int getPage() {

        return page;
    }


    public void setPage(int page) {

        this.page = page;
    }


    public int getSize() {

        return size;
    }


    public void setSize(int size) {

        this.size = size;
    }

}