package com.wisdom.iwcs.service.log.logImpl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.constant.RabbitMQConstants;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 的公共服务
 */
public class RabbitMQPublicService {
    /**
     * 成功节点的消息日志
     * @param taskOperationLog
     */
    public static void successTaskLog(TaskOperationLog taskOperationLog) {
        taskOperationLog.setResultFlag(TaskConstants.resultStatus.SUCCESS);
        taskOperationLog.setCreatedTime(new Date());
        //向消息队列发送消息
        try {
            RabbitMQUtil.basicPublicTaskLog(taskOperationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 失败节点的消息日志
     * @param taskOperationLog
     */
    public static void failureTaskLog(TaskOperationLog taskOperationLog) {
        taskOperationLog.setResultFlag(TaskConstants.resultStatus.FAILURE);
        taskOperationLog.setCreatedTime(new Date());
        //向消息队列发送消息
        try {
            RabbitMQUtil.basicPublicTaskLog(taskOperationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 结束对应routeKey的任务
     * @param routeKey
     * @param subTaskNum 子任务单号, 用于关闭连接时的校验
     */
    public static void sendEndLogo(String routeKey, String subTaskNum) {
        Channel channel = null;
        try {
            channel = RabbitMQUtil.createChannelDefault();
            String message = RabbitMQConstants.END_LOGO + subTaskNum;
            channel.basicPublish(RabbitMQConstants.EXCHANGE_A, routeKey, null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> void sendInfoByRouteKey(String routeKey, T param) {
        String jsonString = JSON.toJSONString(param);
        Channel channel = null;
        try {
            channel = RabbitMQUtil.createChannelDefault();
            channel.basicPublish(RabbitMQConstants.EXCHANGE_A, routeKey, null, jsonString.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

}
