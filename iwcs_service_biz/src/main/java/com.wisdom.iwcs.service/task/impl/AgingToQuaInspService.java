package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.task.AgingToQuaInspRequest;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.*;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.task.intf.IAgingToQuaInspService;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;

/**
 * 任务：老化区前往检验点
 * @Author george
 * @Date 2019/7/4 9:04
 */
@Service
public class AgingToQuaInspService implements IAgingToQuaInspService {
    private final Logger logger = LoggerFactory.getLogger(AgingToQuaInspService.class);

    @Autowired
    private MainTaskMapper mainTaskMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private TaskRelMapper taskRelMapper;
    @Autowired
    private SubTaskConditionMapper subTaskConditionMapper;
    @Autowired
    private TaskRelConditionMapper taskRelConditionMapper;
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService iTaskCreateService;
    @Autowired
    private IMapResouceService iMapResouceService;

    @Override
    public Result agingToQuaInsp(AgingToQuaInspRequest agingToQuaInspRequest){

        //创建主任务
        String mainTaskNum = iTaskCreateService.mainTaskCommonAdd(agingToQuaInspRequest.getTaskTypeCode(), agingToQuaInspRequest.getAreaCode(), agingToQuaInspRequest.getPriority());

        //查询模板关系表查找子任务
        List<TaskRel> taskRelList = taskRelMapper.selectByMainTaskType(agingToQuaInspRequest.getTaskTypeCode());
        for (TaskRel taskRel:taskRelList){
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
            subTaskCreate.setAppCode(taskRel.getAppCode());
            subTaskCreate.setThirdUrl(taskRel.getThirdUrl());
            subTaskCreate.setThirdInvokeType(taskRel.getThirdInvokeType());
            subTaskCreate.setThirdStartMethod(taskRel.getThirdStartMethod());
            subTaskCreate.setThirdEndMethod(taskRel.getThirdEndMethod());
            subTaskCreate.setSendStatus(SUB_NOT_ISSUED);
            subTaskCreate.setTaskStatus(SUB_NOT_ISSUED);
            subTaskCreate.setNeedTrigger(taskRel.getNeedTrigger());
            subTaskCreate.setNeedConfirm(taskRel.getNeedConfirm());
            subTaskCreate.setNeedInform(taskRel.getNeedInform());
            subTaskCreate.setSubTaskSeq(taskRel.getSubTaskSeq());

            subTaskCreate.setPodCode(agingToQuaInspRequest.getPodCode());
            subTaskCreate.setWorkerTaskCode(subTaskNum);

            BasePodDetail basePodDetail = new BasePodDetail();
            basePodDetail.setPodCode(agingToQuaInspRequest.getPodCode());
            basePodDetail.setLockSource(subTaskNum);
            //货架上锁
            iMapResouceService.lockPod(basePodDetail);

            //锁住目标点位
            LockStorageDto lockStorageDto = new LockStorageDto();
            lockStorageDto.setMapCode(agingToQuaInspRequest.getMapCode());
            lockStorageDto.setBerCode(agingToQuaInspRequest.getTargetPoint());
            lockStorageDto.setPodCode(agingToQuaInspRequest.getPodCode());
            lockStorageDto.setLockSource(subTaskNum);
            Result result = iMapResouceService.lockMapBerth(lockStorageDto);
            Preconditions.checkBusinessError(result.getReturnCode() != 200,result.getReturnMsg());


            //计算起点通过地图坐标查询坐标
            BaseMapBerth startBercode = baseMapBerthMapper.selectOneByBercode(agingToQuaInspRequest.getStartPoint());
            subTaskCreate.setStartX(startBercode.getCoox().doubleValue());
            subTaskCreate.setStartY(startBercode.getCooy().doubleValue());

            //计算目标通过地图坐标查询坐标
            BaseMapBerth endBercode = baseMapBerthMapper.selectOneByBercode(agingToQuaInspRequest.getTargetPoint());
            subTaskCreate.setEndX(endBercode.getCoox().doubleValue());
            subTaskCreate.setEndY(endBercode.getCooy().doubleValue());

            subTaskCreate.setStartBercode(agingToQuaInspRequest.getStartPoint());
            subTaskCreate.setEndBercode(agingToQuaInspRequest.getTargetPoint());
            subTaskCreate.setMapCode(endBercode.getMapCode());
            subTaskCreate.setAreaCode(agingToQuaInspRequest.getAreaCode());
            subTaskCreate.setStartAlias(startBercode.getPointAlias());
            subTaskCreate.setEndAlias(endBercode.getPointAlias());
            subTaskMapper.insertSelective(subTaskCreate);

            //向消息队列发送消息
            String message = "老化区前往检验点任务创建完成,主任务号:" + mainTaskNum;
            RabbitMQPublicService.successTaskLog(new TaskOperationLog(subTaskNum, TaskConstants.operationStatus.CREATE_TASK,message));

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);
        }
        return new Result();
    }
}
