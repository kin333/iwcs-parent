/*
 * FileUtils。java
 *
 * Created Date: 2015年1月8日
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

import com.google.common.base.Joiner;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author cecilia.yang
 * @version <br>
 * <p>list工具类</p>
 */

public class ListUtils {

    /**
     * List转成字符串分割的list
     *
     * @param requestList
     * @return
     */
    public static String convertListToString(List<String> requestList) {
        String resultString = Joiner.on(",").join(requestList);
        return resultString;
    }

    /**
     * 逗号分割的String转list
     *
     * @param requestString
     * @return
     */
    public static List<String> convertStringToList(String requestString) {
        List<String> resultList = Arrays.asList(StringUtils.split(requestString, ","));
        return resultList;
    }


}
