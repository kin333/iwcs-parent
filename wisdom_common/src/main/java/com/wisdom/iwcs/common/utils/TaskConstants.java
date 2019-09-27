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
         * 超越  产线呼叫搬离货架
         */
        public final static String PLTOWOKPW = "plToWokpw";
        /**
         * 超越 人工插线区去老化区
         */
        public final static String WOKPWTOAGING = "wokpwToAging";
        /**
         * 超越 检验区呼叫搬离货架
         */
        public final static String QUAHAULBACK = "quaHaulback";
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
        /**
         * 检验区到电梯缓存区
         */
        public final static String QUAINSPTOELVBUF = "quaInspToElvBuf";
        /**
         * 电梯缓存区到一楼包装线体缓存区
         */
        public final static String ELVBUFTOPACKBUF = "elvBufToPackBuf";
        /**
         * 一楼包装线体区呼叫满货货架
         */
        public final static String PACKWBCALLPOD = "packWbCallPod";
        /**
         * 一楼包装线区到线体缓存区/老化区
         */
        public final static String PACKTOPLORAGING = "packToPlorAging";
        /**
         * 初始化货架用（暂时与上面的点到点一个意思）
         */
        public final static String PTOPWITHOUTPODCHECK = "pTopWithoutPodCheck";
        /**
         * 自动产线供料、回收任务
         */
        public final static String SUPPLYANDRECYCLE = "supplyAndRecycle";
        /**
         * 回收空料箱任务
         */
        public final static String EMPTYRECYCLETASK = "emptyRecycleTask";

        /**
         * 美国浪潮点到点
         */
        public final static String US_PTOP = "USpTop";
        /**
         * 美国浪潮起点有等待点的点到点
         */
        public final static String PTOP_START_WAIT = "pTopStartWait";
        /**
         * 美国浪潮终点有等待点的点到点
         */
        public final static String PTOP_END_WAIT = "pTopEndWait";
        /**
         * 美国浪潮起点有两个等待点的点到点
         */
        public final static String PTOP_START_WAIT_TWO = "pTopStartWaitTwo";
        /**
         * 美国浪潮终点有两个等待点的点到点
         */
        public final static String PTOP_END_WAIT_TWO = "pTopEndWaitTwo";




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
     * 子任务类型
     */
    public static final class subTaskType {
        /**
         * 点到点
         */
        public final static String HIK_P2P = "hikp2p";
        /**
         * 电梯子任务
         */
        public final static String ELE_TASK = "eleTask";
        /**
         * 滚筒任务,前往目标点
         */
        public final static String ROLLER_MOVE = "roller_move";
        /**
         * 转动滚筒
         */
        public final static String ROLLER_CONTINUE = "roller_continue";
        /**
         * 释放滚筒任务
         */
        public final static String ROLLER_END = "roller_end";

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
        /**
         * 到达等待点
         */
        public final static String ARRIVE_WITE = "5";

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

    /**
     * 前置/后置条件名称
     */
    public static final class handlerName {
        /**
         * 检查电梯是否可以离开前置条件
         */
        public final static String CHECKELECANLEAVE = "checkEleCanLeaveHandler";
        /**
         * 检查电梯是否可以进入前置条件
         */
        public final static String CHECKELECANENTER = "checkEleCanEnterHandler";
        /**
         * 检验区检查空储位前置条件
         */
        public final static String CHECKFIELDEMPTYBERTH = "checkFieldEmptyBerthHandler";
        /**
         * 检查任务是否完成后置条件
         */
        public final static String CHECKWORKSTATUS = "checkWorkStatusHandler";
        /**
         * 线体工作台补充空货架 检查空货架前置条件
         */
        public final static String EMPTYPODFORPL = "emptyPodForPlHandler";
        /**
         * 缓存区补充空货架前置条件--锁定老化区的一个空货架
         */
        public final static String EMPTYPODLOCKFORPLCACHE = "emptyPodLockForPlCacheHandler";
        /**
         * 产线工作点去老化区前置条件---老化区有空储位并锁定一个
         */
        public final static String EMPTYPOSFORAGINGPOD = "emptyPosForAgingPodHandler";
        /**
         * 多任务时,子任务间保持(传递)货架信息的后置条件
         */
        public final static String KEEPPODCODE = "keepPodCodeHandler";
        /**
         * 检查并锁定电梯空闲缓存点
         */
        public final static String LOCKELEVATOREMPTYCACHE = "lockElevatorEmptyCacheHandler";
        /**
         * 一楼包装线体区呼叫满货货架前置条件-->是否有有货的货架
         */
        public final static String PACKAREA = "packAreaHandler";
        /**
         * 缓存区补充空货架前置条件--锁定缓存区的一个空储位
         */
        public final static String PLCACHEEMPTYPOSLOCK = "plCacheEmptyPosLockHandler";
        /**
         * 产线工作台空闲处理类
         */
        public final static String PLWBAVALIABLECON = "plWbAvaliableConHandler";
        /**
         *
         */
        public final static String QUABUFTOQUA = "quaBufToQuaHandler";
        /**
         *
         */
        public final static String QUAWBLOCK = "quaWbLockHandler";
        /**
         * 到达线体工作台任务后置条件
         */
        public final static String ENTERWORKLINE = "enterWorkLineHandler";
        /**
         * 离开线体工作台任务后置条件
         */
        public final static String LEAVEWORKLINE = "leaveWorkLineHandler";
        /**
         * 电梯缓存区查找有货货架
         */
        public final static String ELECACHEPODHANDLER = "eleCachePodHandler";
        /**
         * action检查处理器
         */
        public final static String ACTIONCHECKHANDLER = "actionCheckHandler";

    }

    /**
     * agv状态
     */
    public static final class agvTaskType {
        /**
         * 到达线体工作台
         */
        public final static String ENTER = "01";
        /**
         * 离开线体工作台
         */
        public final static String LEAVE = "02";
    }
    /**
     * Hik的任务状态
     */
    public static final class hikTaskStatus {
        /**
         * 发送异常0
         */
        public final static String SEND_ERROR = "发送异常";
        /**
         * 已创建1
         */
        public final static String CREATE = "已创建";
        /**
         * 正在执行2
         */
        public final static String RUNNING = "正在执行";
        /**
         * 取消完成5
         */
        public final static String CANCEL_FINISH = "取消完成";
        /**
         * 已结束9
         */
        public final static String END = "已结束";
    }

    public static final class yesOrNo {
        /**
         * 否
         */
        public final static String NO = "0";
        /**
         * 是
         */
        public final static String YES = "1";
    }

    /**
     * 通知电梯起始楼层source_floor或目标楼层dest_floor待检验
     */
    public static final class eleFloor {
        /**
         * 电梯起始楼层
         */
        public final static String SOURCE_FLOOR = "source_floor";
        /**
         * 目标楼层
         */
        public final static String DEST_FLOOR = "dest_floor";
    }

    /**
     * 点位别名
     */
    public static final class pointAlias {
        /**
         * 二楼包装体缓存区别名
         */
        public final static String PACK_CACHE_TWO = "A201";
        /**
         * 三楼包装体缓存区别名
         */
        public final static String PACK_CACHE_THREE = "A301";
    }
    /**
     * AGV释放标志
     */
    public static final class agvRelease {
        /**
         * 不释放
         */
        public final static String HOLD = "hold";
        /**
         * 释放
         */
        public final static String RELEASE = "release";
    }

    /**
     * 主任务执行顺序
     */
    public static final class mainTaskSeq {
        /**
         * 第一个执行
         */
        public final static Integer ONE = 1;
    }

    /**
     * 创建节点
     */
    public static final class createNode {
        /**
         * 点到点任务开始(到达起点)
         */
        public final static String PTOP_START = "pTop_start";
        /**
         * 点到点离开储位
         */
        public final static String PTOP_LEAVE = "pTop_leave";
        /**
         * 点到点任务结束(到达终点)
         */
        public final static String PTOP_END = "pTop_end";
        /**
         * 开始滚动
         */
        public final static String ROLLER_START = "roller_start";
        /**
         * 滚动完成
         */
        public final static String ROLLER_END = "roller_end";
        /**
         * 滚筒移动任务开始
         */
        public final static String ROLLER_MOVE_START = "roller_move_start";
        /**
         * 滚筒移动任务完成
         */
        public final static String ROLLER_MOVE_END = "roller_move_end";

    }

    /**
     * 创建状态
     */
    public static final class actionStatus {
        /**
         * 已创建
         */
        public final static String CREATE = "0";
        /**
         * 发送中
         */
        public final static String SENDING = "1";
        /**
         * 发送成功
         */
        public final static String SEND_SUCCESS = "2";
        /**
         * 发送失败
         */
        public final static String SEND_ERROR = "3";
        /**
         * 结果处理异常
         */
        public final static String RESULT_ERROR = "4";
    }
    /**
     * 执行模型,是否重复发送
     */
    public static final class executeMode {
        /**
         * 非必达
         */
        public final static String NO_PROMISE_ARRIVE = "0";
        /**
         * 必达
         */
        public final static String PROMISE_ARRIVE = "1";
    }

    /**
     * 关联点位类型
     */
    public static final class connectionPointType {
        /**
         * 围栏外等待点
         */
        public final static String OUT_WAIT_POINT = "8";
        /**
         * 围栏内等待点
         */
        public final static String IN_WAIT_POINT = "9";
    }
    /**
     * 主任务节点
     */
    public static final class bizProcess {
        /**
         * 任务创建
         */
        public final static String TASK_CREATE = "0";
        /**
         * 任务结束
         */
        public final static String TASK_END = "9";
        /**
         * 任务开始(到达起点)
         */
        public final static String TASK_START = "1";
        /**
         * 离开储位
         */
        public final static String TASK_LEAVE = "2";
        /**
         * 到达终点
         */
        public final static String TASK_ARRIVED_END = "6";
        /**
         * 进围栏到达围栏外等待点
         */
        public final static String ENTER_ARRIVED_OUT = "11";
        /**
         * 进围栏时可离开围栏外等待点
         */
        public final static String ENTER_ALLOW_LEAVE_OUT_WAIT = "12";
        /**
         * 进围栏到达围栏内等待点
         */
        public final static String ENTER_ARRIVED_IN = "13";
        /**
         * 进围栏可离开围栏内等待点
         */
        public final static String ENTER_ALLOW_LEAVE_IN_WAIT = "14";
        /**
         * 出围栏到达围栏内等待点
         */
        public final static String COME_ARRIVED_IN = "15";
        /**
         * 出围栏时可离开围栏内等待点
         */
        public final static String COME_ALLOW_LEAVE_IN_WAIT = "16";
        /**
         * 出围栏到达围栏外等待点
         */
        public final static String COME_ARRIVED_OUT = "17";
        /**
         * 出围栏时可离开围栏外等待点
         */
        public final static String COME_ALLOW_LEAVE_OUT_WAIT = "18";
    }

    /**
     * 主任务节点
     */
    public static final class doorStatus {
        /**
         * 开门成功
         */
        public final static String OPEN = "1";
        /**
         * 关门成功
         */
        public final static String CLOSE = "2";
    }
    /**
     * 通知AGV可离开机台状态
     */
    public static final class notifyAgvLeaveStatus {
        /**
         * 可离开供料点(上料点)
         */
        public final static String LEAVE_UP = "1";
        /**
         * 可离开接料点1（下料点1）
         */
        public final static String LEAVE_DOWN_FIRST = "2";
        /**
         * 可离开接料点2（下料点2）
         */
        public final static String LEAVE_DOWN_SECOND = "3";
        /**
         * 可离开空料箱回收上箱点（接料点）
         */
        public final static String LEAVE_UP_EMPTY = "4";
        /**
         * 可离开空箱下箱点
         */
        public final static String LEAVE_DOWN_EMPTY = "5";
    }


}
