/*
 * Base64Utils.java
 *
 * Created Date: 2016年6月1日
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

import org.apache.commons.codec.binary.Base64;

import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 * @author jennal
 * @version <br>
 * <p>类的描述</p>
 */

public class Base64Utils {

    public static byte[] decodeStr(String encodeStr) {
        Base64 base64 = new Base64();
        byte[] b = Base64.decodeBase64(encodeStr);
        return b;

    }

    public static boolean generateImage(String imgStr, String path) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;

        try {
            //Base64解码
            byte[] b = decodeStr(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
