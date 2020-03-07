package com.wisdom.iwcs.common.utils;

/**
 * 日志表常量
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/27 10:14
 */
public class DictionaryConstants {

    /**
     * 字典分类常量
     */
    public static final class DictionaryTypeConstants {
        /**
         * 海康参数分组
         */
        public static final String HIK_PARAM = "HIK_PARAM";

        /**
         * 任务优先级分组
         */
        public static final String TASK_PRI = "TASK_PRI";
        /**
         * 出库参数
         */
        public static final String OUTSTOCK_PARAM = "OUTSTOCK_PARAM";

    }

    /**
     * 字典参数名称常量
     */
    public static final class DictionaryNameConstants {
        /**
         * 提交拣货
         */
        public static final String HIK_PRE_PICK = "prePick";
        /**
         * 入库优先级
         */
        public static final String PRI_IN = "instock";
        /**
         * 出库优先级
         */
        public static final String PRI_OUT = "outstock";
        /**
         * 盘点优先级
         */
        public static final String PRI_INV = "inventory";
        /**
         * 出库补充当前选择
         */
        public static final String OUTSTOCK_CURRENT_SUPPLY_CHOICE = "currentSupplyChoice";

        /**
         * 需要迁移的表
         */
        public static final String MIGRATIONTABLE = "MIGRATIONTABLE";

        /**
         * 出库补充总方案
         */
        public static final String OUTSTOCK_TOTAL_SUPPLY_CHOICE_A = "directCalculateFreePod";
        public static final String OUTSTOCK_TOTAL_SUPPLY_CHOICE_B = "reGlobalCalculateOutStockMissingPod";


    }

}
