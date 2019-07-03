package com.wisdom.iwcs.common.utils;

/**
 * 任务静态常量
 * @Author george
 * @Date 2019/7/3 9:57
 */
public class TaskConstants {

    /**
     * 任务类型
     */
    public static final class taskCodeType {
        public final static String PLAUTOWBCALLPOD = "plAutoWbCallPod";
        public final static String PLBUFSUPPLY = "plBufSupply";
        public final static String PLTOAGING = "plToAging";
        public final static String AGINGTOQUAINSP = "agingToQuaInsp";
        public final static String QUAINSPTOELVBUF = "quaInspToElvBuf";
    }

    /**
     * 任务状态值
     */
    public static final class taskStatus {
        /**
         * 任务初始化，未下发
         */
        public final static String  NOT_ISSUED = "0";
        /**
         * 任务已下发
         */
        public final static String  ISSUED= "1";
        /**
         * 任务完成
         */
        public final static String FINISHED = "9";
    }
}
