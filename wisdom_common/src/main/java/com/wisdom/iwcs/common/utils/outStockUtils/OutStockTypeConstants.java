package com.wisdom.iwcs.common.utils.outStockUtils;


/**
 * 出库单配置类型，如配车单等
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/19 9:47
 */
public class OutStockTypeConstants {

    /**
     * 正常出库
     */
    public static final String NORMAL_OUT = "normal";

    /**
     * 异常调整出库
     */
    public static final String EXCEPTION_OUT = "exception_adjust";

    /**
     * 按数量出库
     */
    public static final String OUT_BY_NUM = "0";

    /**
     * 按条码出库
     */
    public static final String OUT_BY_SN = "1";


}
