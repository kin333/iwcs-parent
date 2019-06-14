package com.wisdom.iwcs.common.utils.internal;

/**
 * TextUtils
 *
 * @author fionera
 * @date 2018/3/29 in zc-admin-backend
 */

public class NumberUtils {

    public static boolean isNull(Integer i) {
        return i == null;
    }

    public static boolean isZero(Integer i) {
        return isNull(i) || equals(i, 0);
    }

    public static boolean equals(Integer i1, Integer i2) {
        if (i1 == null || i2 == null) {
            return false;
        }
        return i1.equals(i2);
    }

    public static boolean equals(Integer i1, int i2) {
        if (i1 == null) {
            return false;
        }
        return i1.equals(i2);
    }
}
