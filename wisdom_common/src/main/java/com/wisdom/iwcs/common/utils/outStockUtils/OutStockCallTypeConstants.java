package com.wisdom.iwcs.common.utils.outStockUtils;


/**
 * 出库类型，如订单出库，不指定订单出库
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/19 9:47
 */
public class OutStockCallTypeConstants {

    /**
     * 指定订单明细呼叫
     */
    public static final String ORDER_CALL = "1";

    /**
     * 无订单呼叫
     */
    public static final String NOT_ORDER_CALL = "2";
    /**
     * 按照订单号，整单呼叫
     */
    public static final String WHOLE_ORDER_CALL = "3";

}
