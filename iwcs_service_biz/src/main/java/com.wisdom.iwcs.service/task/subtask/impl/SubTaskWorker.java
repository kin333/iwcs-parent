package com.wisdom.iwcs.service.task.subtask.impl;


import com.rabbitmq.client.Channel;
import com.wisdom.base.context.AppContext;
import com.wisdom.base.quartz.SpringContextUtils;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.task.AbstractTaskWorker;
import com.wisdom.iwcs.service.task.conditions.ConditionBase;
import com.wisdom.iwcs.service.task.impl.SubTaskConditionService;
import com.wisdom.iwcs.service.task.impl.SubTaskService;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import com.wisdom.iwcs.service.task.template.IwcsPublicService;
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
        // 判断是否有资源能满足条件，如果满足则直接锁定。
        // 满足的条件该锁定资源需要锁定，并记录锁定状态及谁锁定了该资源, in_lock and lock_source
        try {
            SubTaskService subTaskService = (SubTaskService) AppContext.getBean("subTaskService");
            subTaskService.preConditionsCheckAndExec(subTask);
            return true;
        } catch (Exception e) {
            logger.info("{}子任务前置条件暂不满足", subTask.getSubTaskNum());
            e.printStackTrace();
            return false;
        }

    }

    public boolean postRunnable() {
        try {
            SubTaskService subTaskService = (SubTaskService) AppContext.getBean("subTaskService");
            return subTaskService.postConditionsCheckAndExec(subTask);
        } catch (Exception e) {
            logger.info("{}子任务后置条件暂不满足", subTask.getSubTaskNum());
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void preConditions() {
        while (true){
            try {
                synchronized (waitLock){
                    System.out.println("sub task is going to wait " + waitLock);
                    if (! isRunnable()) {
                        logger.info("Task {}, subtask: {} is gonging to wait 10*1000, go...", subTask.getMainTaskNum(), subTask.getSubTaskNum());
                        waitLock.wait(5 * 1000 * 1);
                        logger.info("Task thread start!");
                    } else{
                        logger.info("Task {}, subtask: {} become runnable, go...", subTask.getMainTaskNum(), subTask.getSubTaskNum());
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
        while (true){
            try {
                synchronized (waitLock){
                    System.out.println("sub task is going to wait " + waitLock);
                    if (! postRunnable()) {
                        logger.info("子任务{}后置条件检查未符合,5s后重试", subTask.getSubTaskNum());
                        waitLock.wait(5 * 1000);
                    } else{
                        //通知主任务的时机，待定......
                        logger.info("子任务{}后置条件检查符合条件,通知结束", subTask.getSubTaskNum());
                        mainTaskWorker.onSubTaskDone(subTask);
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 锁定的资源

    }

    @Override
    public void process() {
        while (true) {
            synchronized (waitLock) {
                try {
                    IwcsPublicService iwcsPublicService = (IwcsPublicService) SpringContextUtils.getBean("iwcsPublicService");
                    iwcsPublicService.sendInfoBySubTaskNum(subTask.getSubTaskNum());
                    reExecFlag.set(false);
                    break;
                } catch (Exception e) {
                    //向消息队列发送消息
                    String message = "子任务下发失败,主任务号:" + subTask.getMainTaskNum() + ",错误信息:" + e.getMessage();
                    RabbitMQPublicService.failureTaskLog(new TaskOperationLog(subTask.getSubTaskNum(), TaskConstants.operationStatus.SEND_FAILURE,message));
                    logger.error("子任务下发失败{},原因:{},准备回滚前置条件", subTask.getSubTaskNum(), e.getMessage());
                    SubTaskService subTaskService = (SubTaskService) SpringContextUtils.getBean("subTaskService");
                    boolean rollBackPreConResult = subTaskService.rollbackPreCondition(subTask.getSubTaskNum());
                    reExecFlag.set(true);
                    e.printStackTrace();
                    try {
                        waitLock.wait(1 * 60 * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }
        }


    }

    @Override
    public void loginListenner() {
        try {
            SubTaskConditionService subTaskConditionService = (SubTaskConditionService) AppContext.getBean("subTaskConditionService");
            subTaskConditionService.loginListenner(subTask.getSubTaskNum());
        } catch (Exception e) {
            logger.error("子任务单{}消息队列创建失败", subTask.getSubTaskNum());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteListenner() {
        try {
            SubTaskConditionService subTaskConditionService = AppContext.getBean("subTaskConditionService");
            subTaskConditionService.deleteListenner(subTask.getSubTaskNum());
        } catch (Exception e) {
            logger.error("子任务单{}消息队列取消失败", subTask.getSubTaskNum());
            e.printStackTrace();
        }
    }

    public SubTask getSubTask() {
        return subTask;
    }

    //    @Override
//    public void onMessage(WcsObservable o, Object arg) {
//
//    }
}
