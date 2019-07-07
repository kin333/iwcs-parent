package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.QuaBufToQuaRequest;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import com.wisdom.iwcs.service.task.intf.IQuaBufToQuaService;
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
 * 检验缓冲区去检验点
 * @Author george
 * @Date 2019/7/7 15:45
 */
@Service
public class QuaBufToQuaService implements IQuaBufToQuaService {
    private final Logger logger = LoggerFactory.getLogger(QuaBufToQuaService.class);

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
    public Result quaBufToQua(QuaBufToQuaRequest quaBufToQuaRequest){
        //创建主任务
        MainTask mainTaskCreate = new MainTask();
        String mainTaskNum = CodeBuilder.codeBuilder("M");
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setCreateDate(new Date());
        mainTaskCreate.setPriority(quaBufToQuaRequest.getPriority());
        mainTaskCreate.setMainTaskTypeCode(quaBufToQuaRequest.getTaskTypeCode());
        mainTaskCreate.setAreaCode(quaBufToQuaRequest.getAreaCode());
        mainTaskCreate.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskMapper.insertSelective(mainTaskCreate);
        //查询模板关系表查找子任务
        List<TaskRel> taskRelList = taskRelMapper.selectByMainTaskType(quaBufToQuaRequest.getTaskTypeCode());
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

            subTaskCreate.setPodCode(quaBufToQuaRequest.getPodCode());
            subTaskCreate.setWorkerTaskCode(subTaskNum);

            //计算起点通过地图坐标查询坐标
            BaseMapBerth startBercode = baseMapBerthMapper.selectOneByBercode(quaBufToQuaRequest.getStartPoint());
            subTaskCreate.setStart_x(startBercode.getCoox().doubleValue());
            subTaskCreate.setStart_y(startBercode.getCooy().doubleValue());

            //货架上锁
            iMapResouceService.lockPod(quaBufToQuaRequest.getPodCode(), subTaskNum);

            subTaskCreate.setStartBercode(quaBufToQuaRequest.getStartPoint());
            subTaskCreate.setMapCode(startBercode.getMapCode());
            subTaskCreate.setAreaCode(quaBufToQuaRequest.getAreaCode());
            subTaskMapper.insertSelective(subTaskCreate);

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);
        }

        return new Result();
    }



}
