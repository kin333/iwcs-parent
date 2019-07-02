package com.wisdom.iwcs.service.task.subtask.impl;


import com.rabbitmq.client.Channel;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.service.task.AbstractTaskWorker;
import com.wisdom.iwcs.service.task.conditions.ConditionBase;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import com.wisdom.iwcs.service.task.subtask.intf.WcsObservable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SubTaskWorker extends AbstractTaskWorker {
    private final Logger logger = LoggerFactory.getLogger(SubTaskWorker.class);

    private MainTaskWorker mainTaskWorker;
    private SubTask subTask;
    private List<ConditionBase> conditionList = new ArrayList<ConditionBase>();

    public SubTaskWorker(Channel channel, AbstractTaskWorker mainTask, SubTask subTask) {
        super(channel);
        this.mainTaskWorker = (MainTaskWorker) mainTask;
        this.subTask = subTask;
    }

    public boolean isRunnable(){
        // 1. 判断内存中记录的N个条件是否都已经满足，如果满足，直接返回OK


        // 2. 判断是否有资源能满足条件，如果满足则直接锁定。
        //    满足的条件该锁定资源需要锁定，并记录锁定状态及谁锁定了该资源, in_lock and lock_source

        return true;
    }


    @Override
    public void preConditions() {
        while (true){
            try {
                synchronized (waitLock){
                    System.out.println("Main task is going to wait " + waitLock);
                    if (! isRunnable()) {
                        waitLock.wait(10 * 1000);
                    } else{
                        logger.info("Task {}, subtask: {} become runnable, go...");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // End of while

    }

    @Override
    public void postConditions() {

        //通知主任务的时机，待定......
        mainTaskWorker.onSubTaskDone();

        // 锁定的资源


    }

    @Override
    public void process() {
        logger.info("子任务{}执行器线程被启动", subTask.getSubTaskNum());

        // 1. 从数据库获取子任务单

        // 2. 根据subtask的值，完善下发的信息，并下发命令



//        List<SubTaskCondition> preTaskRelConditionsList = ;
//        List<SubTaskCondition> afterTaskRelConditionsList = subtask.getAfterTaskRelConditionsList();
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
