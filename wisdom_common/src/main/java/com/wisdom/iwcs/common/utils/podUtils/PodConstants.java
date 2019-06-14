package com.wisdom.iwcs.common.utils.podUtils;


/**
 * 货架相关常量
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/19 9:47
 */
public class PodConstants {
    /**
     * 仓位载货状态相关
     */
    public static final class BinCargoCapacityStatus {
        /**
         * 空仓
         */
        public static final String EMPTY_BIN = "0";
        /**
         * 载货仓位
         */
        public static final String NOT_FULL_BIN = "1";
        /**
         * 满载仓位
         */
        public static final String FULL_BIN = "2";
    }
}
