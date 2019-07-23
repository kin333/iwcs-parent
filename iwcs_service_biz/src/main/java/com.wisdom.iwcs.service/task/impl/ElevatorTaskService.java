package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.ElevatorTaskRequest;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BaseWaMapMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.service.task.intf.IElevatorTaskService;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;

/**
 *
 * @Author george
 * @Date 2019/7/22 17:18
 */
@Service
public class ElevatorTaskService implements IElevatorTaskService {
    private final Logger logger = LoggerFactory.getLogger(ElevatorTaskService.class);

    @Autowired
    private ITaskCreateService iTaskCreateService;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private TaskRelMapper taskRelMapper;
    @Autowired
    private IMapResouceService iMapResouceService;
    @Autowired
    private BaseWaMapMapper baseWaMapMapper;
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;

    @Override
    public Result elevatorTask(ElevatorTaskRequest elevatorTaskRequest){
        //创建主任务
        String mainTaskNum = iTaskCreateService.mainTaskCommonAdd(elevatorTaskRequest.getTaskTypeCode(), "", elevatorTaskRequest.getPriority());
        //查询模板关系表查找子任务
        List<TaskRel> taskRelList = taskRelMapper.selectByMainTaskType(elevatorTaskRequest.getTaskTypeCode());
        for (TaskRel taskRel:taskRelList) {
            //创建子任务
            SubTask subTaskCreate = new SubTask();
            String subTaskNum = CodeBuilder.codeBuilder("S");
            subTaskCreate.setSubTaskNum(subTaskNum);
            subTaskCreate.setMainTaskNum(mainTaskNum);
            subTaskCreate.setSubTaskTyp(taskRel.getSubTaskTypeCode());
            subTaskCreate.setCreateDate(new Date());
            subTaskCreate.setMainTaskSeq(taskRel.getSubTaskSeq());
            subTaskCreate.setMainTaskType(taskRel.getMainTaskTypeCode());
            subTaskCreate.setThirdType(taskRel.getThirdType());
            subTaskCreate.setThirdUrl(taskRel.getThirdUrl());
            subTaskCreate.setAppCode(taskRel.getAppCode());
            subTaskCreate.setThirdInvokeType(taskRel.getThirdInvokeType());
            subTaskCreate.setThirdStartMethod(taskRel.getThirdStartMethod());
            subTaskCreate.setThirdEndMethod(taskRel.getThirdEndMethod());
            subTaskCreate.setSendStatus(SUB_NOT_ISSUED);
            subTaskCreate.setTaskStatus(SUB_NOT_ISSUED);
            subTaskCreate.setNeedTrigger(taskRel.getNeedTrigger());
            subTaskCreate.setNeedConfirm(taskRel.getNeedConfirm());
            subTaskCreate.setNeedInform(taskRel.getNeedInform());
            subTaskCreate.setSubTaskSeq(taskRel.getSubTaskSeq());

            subTaskCreate.setPodCode(elevatorTaskRequest.getPodCode());
            subTaskCreate.setWorkerTaskCode(subTaskNum);

            BasePodDetail basePodDetail = new BasePodDetail();
            basePodDetail.setPodCode(elevatorTaskRequest.getPodCode());
            basePodDetail.setLockSource(subTaskNum);
            //货架上锁
            iMapResouceService.lockPod(basePodDetail);

            //TODO 锁住电梯 == 梯控

            subTaskCreate.setSourceFloor(elevatorTaskRequest.getSourceFloor());
            subTaskCreate.setDestFloor(elevatorTaskRequest.getDestFloor());
            subTaskCreate.setElevatorWorkType(elevatorTaskRequest.getEleWorkType());
            subTaskMapper.insertSelective(subTaskCreate);

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);
        }
        return new Result();
    }
}
