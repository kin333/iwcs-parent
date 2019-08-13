package com.wisdom.iwcs.service.task.impl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.task.QuaInspToElvBufRequest;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BaseWaMapMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import com.wisdom.iwcs.service.task.intf.IQuaInspToElvBufService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;

/**
 * 检验区到电梯缓存区
 * @Author george
 * @Date 2019/7/22 15:22
 */
@Service
public class QuaInspToElvBufService implements IQuaInspToElvBufService {
    private final Logger logger = LoggerFactory.getLogger(QuaInspToElvBufService.class);

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
    public Result quaInspToElvBuf(QuaInspToElvBufRequest quaInspToElvBufRequest){
        //创建主任务
        String mainTaskNum = iTaskCreateService.mainTaskCommonAdd(quaInspToElvBufRequest.getTaskTypeCode(), quaInspToElvBufRequest.getAreaCode(), quaInspToElvBufRequest.getPriority());
        //查询模板关系表查找子任务
        List<TaskRel> taskRelList = taskRelMapper.selectByMainTaskType(quaInspToElvBufRequest.getTaskTypeCode());
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

            subTaskCreate.setPodCode(quaInspToElvBufRequest.getPodCode());
            subTaskCreate.setWorkerTaskCode(subTaskNum);

            BasePodDetail basePodDetail = new BasePodDetail();
            basePodDetail.setPodCode(quaInspToElvBufRequest.getPodCode());
            basePodDetail.setLockSource(subTaskNum);
            //货架上锁
            iMapResouceService.lockPod(basePodDetail);

            String mapCode = baseWaMapMapper.selectMapCodeByAreaCode(quaInspToElvBufRequest.getAreaCode());
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(mapCode),"找不到对应的地图代码");

            //锁住目标点位
            LockStorageDto lockStorageDto = new LockStorageDto();
            lockStorageDto.setMapCode(mapCode);
            lockStorageDto.setBerCode(quaInspToElvBufRequest.getTargetPoint());
            lockStorageDto.setPodCode(quaInspToElvBufRequest.getPodCode());
            lockStorageDto.setLockSource(subTaskNum);
            Result result = iMapResouceService.lockMapBerth(lockStorageDto);
            Preconditions.checkBusinessError(result.getReturnCode() != 200,result.getReturnMsg());

            //计算起点通过地图坐标查询坐标
            BaseMapBerth startBercode = baseMapBerthMapper.selectOneByBercode(quaInspToElvBufRequest.getStartPoint());
            subTaskCreate.setStartX(startBercode.getCoox().doubleValue());
            subTaskCreate.setStartY(startBercode.getCooy().doubleValue());
            subTaskCreate.setStartBercode(startBercode.getBerCode());
            subTaskCreate.setStartAlias(startBercode.getPointAlias());

            //计算目标通过地图坐标查询坐标
            BaseMapBerth endBercode = baseMapBerthMapper.selectOneByBercode(quaInspToElvBufRequest.getTargetPoint());
            subTaskCreate.setEndX(endBercode.getCoox().doubleValue());
            subTaskCreate.setEndY(endBercode.getCooy().doubleValue());
            subTaskCreate.setEndBercode(endBercode.getBerCode());
            subTaskCreate.setStartAlias(endBercode.getPointAlias());

            subTaskCreate.setMapCode(mapCode);
            subTaskCreate.setAreaCode(quaInspToElvBufRequest.getAreaCode());
            subTaskMapper.insertSelective(subTaskCreate);

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);
        }
        return new Result();
    }
}
