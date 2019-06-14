package com.wisdom.iwcs.common.utils.internal;

/**
 * TextUtils
 *
 * @author fionera
 * @date 2018/3/29 in zc-admin-backend
 */

public class TextUtils {

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }
}
