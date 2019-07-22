package com.wisdom.iwcs.service.task.action;

import com.alibaba.fastjson.JSON;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.taskUtils.IConsumerAction;
import com.wisdom.iwcs.domain.log.BaseQueueInfo;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.LineTaskService;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 唤醒主任务的动作
 * @author han
 */
@Component
public class RouseMainTaskAction implements IConsumerAction {
    private final Logger logger = LoggerFactory.getLogger(RouseMainTaskAction.class);
    @Override
    public void action(String message) {
        if (!message.contains("{")) {
            return;
        }
        MainTaskMapper mainTaskMapper = AppContext.getBean("mainTaskMapper");
        SubTaskMapper subTaskMapper = AppContext.getBean("subTaskMapper");
        WcsTaskScheduler wcsTaskScheduler = AppContext.getBean("wcsTaskScheduler");

        BaseQueueInfo baseQueueInfo = JSON.parseObject(message, BaseQueueInfo.class);
        logger.info("子任务{}开始唤醒主任务", baseQueueInfo.getSubTaskNum());
        SubTask subTask = subTaskMapper.selectBySubTaskNum(baseQueueInfo.getSubTaskNum());
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        //唤醒主任务
        MainTaskWorker mainTaskWorker = new MainTaskWorker(null, mainTask, wcsTaskScheduler);
        Thread thread = new Thread(mainTaskWorker);
        thread.start();
        logger.info("子任务{}唤醒主任务结束", baseQueueInfo.getSubTaskNum());
    }
}
