package com.wisdom.test;

import com.rabbitmq.client.*;
import com.wisdom.config.RabbitConfig;
import com.wisdom.iwcs.common.utils.InterfaceLogConstants;
import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.common.utils.constant.SendStatus;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskTyp;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskTypMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.task.template.TemplateRelatedServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TemplateRelatedServer templateRelatedServer;
    @Autowired
    SubTaskTypMapper subTaskTypMapper;
    @Autowired
    ICommonService iCommonService;
    @GetMapping("/testProcess")
    public void testProcess() {
//        String subTaskNum = "TMP10001";
//
//        // 1. 从数据库获取子任务单
//        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
//        String jsonStr = "";
//        try {
//            jsonStr = templateRelatedServer.templateIntoInfo(subTaskNum);
//        } catch (InvocationTargetException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        // 2. 根据subtask的值，完善下发的信息，并下发命令
//        SubTaskTyp subTaskTyp = subTaskTypMapper.selectByTypeCode(subTask.getSubTaskTyp());
//        if (InterfaceLogConstants.SrcClientCode.SRC_HIK.equals(subTaskTyp.getWorkerType())) {
//            //如果执行者类型是海康,则调用海康的接口
//            String resultBody = NetWorkUtil.transferContinueTask(jsonStr, subTaskTyp.getWorkerUrl());
//            iCommonService.handleHikResponseAndThrowException(resultBody);
//        }
//        subTaskMapper.updateSendStatus(subTaskNum, SendStatus.SEND.getCode());

        String url = "http://192.168.102.99:80/rcs/services/rest/hikTpsService/endAllTasks";
        String jsonStr = "{\n" +
                "\"reqCode\":\"123sd1fa5sdf\",\n" +
                "\"reqTime\":\"2019-07-03 18:44:10\",\n" +
                "\"clientCode\":\"INSPUR\",\n" +
                "\"tokenCode\":\"09bd3456991fe1cf2e185de92fa5aab5\",\n" +
                "\"taskCode\":\"\",\n" +
                "\"wbCode\":\"083250AB118550\"\n" +
                "}";
        String resultBody = NetWorkUtil.transferContinueTask(jsonStr, url);
        iCommonService.handleHikResponseAndThrowException(resultBody);
    }

}

