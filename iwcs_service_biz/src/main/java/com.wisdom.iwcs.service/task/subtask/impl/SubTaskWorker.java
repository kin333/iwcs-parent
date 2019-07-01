package com.wisdom.iwcs.service.task.subtask.impl;


import com.rabbitmq.client.Channel;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.service.task.AbstractTaskWorker;
import com.wisdom.iwcs.service.task.conditions.ConditionBase;
import com.wisdom.iwcs.service.task.subtask.intf.WcsObservable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SubTaskWorker extends AbstractTaskWorker {
    private final Logger logger = LoggerFactory.getLogger(SubTaskWorker.class);

    private AbstractTaskWorker mainTask;
    private SubTask subTask;
    private List<ConditionBase> conditionList = new ArrayList<ConditionBase>();

    public SubTaskWorker(Channel channel, AbstractTaskWorker mainTask, SubTask subTask) {
        super(channel);
        this.mainTask = mainTask;
        this.subTask = subTask;
    }

    @Override
    public void preConditions() {
        while (true){
            try {
                synchronized (waitLock){
                    System.out.println("Main task is going to wait " + waitLock);
                    waitLock.wait();
                    System.out.println("End to wait for wait thread " + waitLock);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void postConditions() {

    }

    @Override
    public void process() {
        logger.info("子任务{}执行器线程被启动", subTask.getSubTaskNum());
//        List<SubTaskConditions> preTaskRelConditionsList = ;
//        List<SubTaskConditions> afterTaskRelConditionsList = subtask.getAfterTaskRelConditionsList();
//        while (true) {
//            logger.info("开始检查主任务{}开始执行下一步子任务{}，检查条件", subtask.getMainTaskNum(), subtask.getSubTaskNum());
//            //已结束的任务自动结束该任务并上报主任务线程
//            if (SubTaskStatusEnum.Finished.getStatusCode().equals(subtask.getTaskStatus())) {
//                //TODO 上报主线程
//                break;
//            } else {
//                // 检查并执行未完成的前置条件
//                preTaskRelConditionsList.stream().forEach(c -> c.getConditonTriger());
//
//
//            }
//
//
//            subtask.getConditionHandler();
//            logger.info("开始执行主任务{}子任务{}", subtask.getMainTaskNum(), subtask.getSubTaskNum());
//        }
    }


//    @Override
//    public void onMessage(WcsObservable o, Object arg) {
//
//    }
}
