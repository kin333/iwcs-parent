package com.wisdom.iwcs.service.log.logImpl;

import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
