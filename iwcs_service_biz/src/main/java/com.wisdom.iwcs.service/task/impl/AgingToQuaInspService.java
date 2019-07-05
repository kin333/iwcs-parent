package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.*;
import com.wisdom.iwcs.service.task.intf.IAgingToQuaInspService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.mainTaskStatus.MAIN_NOT_ISSUED;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;

/**
 * 任务：老化区前往检验点
 * @Author george
 * @Date 2019/7/4 9:04
 */
@Service
public class AgingToQuaInspService implements IAgingToQuaInspService {
    private final Logger logger = LoggerFactory.getLogger(PlBufSupplyService.class);

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

    @Override
    public Result agingToQuaInsp(AgingToQuaInspRequest agingToQuaInspRequest){
        //创建主任务
        MainTask mainTaskCreate = new MainTask();
        String mainTaskNum = CodeBuilder.codeBuilder("M");
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setCreateDate(new Date());
        mainTaskCreate.setMainTaskTypeCode(agingToQuaInspRequest.getTaskTypeCode());
        mainTaskCreate.setPriority(agingToQuaInspRequest.getPriority());
        mainTaskCreate.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskCreate.setAreaCode(agingToQuaInspRequest.getAreaCode());
        mainTaskMapper.insertSelective(mainTaskCreate);
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

            //计算起点通过地图坐标查询坐标
            BaseMapBerth startBercode = baseMapBerthMapper.selectOneByBercode(agingToQuaInspRequest.getStartPoint());
            subTaskCreate.setStart_x(startBercode.getCoox().doubleValue());
            subTaskCreate.setStart_y(startBercode.getCooy().doubleValue());

            //计算目标通过地图坐标查询坐标
            BaseMapBerth endBercode = baseMapBerthMapper.selectOneByBercode(agingToQuaInspRequest.getTargetPoint());
            subTaskCreate.setEnd_x(endBercode.getCoox().doubleValue());
            subTaskCreate.setEnd_y(endBercode.getCooy().doubleValue());

            subTaskCreate.setStartBercode(agingToQuaInspRequest.getStartPoint());
            subTaskCreate.setEndBercode(agingToQuaInspRequest.getTargetPoint());
            subTaskCreate.setMapCode(endBercode.getMapCode());
            subTaskCreate.setAreaCode(agingToQuaInspRequest.getAreaCode());
            subTaskMapper.insertSelective(subTaskCreate);

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);
        }
        return new Result();
    }
}
