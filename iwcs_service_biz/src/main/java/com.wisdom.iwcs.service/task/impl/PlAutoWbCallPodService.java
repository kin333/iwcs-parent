package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.*;
import com.wisdom.iwcs.service.task.intf.IPlAutoWbCallPodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.wisdom.iwcs.common.utils.TaskConstants.taskStatus.NOT_ISSUED;

/**
 * 工作台点位呼叫空货架
 * @Author george
 * @Date 2019/7/3 10:10 
 */
@Service
public class PlAutoWbCallPodService implements IPlAutoWbCallPodService {
    private final Logger logger = LoggerFactory.getLogger(PlAutoWbCallPodService.class);

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

    /**
     *  呼叫空货架
     * @param
     * @return
     */
    @Override
    public Result plAutoWbCallPod(PlAutoWbCallPodRequest plAutoWbCallPodRequest){
        //创建主任务
        MainTask mainTaskCreate = new MainTask();
        String mainTaskNum = CodeBuilder.codeBuilder("M");
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setCreateDate(new Date());
        mainTaskCreate.setMainTaskTypeCode(plAutoWbCallPodRequest.getTaskTypeCode());
        mainTaskCreate.setPriority(plAutoWbCallPodRequest.getPriority());
        mainTaskCreate.setTaskStatus(NOT_ISSUED);
        mainTaskMapper.insertSelective(mainTaskCreate);
        //查询模板关系表查找子任务
        List<TaskRel> taskRelList = taskRelMapper.selectByMainTaskType(plAutoWbCallPodRequest.getTaskTypeCode());
        //创建子任务
        for (TaskRel taskRel:taskRelList){
            SubTask subTask = new SubTask();
            String subTaskNum = CodeBuilder.codeBuilder("S");
            subTask.setSubTaskNum(subTaskNum);
            subTask.setMainTaskNum(mainTaskNum);
            subTask.setSubTaskTyp(taskRel.getSubTaskTypeCode());
            subTask.setCreateDate(new Date());
            subTask.setMainTaskSeq(taskRel.getSubTaskSeq());
            subTask.setMainTaskType(taskRel.getMainTaskTypeCode());
            subTask.setThirdType(taskRel.getThirdType());
            subTask.setAppCode(taskRel.getAppCode());
            subTask.setThirdUrl(taskRel.getThirdUrl());
            subTask.setThirdInvokeType(taskRel.getThirdInvokeType());
            subTask.setThirdStartMethod(taskRel.getThirdStartMethod());
            subTask.setThirdEndMethod(taskRel.getThirdEndMethod());
            subTask.setSendStatus(NOT_ISSUED);
            subTask.setTaskStatus(NOT_ISSUED);
            subTask.setPodCode(plAutoWbCallPodRequest.getPodCode());
            subTask.setStartBercode(plAutoWbCallPodRequest.getStartBercode());
            subTask.setEndBercode(plAutoWbCallPodRequest.getWbCode());
            subTask.setNeedTrigger(taskRel.getNeedTrigger());
            subTask.setNeedConfirm(taskRel.getNeedConfirm());
            subTask.setNeedInform(taskRel.getNeedInform());
            //通过地图坐标查询坐标
            BaseMapBerth startBercode = baseMapBerthMapper.selectOneByBercode(plAutoWbCallPodRequest.getStartBercode());
            subTask.setStart_x(startBercode.getCoox().doubleValue());
            subTask.setStart_y(startBercode.getCooy().doubleValue());
            BaseMapBerth endBercode = baseMapBerthMapper.selectOneByBercode(plAutoWbCallPodRequest.getWbCode());
            subTask.setEnd_x(endBercode.getCoox().doubleValue());
            subTask.setEnd_y(endBercode.getCooy().doubleValue());
            subTaskMapper.insertSelective(subTask);

            //通过主任务编号和子任务编号查询
            TaskRelCondition taskRelConditionList = taskRelConditionMapper.selectByMainTaskTypeCodeAndSubCode(taskRel.getMainTaskTypeCode(),taskRel.getSubTaskTypeCode());

            //添加子任务条件
            SubTaskCondition subTaskCondition = new SubTaskCondition();
            subTaskCondition.setCreateDate(new Date());
            subTaskCondition.setSubTaskNum(subTaskNum);
            subTaskCondition.setSubscribeEvent(taskRelConditionList.getSubscribeEvent());
            subTaskConditionMapper.insertSelective(subTaskCondition);
        }
        return new Result();
    }
}
