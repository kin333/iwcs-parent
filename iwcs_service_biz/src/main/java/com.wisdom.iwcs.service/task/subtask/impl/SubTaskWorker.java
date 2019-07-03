package com.wisdom.iwcs.service.task.subtask.impl;


import com.rabbitmq.client.Channel;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.InterfaceLogConstants;
import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.common.utils.constant.SendStatus;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskTyp;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskTypMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.base.baseImpl.CommonService;
import com.wisdom.iwcs.service.task.AbstractTaskWorker;
import com.wisdom.iwcs.service.task.conditions.ConditionBase;
import com.wisdom.iwcs.service.task.impl.SubTaskService;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import com.wisdom.iwcs.service.task.template.TemplateRelatedServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ActuatorMetricWriter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SubTaskWorker extends AbstractTaskWorker {
    private final Logger logger = LoggerFactory.getLogger(SubTaskWorker.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    SubTaskTypMapper subTaskTypMapper;
    @Autowired
    TemplateRelatedServer templateRelatedServer;
    @Autowired
    ICommonService iCommonService;

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
        SubTaskService subTaskService = (SubTaskService) AppContext.getBean("subTaskService");
        return subTaskService.preConditionsCheckAndExec(subTask);

    }


    @Override
    public void preConditions() {
        while (true){
            try {
                synchronized (waitLock){
                    System.out.println("sub task is going to wait " + waitLock);
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
        String subTaskNum = subTask.getSubTaskNum();
        logger.info("子任务{}开始下发process ", subTaskNum);

        // 1. 从数据库获取子任务单
        subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        String jsonStr = "";
        try {
            jsonStr = templateRelatedServer.templateIntoInfo(subTaskNum);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 2. 根据subtask的值，完善下发的信息，并下发命令
        SubTaskTyp subTaskTyp = subTaskTypMapper.selectByTypeCode(subTask.getSubTaskTyp());
        String resultBody;
        if (InterfaceLogConstants.SrcClientCode.SRC_HIK.equals(subTaskTyp.getWorkerType())) {
            //如果执行者类型是海康,则调用海康的接口
            resultBody = NetWorkUtil.transferContinueTask(jsonStr, subTaskTyp.getWorkerUrl());
            iCommonService.handleHikResponseAndThrowException(resultBody);
        }
        subTaskMapper.updateSendStatus(subTaskNum, SendStatus.SEND.getCode());
    }


//    @Override
//    public void onMessage(WcsObservable o, Object arg) {
//
//    }
}
