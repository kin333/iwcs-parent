package com.wisdom.iwcs.common.utils.taskUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 消息队列的消费者向消费者动作(action方法)传输信息类
 */
@Getter
@Setter
public class ConsumerActionInfo {
    /**
     * 接受的json信息
     */
    private String message;
    /**
     * 队列的名称
     */
    private String queueName;

    public ConsumerActionInfo(String message, String queueName) {
        this.message = message;
        this.queueName = queueName;
    }

    public ConsumerActionInfo() {
    }
}
