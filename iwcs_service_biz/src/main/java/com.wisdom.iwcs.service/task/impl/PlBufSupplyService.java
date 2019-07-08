package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.PlBufSupplyRequest;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.*;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;

/**
 * 空货架缓存区补充
 * @Author george
 * @Date 2019/7/3 21:23
 */
@Service
public class PlBufSupplyService implements com.wisdom.iwcs.service.task.intf.IPlBufSupplyService {
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

    /**
     *  呼叫空货架
     * @param
     * @return
     */
    @Override
    public Result plBufSupply(PlBufSupplyRequest plBufSupplyRequest){

        //创建主任务
        String mainTaskNum = iTaskCreateService.mainTaskCommonAdd(plBufSupplyRequest.getTaskTypeCode(), plBufSupplyRequest.getAreaCode(), plBufSupplyRequest.getPriority());

        //查询模板关系表查找子任务
        List<TaskRel> taskRelList = taskRelMapper.selectByMainTaskType(plBufSupplyRequest.getTaskTypeCode());
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
            //TODO

            subTaskCreate.setNeedTrigger(taskRel.getNeedTrigger());
            subTaskCreate.setNeedConfirm(taskRel.getNeedConfirm());
            subTaskCreate.setNeedInform(taskRel.getNeedInform());

            subTaskCreate.setWorkerTaskCode(subTaskNum);

            //计算目标通过地图坐标查询坐标
            BaseMapBerth endBercode = baseMapBerthMapper.selectOneByBercode(plBufSupplyRequest.getTargetPoint());
            subTaskCreate.setEndX(endBercode.getCoox().doubleValue());
            subTaskCreate.setEndY(endBercode.getCooy().doubleValue());
            subTaskCreate.setEndBercode(plBufSupplyRequest.getTargetPoint());
            subTaskCreate.setMapCode(endBercode.getMapCode());
            subTaskCreate.setAreaCode(plBufSupplyRequest.getAreaCode());
            subTaskMapper.insertSelective(subTaskCreate);

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);
        }
        return new Result();
    }
}
