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

        /**
         * 包装区
         */
        public final static String PAGEAREA = "pageArea";

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
        /**
         * 电梯检验区
         */
        public final static String ELEVATORCHECKAREA = "elevatorCheckArea";

        /**
         * 包装区缓存区前缀
         */
        public final static String PAGECACHEAREA = "pageCacheArea";
        /**
         * 包装区工作区
         */
        public final static String PAGEWORKAREA = "pageWorkArea";

        /**
         * 检查区
         */
        public final static String CHECKFIELD = "checkField";

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
        public final static String AUTO_FIRST = "autoAgingPosFirst";
        /**
         * 手动区优先
         */
        public final static String MANUAL_FIRST = "manualAgingPosFirst";


    }

    /**
     * 货架空满状态
     */
    public static final class PodInStockConstants {
        /**
         * 货架空
         */
        public final static Integer EMPTY_POD = 0;
        /**
         * 货架满
         */
        public final static Integer NOT_EMPTY_POD = 1;


    }

    public static final class InStock {
        /**
         * 无货
         */
        public final static String NO_GOODS = "0";
        /**
         * 有货
         */
        public final static String HAVE_GOODS = "1";
    }

    /**
     * 海康回调方法名
     */
    public static final class HikCallbackMethod {

        /**
         * 任务开始
         */
        public final static String TASK_START = "taskStart";
        /**
         * 走出储位
         */
        public final static String TASK_LEAVE_POINT = "taskLeavePoint";
        /**
         * 任务完成
         */
        public final static String TASK_FINISHED = "taskFinished";
        /**
         * 到达检查点
         */
        public final static String APPLY_RESOURCE = "applyResource";

    }

    /**
     * PLC 通讯消息类型
     */
    public static final class PlcMsgType{
        /**
         * 发送消息
         */
        public final static String PLC_SEND = "send";
        /**
         * 接收消息
         */
        public final static String PLC_RECEIVE = "receive";
    }

    /**
     * 梯控任务状态
     */
    public static final class EleControlTaskStatus{
        /**
         * 创建
         */
        public final static String ELE_TASK_INIT = "0";
        /**
         * 起始接驳
         */
        public final static String ENTER_ELE = "1";
        /**
         * 电梯运行
         */
        public final static String ELE_WORKING = "2";
        /**
         * 目标接驳
         */
        public final static String OUT_ELE = "3";
        /**
         * 结束9
         */
        public final static String ELE_TASK_END = "9";
    }

    /**
     * 电梯任务作业类型
     */
    public static final class EleControlTaskWorkType{
        /**
         * 上楼
         */
        public final static String ELE_UP = "up";
        /**
         * 下楼
         */
        public final static String ELE_DOWN = "down";
    }

    /**
     * 电梯任务 agv动作：送货架/接货架
     */
    public static final class EleControlTaskAgvAction{
        /**
         * 上楼
         */
        public final static String AGV_SEND = "send";
        /**
         * 下楼
         */
        public final static String AGV_RECEIVE = "receive";
    }
}
