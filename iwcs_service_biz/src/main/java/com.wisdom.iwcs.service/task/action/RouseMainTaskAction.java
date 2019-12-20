package com.wisdom.iwcs.service.task.action;

import com.wisdom.iwcs.common.utils.taskUtils.ConsumerActionInfo;
import com.wisdom.iwcs.common.utils.taskUtils.IConsumerAction;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 唤醒主任务的动作
 * @author han
 */
@Component
public class RouseMainTaskAction implements IConsumerAction {
    private final Logger logger = LoggerFactory.getLogger(RouseMainTaskAction.class);
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    WcsTaskScheduler wcsTaskScheduler;
    @Override
    public void action(ConsumerActionInfo consumerActionInfo) {
        String queueName = consumerActionInfo.getQueueName();

        //提取子任务号
        if (!queueName.contains("_")) {
            return;
        }
        String subTaskNum = queueName.split("_")[1];
        rouseMainTaskBySubTask(subTaskNum);
    }

    /**
     * 通过子任务唤醒任务
     * @param subTaskNum
     */
    public void rouseMainTaskBySubTask(String subTaskNum) {
        logger.info("子任务{}开始唤醒主任务", subTaskNum);
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        if (subTask == null) {
            logger.error("子任务{}不存在", subTaskNum);
            return;
        }
        rouseMainTaskByMain(subTask.getMainTaskNum());
        logger.info("子任务{}唤醒主任务结束", subTaskNum);
    }
    /**
     * 通过主任务唤醒任务
     * @param mainTaskNum
     */
    public void rouseMainTaskByMain(String mainTaskNum) {
        //获取正在执行的主任务Map
        ConcurrentHashMap<String, MainTaskWorker> maintaskWorkerMaps = wcsTaskScheduler.getMaintaskWorkerMaps();
        MainTaskWorker mainTaskWorker = maintaskWorkerMaps.get(mainTaskNum);
        //如果是null,说明主任务还没启动
        if (mainTaskWorker != null && mainTaskWorker.getSubTaskWorker() != null) {
            AtomicBoolean waitLock = mainTaskWorker.getSubTaskWorker().getWaitLock();
            //唤醒主任务
            synchronized (waitLock) {
                waitLock.notify();
            }
        } else {
            logger.info("{}主任务未启动", mainTaskNum);
        }
    }


}
