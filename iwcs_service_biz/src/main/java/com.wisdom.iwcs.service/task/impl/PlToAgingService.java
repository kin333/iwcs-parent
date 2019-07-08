package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.PlToAgingRequest;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import com.wisdom.iwcs.service.task.intf.IPlToAgingService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.AgingAreaPriorityProp.MANUAL_FIRST;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;

/**
 * 线体去老化区
 * @Author george
 * @Date 2019/7/4 09:45
 */
@Service
public class PlToAgingService implements IPlToAgingService {
    private final Logger logger = LoggerFactory.getLogger(PlToAgingService.class);

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
    public void plagingToQuaInsp(PlToAgingRequest plToAgingRequest){

        //创建主任务
        String mainTaskNum = iTaskCreateService.mainTaskCommonAdd(plToAgingRequest.getTaskTypeCode(), plToAgingRequest.getAreaCode(), plToAgingRequest.getPriority());

        //查询模板关系表查找子任务
        List<TaskRel> taskRelList = taskRelMapper.selectByMainTaskType(plToAgingRequest.getTaskTypeCode());
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

            subTaskCreate.setPodCode(plToAgingRequest.getPodCode());
            subTaskCreate.setWorkerTaskCode(subTaskNum);

            //计算起点通过地图坐标查询坐标
            BaseMapBerth startBercode = baseMapBerthMapper.selectOneByBercode(plToAgingRequest.getStartPoint());
            subTaskCreate.setStartX(startBercode.getCoox().doubleValue());
            subTaskCreate.setStartY(startBercode.getCooy().doubleValue());

            if (MANUAL_FIRST.equals(plToAgingRequest.getSubTaskBizProp())){
                //计算目标通过地图坐标查询坐标
                BaseMapBerth endBercode = baseMapBerthMapper.selectOneByBercode(plToAgingRequest.getTargetPoint());
                subTaskCreate.setEndX(endBercode.getCoox().doubleValue());
                subTaskCreate.setEndY(endBercode.getCooy().doubleValue());
                subTaskCreate.setEndBercode(endBercode.getBerCode());
            }

            BasePodDetail basePodDetail = new BasePodDetail();
            basePodDetail.setPodCode(plToAgingRequest.getPodCode());
            basePodDetail.setLockSource(subTaskNum);
            //货架上锁
            iMapResouceService.lockPod(basePodDetail);

            subTaskCreate.setStartBercode(plToAgingRequest.getStartPoint());
            subTaskCreate.setMapCode(startBercode.getMapCode());
            subTaskCreate.setAreaCode(plToAgingRequest.getAreaCode());
            subTaskCreate.setSubTaskBizProp(plToAgingRequest.getSubTaskBizProp());
            subTaskMapper.insertSelective(subTaskCreate);

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);

        }
    }
}
