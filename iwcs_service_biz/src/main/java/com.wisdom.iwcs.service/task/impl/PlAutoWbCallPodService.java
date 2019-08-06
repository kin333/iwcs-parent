package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.task.PlAutoWbCallPodRequest;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.*;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.task.intf.IPlAutoWbCallPodService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;


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
    @Autowired
    private ITaskCreateService iTaskCreateService;

    /**
     *  呼叫空货架
     * @param
     * @return
     */
    @Override
    public Result plAutoWbCallPod(PlAutoWbCallPodRequest plAutoWbCallPodRequest){

        //创建主任务
        String mainTaskNum = iTaskCreateService.mainTaskCommonAdd(plAutoWbCallPodRequest.getTaskTypeCode(), plAutoWbCallPodRequest.getAreaCode(), plAutoWbCallPodRequest.getPriority());

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
            subTask.setSendStatus(SUB_NOT_ISSUED);
            subTask.setTaskStatus(SUB_NOT_ISSUED);
            subTask.setNeedTrigger(taskRel.getNeedTrigger());
            subTask.setNeedConfirm(taskRel.getNeedConfirm());
            subTask.setNeedInform(taskRel.getNeedInform());
            subTask.setSubTaskSeq(taskRel.getSubTaskSeq());
            subTask.setStartAlias(plAutoWbCallPodRequest.getEndAlias());
            //通过地图坐标查询坐标
            BaseMapBerth endBercode = baseMapBerthMapper.selectOneByBercode(plAutoWbCallPodRequest.getTargetPoint());
            subTask.setEndX(endBercode.getCoox().doubleValue());
            subTask.setEndY(endBercode.getCooy().doubleValue());
            subTask.setEndBercode(endBercode.getBerCode());

            subTask.setWorkerTaskCode(subTaskNum);

            subTask.setMapCode(endBercode.getMapCode());
            subTask.setAreaCode(plAutoWbCallPodRequest.getAreaCode());
            subTaskMapper.insertSelective(subTask);

            //向消息队列发送消息
            String message = "工作台点位呼叫空货架任务创建完成,主任务号:" + mainTaskNum;
            RabbitMQPublicService.successTaskLog(new TaskOperationLog(subTaskNum, TaskConstants.operationStatus.CREATE_TASK,message));

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);
        }
        return new Result();
    }
}
