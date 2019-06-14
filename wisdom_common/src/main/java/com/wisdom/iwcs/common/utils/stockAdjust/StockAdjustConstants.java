package com.wisdom.iwcs.common.utils.stockAdjust;

/**
 * 库存调整常量
 *
 * @Author: cecilia.yang
 * @Date: 2019/3/21 16:14
 */
public class StockAdjustConstants {
    /**
     * 调整类型
     */
    public static final class AdjustType {
        /**
         * 直接加库存
         */
        public static final String ADJUST_ADD_STOCK = "A";
        /**
         * 直接减库存
         */
        public static final String ADJUST_DELETE_STOCK = "D";
        /**
         * 条码替换
         */
        public static final String ADJUST_REPLACE_SN = "R";
        /**
         * 库存部分移动
         */
        public static final String ADJUST_MOVE_POD = "M";
        /**
         * 更换库存属性
         */
        public static final String ADJUST_CHANGE_CHARACTER = "C";


    }


}
