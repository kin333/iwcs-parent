package com.wisdom.iwcs.common.utils;

/**
 * @author Jack Gao
 * @version <br>
 * <p>入库静态常量</p>
 */

public class InStockConstants {

    /**
     * 入库单据类型
     */
    public static final class instockOrderType {
        public final static String NORMALINSTOCK = "NORMAL_INSTOCK";
        public final static String ABNORMALINSTOCK = "ABNORMAL_INSTOCK";
    }

    /**
     * 入库单据来源
     */
    public static final class instockSrc {
        /**
         * 本地创建
         */
        public final static String LOCALCREAT = "0";
        /**
         * 上游同步
         */
        public final static String UPSYNCHRONOUS = "1";
    }

    /**
     * 入库单据状态
     */
    public static final class OrderStatusConstants {
        /**
         * 已创建
         */
        public static final String ORDER_CREATED_IN = "0";
        /**
         * 开始入库
         */
        public static final String ORDER_START_IN = "1";
        /**
         * 入库结束
         */
        public static final String ORDER_END_IN = "2";

    }

    /**
     * 入库方式
     */
    public static final class instockTypeConstants {
        /**
         * 按数量
         */
        public static final String NUM_IN = "0";
        /**
         * 按条码
         */
        public static final String SN_IN = "1";
    }

    /**
     * 库存冻结标识
     */
    public static final class freezeFlagConstants {
        /**
         * 未冻结
         */
        public static final String FREEZE_IN = "0";
        /**
         * 冻结
         */
        public static final String UNFREEZE_IN = "1";
    }

    /**
     * 按容器管理标识
     */
    public static final class ContainerFlagConstants {
        /**
         * 否
         */
        public static final String NOT_IN_CONTAINER = "0";
        /**
         * 是
         */
        public static final String IN_CONTAINER = "1";
    }

    /**
     * 入库循环呼叫标识
     */
    public static final class cycleCallConstants {
        /**
         * 否
         */
        public static final String NOT_CYCLECALL = "0";
        /**
         * 是
         */
        public static final String CYCLECALL = "1";
    }

}
