package com.wisdom.iwcs.common.utils;

/**
 * @author cecilia.yang
 * @version <br>
 * <p>静态常量</p>
 */

public class InterfaceLogConstants {


    public static final class InterfaceCode {
        public final static String SYNC_NOTIFY_CODE = "syncNotify";
        public final static String OUT_POD_CODE = "getOutPod";
        public final static String RETURN_POD_CODE = "returnPod";
        public final static String NOTIFY_CLIENT_CODE = "notifyClient";
        public final static String POD_RETURN_STRA_CODE = "podReturnArea";
        public final static String NOTIFY_POD_ARR_CODE = "notifyPodArr";
        public final static String END_TASK_CODE = "endTask";
        public final static String ROTATE_POD_CODE = "rotatePod";
        public final static String CALL_BY_BINCODE = "callOutByBincode";
        public final static String CALL_BY_POD_CODE = "callOutByPodCode";
        public final static String INIT_POD_CODE = "initPod";
        public final static String CONFIG_RELATION_CODE = "configMapCodeAndAreaCodeRelation";
        public final static String MOVE_POD_CODE = "moveByBincode";
        public final static String GEN_MOVE_TASK_BY_POD_CODE = "genMoveTaskByPod";
        public final static String RETURN_BY_BINCODE = "returnByBincode";
        public final static String RETURN_BY_POD_CODE = "returnByPodCode";
        public final static String OUT_BOUND_CODE = "outBound";
        public final static String OUT_STOCK_CALL_CODE = "outStockCall";
        public final static String STOCK_ADJUST_CODE = "stockAdjust";
        public final static String MANUAL_UPDATE_POD_LOCK_CODE = "manualUpdatePodLock";
        public final static String GET_FREE_WB_INFO_CODE = "getFreeWbInfo";
        public final static String SHOW_POD_CODE = "showPodInfo";
        public final static String GEN_AGV_SCHEDULING_TASK_CODE = "genAgvSchedulingTask";
        public final static String FREE_ROBOT = "freeRobot";
        public final static String CONTINUE_TASK_CODE = "continueTask";
        public final static String CANCEL_TASK_CODE = "cancelTask";
        public final static String Bind_And_Berth_CODE = "bindPodAndBerth";
        public final static String TASK_CREATE = "taskCreate";
        public final static String APPLY_RESOURCE = "applyResource";
        public final static String EXCUTE_TASK = "excuteTask";
        public final static String RELEASE_RESOURCE = "releaseResource";
        public final static String TASK_NOTIFY = "/iwcs/taskNotify";
        public final static String NOTIFY_TASK_INFO="notifyTaskInfo";
        public final static String NOTIFY_EXCUTE_RESULT_INFO = "NotifyExcuteResultInfo";

        public final static String SAVE_INSTOCK = "saveInStock";
        public final static String SELECT_WHAREA_LIST = "selectWhAreaList";
        public final static String GET_ALLTORAGE_INFO = "getAlltorageInfo";
        public final static String SUPPLY_UNLOADWB_NOTIFY = "supplyUnloadWbNotify";
        public final static String SUPPLY_LOAD_NUM = "supplyLoadNum";
        public final static String START_SUPLLY_AND_RECYLE = "startSupllyAndRecyle";
        public final static String MODIFY_POD_STATUS = "modifyPodStatus";
        public final static String START_RECYLE = "startRecyle";
        public final static String EMPTY_RECYLE_NUM = "emptyRecyleNum";
        public final static String CHECK_SUCCESS = "checkSuccess";
        public final static String SUPLLY_AND_RECYLE_RESULT = "supllyAndRecyleResult";
        public final static String SUPLLY_UNLOAD = "supllyUnload";
        public final static String ROLLER_NOTIFY = "rollerNotify";
        public final static String CARRY_NOTIFY = "carryNotify";
        public final static String CONWAIT_TO_DESTWB = "conWaitToDestWb";
        public final static String CREATE_TASK = "createTask";
        public final static String CANCEL_TASK = "cancelTask";
        public final static String PUBLIC_CANCEL_TASK = "publicCancelTask";
        public final static String CHANGE_AGV = "changeAgv";

        public final static String ARRIVE_SRC_WB = "arriveSrcWb";
        public final static String LEAVE_SRC_WB = "leaveSrcWb";
        public final static String ARRIVE_DEST_WB_WAIT_PORT = "arriveDestWbWaitPort";
        public final static String ARRIVE_DEST_WB = "arriveDestWb";

        public final static String PUBLIC_TASK_CREATE = "publicTaskCreate";

    }

    public static final class InterfaceName {

        public final static String SYNC_NOTIFY_NAME = "海康同步基础信息";
        public final static String OUT_POD_NAME = "货架出库";
        public final static String RETURN_POD_NAME = "货架回库";
        public final static String NOTIFY_CLIENT_NAME = "TPS通知客户端";
        public final static String POD_RETURN_STRA_NAME = "指定货架回库策略";
        public final static String NOTIFY_POD_ARR_NAME = "通知货架返程回储位";
        public final static String END_TASK_NAME = "一键结束";
        public final static String ROTATE_POD_NAME = "货架旋转";
        public final static String CALL_BY_POD_NAME = "按指定货架呼叫";
        public final static String INIT_POD_NAME = "货架初始化";
        public final static String CONFIG_RELATION_NAME = "配置区域地图关系";
        public final static String MOVE_POD_NAME = "移动货架";
        public final static String GEN_MOVE_TASK_BY_POD_NAME = "货架变更存储区";
        public final static String RETURN_BY_BINCODE_NAME = "指定货架回库";
        public final static String OUT_BOUND_NAME = "出库确认";
        public final static String OUT_STOCK_CALL_NAME = "出库呼叫";
        public final static String STOCK_ADJUST_NAME = "库存调整";
        public final static String MANUAL_UPDATE_POD_LOCK_NAME = "手动上锁/解锁货架";
        public final static String GET_FREE_WB_INFO_NAME = "拉取空闲点位信息";
        public final static String SHOW_POD_NAME = "获取货架信息";
        public final static String GEN_AGV_SCHEDULING_TASK_NAME = "生成任务单";
        public final static String CONTINUE_TASK_NAME = "继续执行任务";
        public final static String FREE_ROBOT_NAME = "释放AGV";
        public final static String NOTIFY_EXCUTE_RESULT_INFO_NAME = "自动门开门到位/开始关闭";
        public final static String CANCEL_TASK_NAME = "取消任务";
        public final static String Bind_And_Berth_NAME = "货架与位置绑定、解绑";
        public final static String TASK_CREATE_DESC = "浪潮搬运任务创建";
        public final static String MOVE_TASK_CREATE = "美国浪潮移载式AGV搬运任务创建";
        public final static String MOVE_TASK_CREAT = "超越移载式AGV搬运任务创建";
        public final static String PToP_TASK_CREATE = "超越点到点移载式AGV搬运任务创建";
        public final static String GENERAL_INTERFACE = "通用模板接口调用";
        public final static String APPLY_RESOURCE_DESC = "小车到达检验点回调";
        public final static String EXCUTE_TASK_DESC = "小车送货架出电梯后回调";
        public final static String RELEASE_RESOURCE_DESC = "agv接货架出电梯时回调";
        public final static String TASK_NOTIFY_DESC = "小车移动的回调接口";
        public final static String NOTIFY_TASK_INFO_DESC="agv请求自动门开门/关门";

        public final static String SAVE_INSTOCK_DESC = "更新货架空满";
        public final static String SELECT_WHAREA_LIST_DESC = "拉取库区列表";
        public final static String GET_ALLTORAGE_INFO_DESC = "提供PDA查询储位信息";
        public final static String SUPPLY_UNLOADWB_NOTIFY_DESC = "通知AGV接料点目的地";
        public final static String SUPPLY_LOAD_NUM_DESC = "通知AGV上料数量";
        public final static String START_SUPLLY_AND_RECYLE_DESC = "接料点通知供料及回收空框信息";
        public final static String START_SUPLLY_AND_RECYLE_DESCS = "通知小车是否滚动";
        public final static String MODIFY_POD_STATUS_DESCS = "修改货架状态";
        public final static String START_RECYLE_DESC = "通知可出空料框";
        public final static String EMPTY_RECYLE_NUM_DESC = "通知上空框数量";
        public final static String CHECK_SUCCESS_DESC = "通知小车可出机台";
        public final static String SUPLLY_AND_RECYLE_RESULT_DESC = "上报已下料数量及已接收空框数量";
        public final static String SUPLLY_UNLOAD_DESC = "通知AGV是否可以离开";
        public final static String ROLLER_NOTIFY_DESC = "滚筒Agv回调";
        public final static String CARRY_NOTIFY_DESC = "点到点搬运Agv回调";
        public final static String CONWAIT_TO_DESTWB_DESC = "通知Agv可从等待点前往终点";
        public final static String CREATE_TASK_DESC = "创建点到点任务";

        public final static String CANCEL_TASK_DESC = "取消滚筒任务";
        public final static String PUBLIC_CANCEL_TASK_DESC = "顶升式通用的取消任务";
        public final static String CHANGE_AGV_DESC = "滚筒换车任务";


        public final static String ARRIVE_SRC_WB_DESC = "AGV到达起点";
        public final static String LEAVE_SRC_WB_DESC = "AGV离开起点";
        public final static String ARRIVE_DEST_WB_WAIT_PORT_DESC = "AGV到达（机械臂）等待点";
        public final static String ARRIVE_DEST_WB_DESC = "AGV到达终点";
        public final static String PUBLIC_TASK_CREATE_DECS = "通用的创建主任务接口";
        public final static String CHANGCHUN_PToP_TASK_CREATE = "长春手持";
    }

    public static final class SrcClientCode {

        public final static String SRC_HIK = "HIK";
        public final static String SRC_IWCS = "IWCS";
        public final static String SRC_OUTER = "OUTER";
        public final static String SRC_INSUPR = "INSUPR";
        public final static String SRC_MES = "MES";
        public final static String SRC_PDA = "PDA";
        public final static String SCADA = "SCADA";
    }


}
