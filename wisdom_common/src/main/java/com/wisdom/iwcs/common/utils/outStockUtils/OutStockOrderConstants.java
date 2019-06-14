package com.wisdom.iwcs.common.utils.outStockUtils;


/**
 * 出库单据常量
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/19 9:47
 */
public class OutStockOrderConstants {

    /**
     * 单据状态
     */
    public static final class OrderStatusConstants {
        /**
         * 已创建
         */
        public static final String ORDER_CREATED = "0";
        /**
         * 开始出库
         */
        public static final String ORDER_START_OUT = "1";
        /**
         * 出库结束
         */
        public static final String ORDER_END_OUT = "2";

    }

    /**
     * 单据类型 (订单类型)
     */
    public static final class OrderTypeConstants {
        /**
         * 虚拟出库，系统自行创建的出库单(虚拟单)
         */
        public static final String VIRTUAL_OUT_ORDER = "virtualOut";

        /**
         * 业务单
         */
        public static final String BIZ_ORDER = "bizOrder";


    }


}
