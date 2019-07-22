package com.wisdom.iwcs.common.utils;

/**
 * 任务静态常量
 * @Author george
 * @Date 2019/7/3 9:57
 */
public class TaskConstants {

    /**
     * 主任务类型
     */
    public static final class taskCodeType {
        /**
         * 自动工作台点位呼叫空货架
         */
        public final static String PLAUTOWBCALLPOD = "plAutoWbCallPod";
        /**
         * 自动补充产线空货架缓存区
         */
        public final static String PLBUFSUPPLY = "plBufSupply";
        /**
         * 产线去老化区搬运
         */
        public final static String PLTOAGING = "plToAging";
        /**
         * 老化区前往检验点
         */
        public final static String AGINGTOQUAINSP = "agingToQuaInsp";
        /**
         * 点到点
         */
        public final static String PTOP = "pTop";
        /**
         * 自动检验缓冲区去检验点
         */
        public final static String QUABUFTOQUA = "quaBufToQua";
        public final static String QUAINSPTOELVBUF = "quaInspToElvBuf";
        /**
         * 点到点（暂时与上面的点到点一个意思）
         */
        public final static String PTOPWITHOUTPODCHECK = "pTopWithoutPodCheck";
    }

    /**
     * 点到点任务子类型
     */
    public static final class pTopTaskSubTaskTypeConstants {
        /**
         * 初始化入库
         */
        public final static String INIT_STORAGE= "init_storage";

    }

    /**
     * 主任务状态值
     */
    public static final class mainTaskStatus {
        /**
         * 任务初始化，未下发
         */
        public final static String  MAIN_NOT_ISSUED = "0";
        /**
         * 任务正在执行
         */
        public final static String  MAIN_ISSUED= "1";
        /**
         * 任务完成
         */
        public final static String MAIN_FINISHED = "9";
    }

    /**
     * 次任务状态值
     */
    public static final class subTaskStatus {
        /**
         * 任务初始化，未下发
         */
        public final static String  SUB_NOT_ISSUED = "0";
        /**
         * 任务正在执行
         */
        public final static String  SUB_ISSUED= "1";
        /**
         * 任务完成
         */
        public final static String SUB_FINISHED = "9";
    }

    /**
     * 子任务下发状态
     */
    public static final class subTaskSendStatus {
        /**
         * 任务初始化，未下发
         */
        public final static String INIT = "0";
        /**
         * 已下发worker
         */
        public final static String HAS_SEND = "1";
    }

    /**
     * 条件状态
     */
    public static final class metStatus {
        /**
         * 已符合
         */
        public final static String CONFORM = "1";
        /**
         * 不符合
         */
        public final static String NOT_CONFORM = "0";

    }
    /**
     * 手动锁定锁定源
     */
    public final static String MANUAL_LOCK = "manual_lock";
    /**
     * 实际工作状态
     */
    public static final class workTaskStatus {
        /**
         * 未开始
         */
        public final static String READY = "0";
        /**
         * 已开始
         */
        public final static String START = "1";
        /**
         * 已结束
         */
        public final static String END = "2";

    }

    /**
     * 主任务是否循环调用
     */
    public static final class loopExec {
        /**
         * 不循环调用
         */
        public final static String NOT_LOOP = "0";
        /**
         * 循环调用
         */
        public final static String LOOP = "1";
    }

    public static final class resultStatus {
        /**
         * 成功
         */
        public final static String SUCCESS = "0";
        /**
         * 失败
         */
        public final static String FAILURE = "1";
    }

    /**
     * 操作状态
     */
    public static final class operationStatus {
        /**
         * 创建任务
         */
        public final static String CREATE_TASK = "create_task";
        /**
         * 开始执行任务
         */
        public final static String START_TASK = "start_task";
        /**
         * 前置条件全部满足
         */
        public final static String PRE_CONDITION_SUCCESS = "pre_condition_success";
        /**
         * 前置条件不满足
         */
        public final static String PRE_CONDITION_FAILURE = "pre_condition_failure";
        /**
         * 下发成功
         */
        public final static String SEND_SUCCESS = "send_success";
        /**
         * 下发失败
         */
        public final static String SEND_FAILURE = "send_failure";
        /**
         * 后置条件全部满足
         */
        public final static String POST_CONDITION_SUCCESS = "post_condition_success";
        /**
         * 后置条件不满足
         */
        public final static String POST_CONDITION_FAILURE = "post_condition_failure";
        /**
         * 任务开始时的回调
         */
        public final static String CALLBACK_START = "callback_start";
        /**
         * 走出储位时回调
         */
        public final static String CALLBACK_LEAVE = "callback_leave";
        /**
         * 任务结束时回调
         */
        public final static String CALLBACK_END = "callback_end";
    }

    /**
     * 事件类型
     */
    public static final class resourceType {
        /**
         * 释放货架
         */
        public final static String POD_RELEASE = "podRelease";
        /**
         * 释放储位
         */
        public final static String POS_RELEASE = "posRelease";
    }
}
