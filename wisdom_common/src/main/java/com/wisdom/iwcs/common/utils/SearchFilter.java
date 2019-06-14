/*
 * SearchFilter.java
 *
 * Created Date: 2017年2月6日
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

public class SearchFilter {
    private String type;

    private String model;

    private String options;


    public SearchFilter() {
        super();
    }

    public SearchFilter(String type, String model, String options) {
        super();
        this.type = type;
        this.model = model;
        this.options = options;
    }


    public String getType() {

        return type;
    }


    public void setType(String type) {

        this.type = type;
    }


    public String getModel() {

        return model;
    }


    public void setModel(String model) {

        this.model = model;
    }


    public String getOptions() {

        return options;
    }


    public void setOptions(String options) {

        this.options = options;
    }


}
