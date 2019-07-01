package com.wisdom.test;

import com.rabbitmq.client.*;
import com.wisdom.config.RabbitConfig;
import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMq消息中间件测试
 */
@RestController
@RequestMapping("api/test/RabbitMq")
public class RabbitMQTest {

    private final String QUEUE = "iwcs_queue";
    private final String EXCHANGE = RabbitConfig.EXCHANGE_A;
    private final String ROUTING_KEY = "iwcs_routingKey";

    /**
     * 测试: RabbitMq 使用测试
     */
    @GetMapping("/testRabbitMQ")
    public void testRabbitMQ() throws IOException, TimeoutException {
        //创建连接
        Connection connection = RabbitMQUtil.getConnection();

        //创建消息信道
        final Channel channel = connection.createChannel();

        //声明一个消息队列
        channel.queueDeclare(QUEUE, true, false, false, null);
        //将队列绑定到交换器，不需要额外的参数。
        RabbitMQUtil.bindExchage(channel, QUEUE, EXCHANGE, ROUTING_KEY);

        //收到消息之后应该做的事
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                try {
                    System.out.println("[" + QUEUE + "] Received '" + message);
                } finally {
                    System.out.println("[" + QUEUE + "] Done");
                    //确认收到的一条或多条消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        //5、监听队列
        /*
         true:表示自动确认，只要消息从队列中获取，无论消费者获取到消息后是否成功消费，都会认为消息已经成功消费
         false:表示手动确认，消费者获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，
                如果消费者一直没有反馈，那么该消息将一直处于不可用状态，并且服务器会认为该消费者已经挂掉，不会再给其
                发送消息，直到该消费者反馈。
        */
        // 取消自动ack
        //使用服务器生成的consumerTag启动非nolocal、非独占的消费者。
        channel.basicConsume(QUEUE, false, consumer);
    }
    /**
     * 测试: 删除交换机
     */
    @GetMapping("/testDeleteExcange")
    public void testDeleteExcange() throws IOException, TimeoutException {
        //创建连接
        Connection connection = RabbitMQUtil.getConnection();

        //创建消息信道
        final Channel channel = connection.createChannel();
        RabbitMQUtil.deleteExchange(channel, EXCHANGE);
    }
    /**
     * 测试: 删除队列
     */
    @GetMapping("/testDeleteQueue")
    public void testDeleteQueue() throws IOException, TimeoutException {
        //创建连接
        Connection connection = RabbitMQUtil.getConnection();

        //创建消息信道
        final Channel channel = connection.createChannel();
        RabbitMQUtil.deleteQueue(channel, QUEUE);
    }
    /**
     * 测试: 交换机与队列解绑(无 routingKey)
     */
    @GetMapping("/testunbind")
    public void testunbind() throws IOException, TimeoutException {
        //创建连接
        Connection connection = RabbitMQUtil.getConnection();

        //创建消息信道
        final Channel channel = connection.createChannel();
        RabbitMQUtil.unbindExchage(channel, QUEUE, EXCHANGE, "");
    }

    /**
     * 测试: 交换机与队列解绑
     */
    @GetMapping("/testunbind2")
    public void testunbind2() throws IOException, TimeoutException {
        //创建连接
        Connection connection = RabbitMQUtil.getConnection();

        //创建消息信道
        final Channel channel = connection.createChannel();
        AMQP.Queue.UnbindOk unbindOk = RabbitMQUtil.unbindExchage(channel, QUEUE, EXCHANGE, ROUTING_KEY);
        System.out.println(unbindOk);
    }
}
