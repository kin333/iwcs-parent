package com.wisdom.iwcs.common.utils.taskUtils;

import com.rabbitmq.client.*;
import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.common.utils.constant.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.wisdom.iwcs.common.utils.constant.RabbitMQConstants.TASK_LOG_QUEUE;

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
        Channel channel;
        try {
            channel = connection.createChannel();
            String queue = channel.queueDeclare(queueName, false, false, true, null).getQueue();
            logger.debug("建立消息队列成功:" + queue);
            channel.queueBind(queue, RabbitMQConstants.EXCHANGE_A, routeKey);
            logger.debug("交换机与消息队列绑定成功:" + queue);
            //每次仅处理一条消息
            channel.basicQos(1);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //返回确认状态
                    if (TASK_LOG_QUEUE.equals(queueName)) {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                    String message = new String(body, "UTF-8");
                    logger.info("队列名称:{} routeKey:{} 信息:{}", queueName, envelope.getRoutingKey() , message);
                    //调用消费者活动
                    consumerAction.action(new ConsumerActionInfo(message, queueName));
                    if (!queueName.contains("_")) {
                        return;
                    }
                    String subTaskNum = queueName.split("_")[1];
                    //当队列消息含有结束标识,并且含有启动这个子任务的子任务号时,则认为这个子任务已经执行完了,可以关闭这个消息队列了
                    if (message.contains(RabbitMQConstants.END_LOGO) && message.contains(subTaskNum)){
                        logger.info("{}队列的连接将被关闭: {}, routeKey:{}", queueName, consumerTag, envelope.getRoutingKey());
                        channel.basicCancel(consumerTag);
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
            boolean autoAck = true;
            if (TASK_LOG_QUEUE.equals(queueName)) {
                //false是取消自动应答机制,开启手动应答机制
                autoAck = false;
            }
            String consumerTag = channel.basicConsume(queueName, autoAck, consumer);
            logger.info("Consume with tag: {}", consumerTag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
