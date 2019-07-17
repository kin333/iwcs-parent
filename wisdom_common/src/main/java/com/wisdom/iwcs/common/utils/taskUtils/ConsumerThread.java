package com.wisdom.iwcs.common.utils.taskUtils;

import com.rabbitmq.client.*;
import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.common.utils.constant.RabbitMQConstants;
import com.wisdom.rabbitmq.consumerAction.IConsumerAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerThread.class);

    private final Object lock = new Object();

    /**
     *  队列名称
     */
    private String queueName;
    /**
     * routeKey
     */
    private String routeKey;

    /**
     * 消费动作
     */
    private IConsumerAction consumerAction;

    public ConsumerThread(String queueName, String routeKey) {
        this.queueName = queueName;
        this.routeKey = routeKey;
    }

    public ConsumerThread(String queueName, String routeKey, IConsumerAction consumerAction) {
        this.queueName = queueName;
        this.routeKey = routeKey;
        this.consumerAction = consumerAction;
    }

    public ConsumerThread() {
    }

    @Override
    public void run() {
        while (true){
            try {
                createNewEvent();
                synchronized (lock) {
                    lock.wait();
                }
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 建立新的消息队列并绑定交换机
     * @return 连接key,删除连接时使用
     */
    private void createNewEvent() {
        Connection connection = RabbitMQUtil.getConnection();
        try {
            Channel channel = connection.createChannel();
            String queue = channel.queueDeclare(queueName, false, false, true, null).getQueue();
            logger.debug("建立消息队列成功:" + queue);
            channel.queueBind(queue, RabbitMQConstants.EXCHANGE_A, routeKey);
            logger.debug("交换机与消息队列绑定成功:" + queue);
//            channel.basicQos(1);

            Consumer consumer = new DefaultConsumer(RabbitMQUtil.getConnection().createChannel()) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    logger.info("队列名称:{} routeKey:{} 信息:{}", queueName, envelope.getRoutingKey() , message);
                    consumerAction.action(message);
                    if (message.contains(RabbitMQConstants.END_LOGO)){
                        logger.info("{}队列的连接将被关闭: {}", queueName, consumerTag);
                        channel.basicCancel(consumerTag);
//                        channel.queuePurge(queueName);
                        try {
                            channel.close();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        }
                        synchronized (lock) {
                            lock.notifyAll();
                        }
                    }
                }
            };

            String consumerTag = channel.basicConsume(queueName, true, consumer);
            logger.info("Consume with tag: {}", consumerTag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
