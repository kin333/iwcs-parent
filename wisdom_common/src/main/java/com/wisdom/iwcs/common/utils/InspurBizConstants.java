package com.wisdom.iwcs.common.utils;

/**
 * 济南浪潮 业务常量
 * @Author george
 * @Date 2019/7/4 19:21
 */
public class InspurBizConstants {

    /**
     * 作业区域(如老化区、检验区)
     */
    public static final class OperateAreaCodeConstants {

        /**
         * 老化区
         */
        public final static String AGINGREA = "agingArea";
        /**
         * 线体区
         */
        public final static String LINEAREA = "lineArea";
        /**
         * 维修区
         */
        public final static String REPAIRAREA = "repairArea";
        /**
         * 电梯区
         */
        public final static String ELEVATORAREA = "elevatorArea";

        /**
         * 检验区
         */
        public final static String QUAINSPAREA = "quaInspArea";

    }

    /**
     * 作业区域内分区
     */
    public static final class BizTypeConstants {
        /**
         * 老化区自动区
         */
        public final static String AGINGAREAAUTO = "agingAreaAuto";
        /**
         * 老化区手动区
         */
        public final static String AGINGAREAMANUAL = "agingAreaManual";

        /**
         * 检验区缓存区
         */
        public final static String QUAINSPCACHEAREA = "quaInspCacheArea";

        /**
         * 检验区工作区
         */
        public final static String QUAINSPWORKAREA = "quaInspWorkArea";

        /**
         * 线体缓存区
         */
        public final static String LINECACHEAREA = "lineCacheArea";

        /**
         * 电梯缓存区
         */
        public final static String ELEVATORCACHEAREA = "elevatorCacheArea";

    }

    /**
     * 作业区域内分区分点
     */
    public static final class BizSecondAreaCodeTypeConstants {

        /**
         * 线体区作业点类型自动
         */
        public final static String LINEAREAAUTOPOINT = "lineAreaAutoPoint";

        /**
         * 线体区作业点类型手动
         */
        public final static String LINEAREAMANUALPOINT = "lineAreaManualPoint";
    }

    /**
     * 老化区优先策略
     */
    public static final class AgingAreaPriorityProp {
        /**
         * 自动区优先
         */
        public final static String AUTO_FIRST = "autoAgingPosFirst ";
        /**
         * 手动区优先
         */
        public final static String MANUAL_FIRST = "manualAgingPosFirst ";


    }
}
