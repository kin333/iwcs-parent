package com.wisdom.rabbitmq.consumerAction;

/**
 * RabbitMQ消费者的消费动作
 * @author han
 */
public interface IConsumerAction {
    /**
     * 具体消费动作
     */
    void action(String message);

}
