package com.wisdom.iwcs.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePod;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.base.BasePodMapper;
import com.wisdom.iwcs.mapper.task.MainTaskTypeMapper;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import com.wisdom.iwcs.service.task.intf.IPlAutoWbCallPodService;
import com.wisdom.iwcs.service.task.intf.IPlBufSupplyService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.*;

/**
 * 任务创建
 * @Author george
 * @Date 2019/7/3 9:14
 */
@Service
public class TaskCreateService implements ITaskCreateService {
    private final Logger logger = LoggerFactory.getLogger(TaskCreateService.class);

    @Autowired
    private MainTaskTypeMapper mainTaskTypeMapper;
    @Autowired
    private IPlAutoWbCallPodService iPlAutoWbCallPodService;
    @Autowired
    private IPlBufSupplyService iPlBufSupplyService;
    @Autowired
    private IMapResouceService iMapResouceService;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;

    /**
     * 创建任务
     * @param  taskCreateRequest
     * @return
     */
    @Override
    public Result creatTask(TaskCreateRequest taskCreateRequest){

        //校验参数
        checkCreateTaskConfirmParam(taskCreateRequest);

        //根据任务编号调相应service
        MainTaskType mainTaskType = mainTaskTypeMapper.selectByMainTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        String taskTypeCode = mainTaskType.getMainTaskTypeCode();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskTypeCode), "未配置任务类型编号");
        switch (taskTypeCode){
            case PLAUTOWBCALLPOD:
                taskCreateRequest.setPriority(mainTaskType.getPriority());
                plAutoWbCallPodFunction(taskCreateRequest);
                break;
            case PLBUFSUPPLY:
                taskCreateRequest.setPriority(mainTaskType.getPriority());
                plBufSupplyFunction(taskCreateRequest);
                break;
            case PLTOAGING:
                taskCreateRequest.setPriority(mainTaskType.getPriority());
                PlToAgingRequest plToAgingRequest = new PlToAgingRequest();
                break;
            case AGINGTOQUAINSP:
                taskCreateRequest.setPriority(mainTaskType.getPriority());
                AgingToQuaInspRequest agingToQuaInspRequest = new AgingToQuaInspRequest();
                break;
            default:
                logger.error("wrong task type Code:{}",taskTypeCode);
        }
        return new Result();
    }

    /**
     * 统一参数校验
     */
    private void checkCreateTaskConfirmParam(TaskCreateRequest request){
        String taskCode = request.getTaskTypeCode();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCode), "缺少任务类型编号");
    }

    /**
     * 任务：工作台点位呼叫空货架
     * taskTypeCode: plAutoWbCallPod
     */
    public Result plAutoWbCallPodFunction(TaskCreateRequest taskCreateRequest){
        logger.info("工作台点位呼叫空货架:{}",JSON.toJSONString(taskCreateRequest));
        PlAutoWbCallPodRequest plAutoWbCallPodRequest = new PlAutoWbCallPodRequest();
        plAutoWbCallPodRequest.setPriority(taskCreateRequest.getPriority());
        plAutoWbCallPodRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plAutoWbCallPodRequest.setWbCode(taskCreateRequest.getWbCode());
        plAutoWbCallPodRequest.setAreaCode(SecurityUtils.getCurrentAreaCode());
        iPlAutoWbCallPodService.plAutoWbCallPod(plAutoWbCallPodRequest);
        return new Result();
    }

    /**
     * 任务：补充产线空货架缓存区
     * taskTypeCode: plBufSupply
     */
    public Result plBufSupplyFunction(TaskCreateRequest taskCreateRequest){
        logger.info("补充产线空货架缓存区:{}",JSON.toJSONString(taskCreateRequest));
        PlBufSupplyRequest plBufSupplyRequest = new PlBufSupplyRequest();
        plBufSupplyRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plBufSupplyRequest.setPriority(taskCreateRequest.getPriority());
        plBufSupplyRequest.setPodCode(taskCreateRequest.getPodCode());
        plBufSupplyRequest.setOperateAreaCode(taskCreateRequest.getOperateAreaCode());
        iPlBufSupplyService.plBufSupply(plBufSupplyRequest);
        return new Result();
    }

    /**
     * 任务：产线去老化区搬运
     * taskTypeCode: plToAging
     */
    public Result plToAgingFunction(TaskCreateRequest taskCreateRequest){
        logger.info("产线去老化区搬运:{}",JSON.toJSONString(taskCreateRequest));

        return new Result();
    }

    /**
     * 任务：自动老化区前往检验点
     * taskTypeCode: agingToQuaInsp
     */
    public Result agingToQuaInspFunction(TaskCreateRequest taskCreateRequest){
        logger.info("自动老化区前往检验点:{}",JSON.toJSONString(taskCreateRequest));
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getPodCode()), "货架号不能为空");
        //根据货架号查询起始点
        BasePodDetail basePodDetail = basePodDetailMapper.selectPodByPodCode(taskCreateRequest.getPodCode());
        //当前货架所在楼层，对比用户登录楼层权限//如果不在一个楼层创建失败
        String userAreaCode = SecurityUtils.getCurrentAreaCode();
        Preconditions.checkBusinessError(!userAreaCode.equals(basePodDetail.getAreaCode()), "用户登录的楼层不能创建该货架任务");
        //获取目标空闲点位，如果没有空闲点，任务创建失败

        //检验区先检验缓存区是否有点
        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
        BaseMapBerth baseMapBerth = iMapResouceService.caculateInspectionAreaEmptyPoint(lockMapBerthCondition);
        if (baseMapBerth == null){

        }
//        //锁定目标点
//        LockStorageDto lockStorageDto = new LockStorageDto();
//        lockStorageDto.setMapCode();
//        lockStorageDto.setBerCode();
//        lockStorageDto.setVersion();
        //iMapResouceService.lockMapBerth(lockStorageDto);
        //创建任务
        AgingToQuaInspRequest agingToQuaInspRequest = new AgingToQuaInspRequest();
        agingToQuaInspRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        agingToQuaInspRequest.setPodCode(taskCreateRequest.getPodCode());
        agingToQuaInspRequest.setPriority(taskCreateRequest.getPriority());


        return new Result();
    }

}
