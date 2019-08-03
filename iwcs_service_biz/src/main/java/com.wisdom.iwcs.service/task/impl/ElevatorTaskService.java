package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.elevator.Elevator;
import com.wisdom.iwcs.domain.elevator.ElevatorReport;
import com.wisdom.iwcs.domain.elevator.ElevatorTaskRequest;
import com.wisdom.iwcs.domain.task.EleControlTask;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BaseWaMapMapper;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.mapper.elevator.ElevatorMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.elevator.impl.ElevatorNotifyService;
import com.wisdom.iwcs.service.task.intf.IElevatorTaskService;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskStatus.ELE_TASK_INIT;
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
    @Autowired
    private EleControlTaskMapper eleControlTaskMapper;
    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private ElevatorMapper elevatorMapper;
    @Autowired
    private ElevatorNotifyService elevatorNotifyService;
    @Autowired
    private MainTaskMapper mainTaskMapper;

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

            //锁住目标点位
            LockStorageDto lockStorageDto = new LockStorageDto();
            lockStorageDto.setBerCode(elevatorTaskRequest.getTargetPoint());
            lockStorageDto.setLockSource(subTaskNum);
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(elevatorTaskRequest.getTargetPoint());
            lockStorageDto.setMapCode(baseMapBerth.getMapCode());
            Result result = iMapResouceService.lockMapBerth(lockStorageDto);
            Preconditions.checkBusinessError(result.getReturnCode() != 200,result.getReturnMsg());
            subTaskCreate.setPodCode(elevatorTaskRequest.getPodCode());
            subTaskCreate.setWorkerTaskCode(subTaskNum);

            //计算起点通过地图坐标查询坐标
            BaseMapBerth startBercode = baseMapBerthMapper.selectOneByBercode(elevatorTaskRequest.getStartPoint());
            subTaskCreate.setStartX(startBercode.getCoox().doubleValue());
            subTaskCreate.setStartY(startBercode.getCooy().doubleValue());

            //计算目标通过地图坐标查询坐标
            BaseMapBerth endBercode = baseMapBerthMapper.selectOneByBercode(elevatorTaskRequest.getTargetPoint());
            subTaskCreate.setEndX(endBercode.getCoox().doubleValue());
            subTaskCreate.setEndY(endBercode.getCooy().doubleValue());

            //货架上锁
            BasePodDetail basePodDetail = new BasePodDetail();
            basePodDetail.setPodCode(elevatorTaskRequest.getPodCode());
            basePodDetail.setLockSource(subTaskNum);
            iMapResouceService.lockPod(basePodDetail);

            subTaskCreate.setStartBercode(elevatorTaskRequest.getStartPoint());
            subTaskCreate.setEndBercode(elevatorTaskRequest.getTargetPoint());
            subTaskMapper.insertSelective(subTaskCreate);

            //添加子任务条件
            iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);
        }

        //更新主任务
        MainTask mainTask = new MainTask();
        mainTask.setSourceFloor(elevatorTaskRequest.getSourceFloor());
        mainTask.setDestFloor(elevatorTaskRequest.getDestFloor());
        mainTask.setElevatorWorkType(elevatorTaskRequest.getEleWorkType());
        mainTask.setMainTaskNum(mainTaskNum);
        mainTaskMapper.updateMainTaskEleByMainTaskNum(mainTask);

        //TODO 锁住电梯 == 梯控任务创建
        String eleTaskCode =  iCommonService.randomHexString(8);
        EleControlTask eleControlTask = new EleControlTask();
        eleControlTask.setMainTaskNum(mainTaskNum);
        eleControlTask.setEleTaskCode(eleTaskCode);
        eleControlTask.setTaskStatus(ELE_TASK_INIT);
        eleControlTask.setSourceFloor(Integer.valueOf(elevatorTaskRequest.getSourceFloor()));
        eleControlTask.setDestFloor(Integer.valueOf(elevatorTaskRequest.getDestFloor()));
        eleControlTask.setCreatedTime(new Date());

        //更新电梯表
        Elevator elevator = new Elevator();
        elevator.setEleTaskUpdateTime(new Date());
        elevator.setMainTaskNum(mainTaskNum);
        elevator.setEleTaskStatus("1");
        elevator.setEleTaskCode(eleTaskCode);
        elevatorMapper.updateElevatorInfo(elevator);

        //通知电梯 到起始楼层
        ElevatorReport elevatorReport = new ElevatorReport();
        String randomNum = iCommonService.randomHexString(8);
        elevatorReport.setReqCode(randomNum);
        elevatorReport.setFloor("0"+elevatorTaskRequest.getSourceFloor());
        elevatorNotifyService.selectCrossFloorTask(elevatorReport);

        return new Result();
    }
}
