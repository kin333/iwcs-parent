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
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.base.BasePodMapper;
import com.wisdom.iwcs.mapper.task.MainTaskTypeMapper;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.mapper.task.TaskRelConditionMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.intf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.AgingAreaPriorityProp.MANUAL_FIRST;
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
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private IPlAutoWbCallPodService iPlAutoWbCallPodService;
    @Autowired
    private IPlBufSupplyService iPlBufSupplyService;
    @Autowired
    private IMapResouceService iMapResouceService;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private IAgingToQuaInspService iAgingToQuaInspService;
    @Autowired
    IPlToAgingService iPlToAgingService;
    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private SubTaskConditionMapper subTaskConditionMapper;
    @Autowired
    private TaskRelConditionMapper taskRelConditionMapper;

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
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getPointAlias()), "请填写点位编号");
        //查询点位坐标
        BaseMapBerth baseMapBerth =  baseMapBerthMapper.selectByPointAlias(taskCreateRequest.getPointAlias());
        Preconditions.checkBusinessError(baseMapBerth == null, "目标点位信息为空");

        PlAutoWbCallPodRequest plAutoWbCallPodRequest = new PlAutoWbCallPodRequest();
        plAutoWbCallPodRequest.setPriority(taskCreateRequest.getPriority());
        plAutoWbCallPodRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plAutoWbCallPodRequest.setTargetPoint(baseMapBerth.getBerCode());
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
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getTargetPoint()), "请填写目标点位");
        //查询点位坐标
        BaseMapBerth baseMapBerth =  baseMapBerthMapper.selectByPointAlias(taskCreateRequest.getPointAlias());
        Preconditions.checkBusinessError(baseMapBerth == null, "目标点位信息为空");

        PlBufSupplyRequest plBufSupplyRequest = new PlBufSupplyRequest();
        plBufSupplyRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plBufSupplyRequest.setPriority(taskCreateRequest.getPriority());
        plBufSupplyRequest.setTargetPoint(baseMapBerth.getBerCode());
        plBufSupplyRequest.setAreaCode(SecurityUtils.getCurrentAreaCode());
        iPlBufSupplyService.plBufSupply(plBufSupplyRequest);
        return new Result();
    }

    /**
     * 任务：产线去老化区搬运
     * taskTypeCode: plToAging
     */
    public Result plToAgingFunction(TaskCreateRequest taskCreateRequest){
        logger.info("产线去老化区搬运:{}",JSON.toJSONString(taskCreateRequest));
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getPodCode()) && Strings.isNullOrEmpty(taskCreateRequest.getStartBercode()), "货架号或起始点坐标不能为空");
        //校验是否传自动还是手动，手动必选目标点
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getSubTaskBizProp()), "请选择自动还是手动");
        if (MANUAL_FIRST.equals(taskCreateRequest.getSubTaskBizProp())){
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getTargetPoint()), "手动模式请选择目标点位");
        }
        if (!Strings.isNullOrEmpty(taskCreateRequest.getPodCode())){
            //货架不为空，查询所在点位
        }
        if (!Strings.isNullOrEmpty(taskCreateRequest.getStartBercode())){
            //起点不为空，查询点位正在执行任务的货架
            //如果货架为空，查询创建失败
        }

        //ICommonService.checkPodPointAgreement();
        //更改货架空满状态


        //目标点有货架，创建失败, 无货架、无任务，上锁

        //创建任务
        PlToAgingRequest plToAgingRequest = new PlToAgingRequest();
        plToAgingRequest.setAreaCode(SecurityUtils.getCurrentAreaCode());
        plToAgingRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plToAgingRequest.setPriority(taskCreateRequest.getPriority());
//        plToAgingRequest.setPodCode();
//        plToAgingRequest.setStartPoint();
        if (MANUAL_FIRST.equals(taskCreateRequest.getSubTaskBizProp())){
            //查询点位坐标
            BaseMapBerth baseMapBerth =  baseMapBerthMapper.selectByPointAlias(taskCreateRequest.getPointAlias());
            Preconditions.checkBusinessError(baseMapBerth == null, "目标点位信息为空");

            plToAgingRequest.setTargetPoint(baseMapBerth.getBerCode());
        }
        plToAgingRequest.setSubTaskBizProp(taskCreateRequest.getSubTaskBizProp());
        iPlToAgingService.agingToQuaInsp(plToAgingRequest);


        return new Result();
    }

    /**
     * 任务：老化区前往检验点
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
        //检验区先检验缓存区是否有空闲点，后获工作点是否有空闲
        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
//        lockMapBerthCondition.setBerthTypeValue();
        BaseMapBerth baseMapBerth = iMapResouceService.caculateInspectionWorkAreaEmptyPoint(lockMapBerthCondition);
//        if (baseMapBerth != null){
//
//        }else if (){
//
//        }else {
//
//        }
//        //锁定目标点

        //创建任务
        AgingToQuaInspRequest agingToQuaInspRequest = new AgingToQuaInspRequest();
        agingToQuaInspRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        agingToQuaInspRequest.setPodCode(taskCreateRequest.getPodCode());
        agingToQuaInspRequest.setPriority(taskCreateRequest.getPriority());
        agingToQuaInspRequest.setAreaCode(SecurityUtils.getCurrentAreaCode());
//        agingToQuaInspRequest.setStartPoint();
//        agingToQuaInspRequest.setTargetPoint();
        iAgingToQuaInspService.agingToQuaInsp(agingToQuaInspRequest);

        return new Result();
    }

    /**
     * 添加子任务条件
     * @param
     * @return
     */
    @Override
    public void subTaskConditionCommonAdd(String mainTaskTypeCode, String subTaskTypeCode, String subTaskNum){
        //通过主任务编号和子任务编号查询
        List<TaskRelCondition> taskRelConditionList = taskRelConditionMapper.selectByMainTaskTypeCodeAndSubCode(mainTaskTypeCode,subTaskTypeCode);
        for (TaskRelCondition taskRelCondition: taskRelConditionList){
            SubTaskCondition subTaskCondition = new SubTaskCondition();
            subTaskCondition.setCreateDate(new Date());
            subTaskCondition.setSubTaskNum(subTaskNum);
            subTaskCondition.setConditonHandler(taskRelCondition.getConditonHandler());
            subTaskCondition.setSubscribeEvent(taskRelCondition.getSubscribeEvent());
            subTaskCondition.setConditonTriger(taskRelCondition.getConditonTriger());
            subTaskConditionMapper.insertSelective(subTaskCondition);
        }
    }

}
