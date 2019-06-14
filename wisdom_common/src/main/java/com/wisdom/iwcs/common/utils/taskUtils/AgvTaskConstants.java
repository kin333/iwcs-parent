package com.wisdom.iwcs.common.utils.taskUtils;


/**
 * AGV任务常量
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/19 9:47
 */
public class AgvTaskConstants {

    /**
     * 任务状态
     */
    public static final class AgvTaskStatusConstants {
        /**
         * 已创建
         */
        public static final String TASK_CREATED = "0";

        /**
         * 已结束
         */
        public static final String TASK_ENDED = "9";
    }


    /**
     * 小车动作类型，对应agv_task_log中的log_type
     */
    public static final class AgvActionConstants {
        /**
         * 到达工作台
         */
        public static final String ARRIVE_WORK_DESK = "1";

        /**
         * 到达排队区
         */
        public static final String ARRIVE_QUEUE_AREA = "2";
    }

    /**
     * 入库确认或出库确认后的小车动作类型
     */
    public static final class AgvAfterArrivalActionConstants {
        /**
         * 小车离开
         */
        public static final String AGV_LEAVE = "0";

        /**
         * 小车等待
         */
        public static final String AGV_WAIT = "1";
    }

    /**
     * 呼叫客户端类型
     */
    public static final class SrcClientType {
        /**
         * 手持呼叫
         */
        public static final String MOBILE_CALL = "0";

        /**
         * PC端呼叫，如IWCS前台页面
         */
        public static final String PC_CALL = "1";
        /**
         * 北向系统呼叫
         */
        public static final String NORTH_SYS_CALL = "2";

    }

    /**
     * 举升状态常量举升-0 放下-1 放下释放小车-2
     */
    public static final class LiftStatusConstants {

        public static final String AGV_LIFT = "0";

        public static final String AGV_DOWN = "1";

        public static final String AGV_FREE = "2";

    }


}
