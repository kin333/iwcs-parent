package com.wisdom.iwcs.common.utils;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.constant.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ工具类
 *
 * 提示: 在获取消息通道之后
 *          如果需要创建交换机,可使用 channel.exchangeDeclare（exchange_name，"topic"）;
 *          如果需要创建消息队列,可使用 channel.queueDeclare(queue_name, true, false, false, null);是否持久化/是否排他/是否自动删除
 *          如果需要将队列绑定到交换器中,可使用 channel.queueBind(queue_name, exchange_name, routingKey);
 *
 */
@Component
public class RabbitMQUtil {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQUtil.class);

    private static String host;
    private static Integer port;
    private static String username;
    private static String password;
    private static String virtualHost;

    private static Connection connection;

    /**
     * 消息日志使用的channel
     */
    private static Channel taskLogChannel;

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
     */
    private static Connection getNewConnection() {
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
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 创建连接
     * @return
     */
    public static Connection getConnection() {
        if (connection == null) {
            connection = getNewConnection();
        }
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
     * 合并创建连接和创建消息信道
     */
    public static Channel createChannelDefault() {
        Connection connection = getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }


    /**
     * 在某个队列上注册消费者
     *
     * @param channel
     * @param consumer
     * @return 一个消费者与消息队列之间连接的标记, 用于取消连接
     * @throws IOException
     */
    public static String registConsumer(Channel channel, String topic, Consumer consumer) throws IOException {
        if (topic == null ||channel == null || consumer == null) {
            throw new NullPointerException("startConsume()的参数不能为空!");
        }
        return channel.basicConsume(topic, consumer);
    }

    /**
     * 在多个队列上注册一个消费者
     * @param channel
     * @param topics
     * @param consumer
     * @return 消费者与所有消息队列之间连接的标记, 用于取消连接
     * @throws IOException
     */
    public static List<String> registConsumers(Channel channel, String[] topics, Consumer consumer) throws IOException {
        if (topics == null) {
            throw new NullPointerException("startMoreConsume()的参数不能为空!");
        }
        List<String> queueList = Arrays.asList(topics);
        return registConsumers(channel, queueList, consumer);
    }

    /**
     * 在多个队列上注册一个消费者
     * @param channel
     * @param topicList
     * @param consumer
     * @return
     * @throws IOException
     */
    public static List<String> registConsumers(Channel channel, List<String> topicList, Consumer consumer) throws IOException {
        if (topicList == null || channel == null || consumer == null) {
            throw new NullPointerException("startMoreConsume()的参数不能为空!");
        }
        List<String> consumerTags = new ArrayList<>();
        for (String topic : topicList) {
            //在某个队列上注册消费者
            String consumerTag = channel.basicConsume(topic, consumer);
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
    public static void cancelConsumer(Channel channel, String consumerTag) throws IOException {
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
    public static void cancelConsumers(Channel channel, String[] consumerTags) throws IOException {
        if (consumerTags == null) {
            throw new NullPointerException("cancelConsumers的参数不能为空!");
        }
        List<String> consumerList = Arrays.asList(consumerTags);
        cancelConsumers(channel, consumerList);
    }

    /**
     * 批量取消消费者与消息队列之间的连接
     * @param channel
     * @param consumerTags
     * @throws IOException
     */
    public static void cancelConsumers(Channel channel, List<String> consumerTags) throws IOException {
        if (channel == null || consumerTags == null) {
            throw new NullPointerException("startMoreConsume()的参数不能为空!");
        }
        for (String consumerTag : consumerTags) {
            //取消消费者与某个消息队列之间的连接
            channel.basicCancel(consumerTag);
        }
    }

    /**
     * 绑定交换机和队列,并绑定一个routing_key
     * @param queue 队列名
     * @param exchange 交换机名
     * @return
     */
    public static AMQP.Queue.BindOk bindExchage(Channel channel, String queue, String exchange, String routingKey)
            throws IOException {
        return channel.queueBind(queue, exchange,routingKey);
    }

    /**
     * 解绑交换机和队列
     * @param queue 队列名
     * @param exchange 交换机名
     * @return
     */
    public static AMQP.Queue.UnbindOk unbindExchage(Channel channel, String queue, String exchange, String routingKey)
            throws IOException {
        return channel.queueUnbind(queue, exchange,routingKey);
    }

    /**
     * 删除指定队列 -- 直接删除
     */
    public static AMQP.Queue.DeleteOk deleteQueue(Channel channel, String queue)
            throws IOException {
        return channel.queueDelete(queue);
    }

    /**
     * 删除指定交换机-- 直接删除
     */
    public static AMQP.Exchange.DeleteOk deleteExchange(Channel channel, String exchange)
            throws IOException {
        return channel.exchangeDelete(exchange);
    }

    /**
     * 清空指定队列
     */
    public static AMQP.Queue.PurgeOk clearQueue(Channel channel, String queue) throws IOException {
        return channel.queuePurge(queue);
    }

    /**
     * 向交换机发送消息
     * @param exchangeName 交换机名称
     * @param routeKey routeKey
     * @param param 发送的消息体对象
     */
    public static <T> void basicPublish(String exchangeName, String routeKey, T param) {
        String message = JSON.toJSONString(param);
        Connection connection = getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
            channel.basicPublish(exchangeName, routeKey, null, message.getBytes("UTF-8"));
            channel.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }

    /**
     * 消息日志向交换机发送消息
     * @param param
     * @param <T>
     */
    public static synchronized <T> void basicPublicTaskLog(T param) {
        String jsonString = JSON.toJSONString(param);
        Channel channel = null;
        try {
            channel = createChannelDefault();
            channel.basicPublish(RabbitMQConstants.EXCHANGE_A, RabbitMQConstants.ROUTEKEY_TASK_LOG, null, jsonString.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (TimeoutException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取消息日志的channel
     * 消息日志使用单独的channel
     * @return
     */
    private static Channel getTaskLogChannel() {
        if (taskLogChannel == null) {
            try {
                taskLogChannel = getConnection().createChannel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return taskLogChannel;
    }

    /**
     * 节点通知向交换机发送消息
     * @param param
     * @param <T>
     */
    public static synchronized <T> void basicPublicNode(T param) {
        String jsonString = JSON.toJSONString(param);
        Channel channel = null;
        try {
            channel = createChannelDefault();
            channel.basicPublish(RabbitMQConstants.EXCHANGE_A, RabbitMQConstants.ROUTEKEY_TASK_LOG, null, jsonString.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (TimeoutException | IOException e) {
                e.printStackTrace();
            }
        }
    }


}
