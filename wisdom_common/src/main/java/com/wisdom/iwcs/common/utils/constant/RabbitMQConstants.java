package com.wisdom.iwcs.common.utils.constant;

/**
 * 消息队列的常量
 */
public class RabbitMQConstants {

    /**
     * echange-A
     */
    public static final String EXCHANGE_A = "iwcs_exchange_A";
    /**
     * queue-A
     */
    public static final String QUEUE_A = "iwcs_queue_A_Test";
    /**
     * 消息日志的队列名称
     */
    public static final String TASK_LOG_QUEUE = "task_log_queue";
    /**
     * 消息日志的routeKey
     */
    public static final String ROUTEKEY_TASK_LOG = "agv.task.taskLog";
    /**
     * 节点动作 的routeKey
     */
    public static final String ROUTEKEY_NODE_ACTION = "agv.node.action";
    /**
     * 消息队列关闭的结束标记
     */
    public static final String END_LOGO = "OK";
}
