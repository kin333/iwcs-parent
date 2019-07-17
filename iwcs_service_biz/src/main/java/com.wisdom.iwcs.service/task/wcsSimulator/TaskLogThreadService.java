package com.wisdom.iwcs.service.task.wcsSimulator;

import com.alibaba.fastjson.JSON;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.constant.RabbitMQConstants;
import com.wisdom.iwcs.common.utils.taskUtils.ConsumerThread;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.mapper.log.TaskOperationLogMapper;
import org.springframework.stereotype.Service;

/**
 * 消息日志的消费者队列线程
 * @author han
 */
@Service
public class TaskLogThreadService extends ConsumerThread {

    public TaskLogThreadService() {
        //创建消息日志
        super(RabbitMQConstants.TASK_LOG_QUEUE,
                RabbitMQConstants.ROUTEKEY_TASK_LOG,
                message -> {
            //消息日志的动作
            TaskOperationLog taskOperationLog = JSON.parseObject(message, TaskOperationLog.class);
            TaskOperationLogMapper taskOperationLogMapper = AppContext.getBean("taskOperationLogMapper");
            //添加日志
            taskOperationLogMapper.insert(taskOperationLog);
        });
    }

}
