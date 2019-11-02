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

        /**
         * 测试线区
         */
        public final static String TESTLINEAREA = "testLineArea";
        /**
         * 人工插线区
         */
        public final static String WOKPWAREA = "wokpwArea";

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
         * 老化缓存区
         */
        public final static String AGINGCACHEAREA = "agingCacheArea";

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
         * 线体工作区
         */
        public final static String LINEWORKAREA = "lineWorkArea";

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
         * 超越初始化入库
         */
        public final static String TASK_LEAVE_POINT_CHAO = "taskLeavePointChao";

        /**
         * 任务完成
         */
        public final static String TASK_FINISHED = "taskFinished";
        /**
         * 到达检查点
         */
        public final static String APPLY_RESOURCE = "applyResource";

        /**
         * 滚筒AGV开始移动
         */
        public final static String MOVE_START = "moveStart";
        /**
         * 滚筒AGV到达终点
         */
        public final static String MOVE_END = "moveEnd";
        /**
         * 滚筒AGV开始滚动
         */
        public final static String ROLL_START = "rollStart";
        /**
         * 滚筒AGV结束滚动
         */
        public final static String ROLL_END = "rollEnd";
        /**
         * 到达机械臂等待点(起点有等待点)
         */
        public final static String ARRIVE_START_WAIT = "arrivedStartWait";
        /**
         * 到达机械臂等待点(终点有等待点)
         */
        public final static String ARRIVE_END_WAIT = "arrivedEndWait";
        /**
         * AGV举升完成
         */
        public final static String LIFT_POD_END = "liftPodEnd";
        /**
         * AGV放下货架后离开储位
         */
        public final static String DOWN_POD_AND_LEAVE = "downPodAndLeave";
        /**
         * 进围栏到达围栏外等待点
         */
        public final static String ENTER_ARRIVED_OUT_WAIT_POINT = "enterArrivedOutWaitPoint";
        /**
         * 进围栏到达围栏内等待点
         */
        public final static String ENTER_ARRIVED_IN_WAIT_POINT = "enterArrivedInWaitPoint";
        /**
         * 出围栏到达围栏内等待点
         */
        public final static String COME_ARRIVED_IN_WAIT_POINT = "comeArrivedInWaitPoint";
        /**
         * 出围栏到达围栏外等待点
         */
        public final static String COME_ARRIVED_OUT_WAIT_POINT = "comeArrivedOutWaitPoint";

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

    /**
     * 超越 接供料
     */
    public static final class SupllyNodeType {
        /**
         * 供料
         */
        public final static String RECEIVE_TYPE = "1";
        /**
         * 下料
         */
        public final static String SEND_TYPE = "2";
        /**
         * 回收
         */
        public final static String RECOVERY_TYPE = "3";
        /**
         * 单独回收空料箱
         */
        public final static String RECYLE_TYPE = "4";
    }
}
