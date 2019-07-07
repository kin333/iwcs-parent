package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.PToPRequest;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import com.wisdom.iwcs.service.task.intf.IPToPService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.AgingAreaPriorityProp.MANUAL_FIRST;
import static com.wisdom.iwcs.common.utils.TaskConstants.mainTaskStatus.MAIN_NOT_ISSUED;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;

/**
 * 点到点搬运任务
 * @Author george
 * @Date 2019/7/5 10:35
 */
@Service
public class PToPService implements IPToPService {
    private final Logger logger = LoggerFactory.getLogger(PToPService.class);

    @Autowired
    private MainTaskMapper mainTaskMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private TaskRelMapper taskRelMapper;
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService iTaskCreateService;
    @Autowired
    private IMapResouceService iMapResouceService;

    @Override
    public Result pTop(PToPRequest pToPRequest){

        //创建主任务
        String mainTaskNum = iTaskCreateService.mainTaskCommonAdd(pToPRequest.getTaskTypeCode(), pToPRequest.getAreaCode(), pToPRequest.getPriority());

        //查询模板关系表查找子任务
        List<TaskRel> taskRelList = taskRelMapper.selectByMainTaskType(pToPRequest.getTaskTypeCode());
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

            subTaskCreate.setPodCode(pToPRequest.getPodCode());
            subTaskCreate.setWorkerTaskCode(subTaskNum);

            //计算起点通过地图坐标查询坐标
            BaseMapBerth startBercode = baseMapBerthMapper.selectOneByBercode(pToPRequest.getStartPoint());
            subTaskCreate.setStart_x(startBercode.getCoox().doubleValue());
            subTaskCreate.setStart_y(startBercode.getCooy().doubleValue());

            //计算目标通过地图坐标查询坐标
            BaseMapBerth endBercode = baseMapBerthMapper.selectOneByBercode(pToPRequest.getTargetPoint());
            subTaskCreate.setEnd_x(endBercode.getCoox().doubleValue());
            subTaskCreate.setEnd_y(endBercode.getCooy().doubleValue());
            subTaskCreate.setEndBercode(pToPRequest.getTargetPoint());


            //货架上锁
            iMapResouceService.lockPod(pToPRequest.getPodCode(), subTaskNum);

            subTaskCreate.setStartBercode(pToPRequest.getStartPoint());
            subTaskCreate.setMapCode(startBercode.getMapCode());
            subTaskCreate.setAreaCode(pToPRequest.getAreaCode());
            subTaskMapper.insertSelective(subTaskCreate);

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);
        }
        return new Result();
    }
}
