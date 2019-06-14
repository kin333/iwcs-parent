/*
 * ASCValue.java
 *
 * Created Date: 2016年6月27日
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

import org.apache.commons.lang3.StringUtils;


/**
 * @author derrick.yang
 * @version <br>
 * <p>字符串asc码加密/p>
 */

public class ASCValue {
    /**
     * asc码加密
     *
     * @param str
     * @return
     */
    public static String SumStrAscii(String str) {
        String sum = "";
        if (StringUtils.isNotBlank(str)) {
            str = str.toUpperCase();
            byte[] bytestr = str.getBytes();
            for (int i = 0; i < bytestr.length; i++) {
                sum += Integer.toHexString(bytestr[i]);
            }
        }

        return sum;
    }

    public static String ascToString(String ascValue) {

        int len = ascValue.length();
        StringBuffer value = new StringBuffer();
        for (int i = 0; i < len; i++, i++) {
            //每次截取两个字符
            String asc = ascValue.substring(i, i + 2);
            //将16进制转换为10进制
            value.append((char) Integer.parseInt(asc, 16));
        }

        return value.toString();
    }

//	//测试
//	public static void main(String[] args) {
//		
//		String str = "1234567890a";
//		String asc = SumStrAscii(str);
//		System.out.println(asc);
//		
//		System.out.println(ascToString(asc));
//	}
}
