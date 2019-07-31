package com.wisdom.iwcs.service.task.wcsSimulator;

import com.alibaba.fastjson.JSON;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.constant.RabbitMQConstants;
import com.wisdom.iwcs.common.utils.taskUtils.ConsumerThread;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.mapper.log.TaskOperationLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 消息日志的消费者队列线程
 * @author han
 */
@Service
public class TaskLogThreadService extends ConsumerThread {
    private static final Logger logger = LoggerFactory.getLogger(TaskLogThreadService.class);

    public TaskLogThreadService() {
        //创建消息日志
        super(RabbitMQConstants.TASK_LOG_QUEUE,
                RabbitMQConstants.ROUTEKEY_TASK_LOG,
                consumerActionInfo -> {
                    String message = consumerActionInfo.getMessage();
                    //消息日志的动作
                    TaskOperationLog taskOperationLog = JSON.parseObject(message, TaskOperationLog.class);
                    TaskOperationLogMapper taskOperationLogMapper = AppContext.getBean("taskOperationLogMapper");
                    logger.info("日志开始生成:{}", message);
                    if (!TaskConstants.operationStatus.POST_CONDITION_FAILURE.equals(taskOperationLog.getOperationStatus())) {
                        //添加日志
                        taskOperationLogMapper.insertSelective(taskOperationLog);
                    } else {
                        //后置条件不满足日志为了防止日志泛滥,增加特殊处理机制
                        int count = taskOperationLogMapper.selectLogCount(taskOperationLog);
                        if (count <= 0) {
                            taskOperationLogMapper.insertSelective(taskOperationLog);
                        } else {
                            //如果此条子任务已经存在后置条件不满足,则新增,只不更新
                            taskOperationLogMapper.updateBySubTaskNum(taskOperationLog);
                        }
                    }
                    logger.info("日志生成结束:");
                });
    }

}
