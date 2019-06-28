package com.wisdom.iwcs.common.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.wisdom.base.context.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ工具类
 *
 * 提示: 在获取消息通道之后
 *          如果需要创建消息队列,可使用 channel.queueDeclare(queue, true, false, false, null);
 *          如果需要将队列绑定到交换器中,可使用 channel.queueBind(queue, exchange, routingKey);
 *
 */
@Component
public class RabbitMQUtil {
    private static String host;
    private static Integer port;
    private static String username;
    private static String password;
    private static String virtualHost;

    @Autowired
    ApplicationProperties applicationProperties;

    /**
     * 利用@PostConstruct将yml中配置的值赋给本地的变量(static变量)
     */
    @PostConstruct
    public void getHost(){
        host = applicationProperties.getRabbitmq().getHost();
    }
    @PostConstruct
    public void getPort(){
        port = applicationProperties.getRabbitmq().getPort();
    }
    @PostConstruct
    public void getUsername(){
        username = applicationProperties.getRabbitmq().getUsername();
    }
    @PostConstruct
    public void getPassword(){
        password = applicationProperties.getRabbitmq().getPassword();
    }
    @PostConstruct
    public void getVirtualHost(){
        virtualHost = applicationProperties.getRabbitmq().getVirtualHost();
    }
    /**
     * 创建连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //1、定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2、设置服务器地址
        factory.setHost(host);
        //3、设置端口
        factory.setPort(port);
        //4、设置虚拟主机、用户名、密码
        factory.setVirtualHost(virtualHost);
        factory.setUsername(username);
        factory.setPassword(password);
        //5、通过连接工厂获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

    /**
     * 创建消息信道
     * @param connection
     * @return
     * @throws IOException
     */
    public static Channel createChannel(Connection connection) throws IOException {
        if (connection == null) {
            throw new NullPointerException("createChannel()方法参数不能为空");
        }
        return connection.createChannel();
    }


    /**
     * 在某个队列上注册消费者
     *
     * @param channel
     * @param consumer
     * @return 一个消费者与消息队列之间连接的标记, 用于取消连接
     * @throws IOException
     */
    public static String startConsume(Channel channel, String queue, Consumer consumer) throws IOException {
        if (queue == null ||channel == null || consumer == null) {
            throw new NullPointerException("startConsume()的参数不能为空!");
        }
        String consumerTag = channel.basicConsume(queue, consumer);
        return consumerTag;
    }

    /**
     * 在多个队列上注册一个消费者
     * @param channel
     * @param queues
     * @param consumer
     * @return 消费者与所有消息队列之间连接的标记, 用于取消连接
     * @throws IOException
     */
    public static List<String> startMoreConsume(Channel channel, String[] queues, Consumer consumer) throws IOException {
        if (queues == null) {
            throw new NullPointerException("startMoreConsume()的参数不能为空!");
        }
        List<String> queueList = Arrays.asList(queues);
        return startMoreConsume(channel, queueList, consumer);
    }

    /**
     * 在多个队列上注册一个消费者
     * @param channel
     * @param queues
     * @param consumer
     * @return
     * @throws IOException
     */
    public static List<String> startMoreConsume(Channel channel, List<String> queues, Consumer consumer) throws IOException {
        if (queues == null || channel == null || consumer == null) {
            throw new NullPointerException("startMoreConsume()的参数不能为空!");
        }
        List<String> consumerTags = new ArrayList<>();
        for (String queue : queues) {
            //在某个队列上注册消费者
            String consumerTag = channel.basicConsume(queue, consumer);
            consumerTags.add(consumerTag);
        }
        return consumerTags;
    }

    /**
     * 取消消费者与消息队列之间的连接
     * @param channel
     * @param consumerTag 消费者与消息队列之间连接的标记
     * @throws IOException
     */
    public static void cancelConsume(Channel channel, String consumerTag) throws IOException {
        if (channel == null || consumerTag == null) {
            throw new NullPointerException("cancelConsume()的参数不能为空!");
        }
        channel.basicCancel(consumerTag);
    }

    /**
     * 批量取消消费者与消息队列之间的连接
     * @param channel
     * @param consumerTags
     * @throws IOException
     */
    public static void cancelMoreConsume(Channel channel, String[] consumerTags) throws IOException {
        if (consumerTags == null) {
            throw new NullPointerException("startMoreConsume()的参数不能为空!");
        }
        List<String> consumerList = Arrays.asList(consumerTags);
        cancelMoreConsume(channel, consumerList);
    }

    /**
     * 批量取消消费者与消息队列之间的连接
     * @param channel
     * @param consumerTags
     * @throws IOException
     */
    public static void cancelMoreConsume(Channel channel, List<String> consumerTags) throws IOException {
        if (channel == null || consumerTags == null) {
            throw new NullPointerException("startMoreConsume()的参数不能为空!");
        }
        for (String consumerTag : consumerTags) {
            //取消消费者与某个消息队列之间的连接
            channel.basicCancel(consumerTag);
        }
    }
}