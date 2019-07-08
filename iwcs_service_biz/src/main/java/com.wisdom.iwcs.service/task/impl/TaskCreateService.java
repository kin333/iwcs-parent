package com.wisdom.iwcs.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.MainTaskTypeMapper;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.mapper.task.TaskRelConditionMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.intf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.AgingAreaPriorityProp.MANUAL_FIRST;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.QUAINSPCACHEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.QUAINSPWORKAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.AGINGREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PodInStockConstants.NOT_EMPTY_POD;
import static com.wisdom.iwcs.common.utils.TaskConstants.mainTaskStatus.MAIN_NOT_ISSUED;
import static com.wisdom.iwcs.common.utils.TaskConstants.pTopTaskSubTaskTypeConstants.INIT_STORAGE;
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
    private MainTaskMapper mainTaskMapper;
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
    @Autowired
    private IPToPService ipToPService;
    @Autowired
    private IQuaBufToQuaService iQuaBufToQuaService;

    /**
     * 创建任务
     * @param  taskCreateRequest
     * @return
     */
    @Override
    @Transactional
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
                plToAgingFunction(taskCreateRequest);
                break;
            case AGINGTOQUAINSP:
                taskCreateRequest.setPriority(mainTaskType.getPriority());
                agingToQuaInspFunction(taskCreateRequest);
                break;
            case PTOP:
                taskCreateRequest.setPriority(mainTaskType.getPriority());
                pTopFunction(taskCreateRequest);
                break;
            case QUABUFTOQUA:
                taskCreateRequest.setPriority(mainTaskType.getPriority());
                pTopFunction(taskCreateRequest);
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
    public void plAutoWbCallPodFunction(TaskCreateRequest taskCreateRequest){
        logger.info("工作台点位呼叫空货架:{}",JSON.toJSONString(taskCreateRequest));
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getTargetPointAlias()), "请填写点位编号");
        //查询点位坐标
        BaseMapBerth baseMapBerth =  baseMapBerthMapper.selectByPointAlias(taskCreateRequest.getTargetPointAlias());
        Preconditions.checkBusinessError(baseMapBerth == null, "目标点位信息为空");

        //校验目标点位和用户登录的点位是否在同一楼层
        //Preconditions.checkBusinessError(baseMapBerth.getAreaCode() != SecurityUtils.getCurrentAreaCode(), "请选择点位楼层创建任务");


        PlAutoWbCallPodRequest plAutoWbCallPodRequest = new PlAutoWbCallPodRequest();
        plAutoWbCallPodRequest.setPriority(taskCreateRequest.getPriority());
        plAutoWbCallPodRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plAutoWbCallPodRequest.setTargetPoint(baseMapBerth.getBerCode());
        plAutoWbCallPodRequest.setAreaCode(baseMapBerth.getAreaCode());
        iPlAutoWbCallPodService.plAutoWbCallPod(plAutoWbCallPodRequest);
    }

    /**
     * 任务：补充产线空货架缓存区
     * taskTypeCode: plBufSupply
     */
    public void plBufSupplyFunction(TaskCreateRequest taskCreateRequest){
        logger.info("补充产线空货架缓存区:{}",JSON.toJSONString(taskCreateRequest));
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getTargetPointAlias()), "请填写目标点位");
        //查询点位坐标
        BaseMapBerth baseMapBerth =  baseMapBerthMapper.selectByPointAlias(taskCreateRequest.getTargetPointAlias());
        Preconditions.checkBusinessError(baseMapBerth == null, "目标点位信息为空");

        //校验目标点位和用户登录的点位是否在同一楼层
        //Preconditions.checkBusinessError(baseMapBerth.getAreaCode() != SecurityUtils.getCurrentAreaCode(), "请选择点位楼层创建任务");

        PlBufSupplyRequest plBufSupplyRequest = new PlBufSupplyRequest();
        plBufSupplyRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plBufSupplyRequest.setPriority(taskCreateRequest.getPriority());
        plBufSupplyRequest.setTargetPoint(baseMapBerth.getBerCode());
        plBufSupplyRequest.setAreaCode(baseMapBerth.getAreaCode());
        iPlBufSupplyService.plBufSupply(plBufSupplyRequest);
    }

    /**
     * 任务：产线去老化区搬运
     * taskTypeCode: plToAging
     */
    public void plToAgingFunction(TaskCreateRequest taskCreateRequest){
        logger.info("产线去老化区搬运:{}",JSON.toJSONString(taskCreateRequest));
        String podCode = taskCreateRequest.getPodCode();
        String startPointAlias = taskCreateRequest.getStartPointAlias();
        String targetPointAlias = taskCreateRequest.getTargetPointAlias();
        String startPoint = "";

        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode) && Strings.isNullOrEmpty(startPointAlias), "货架号或起始点坐标不能为空");
        //校验是否传自动还是手动，手动必选目标点
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getSubTaskBizProp()), "请选择自动还是手动");
        if (MANUAL_FIRST.equals(taskCreateRequest.getSubTaskBizProp())){
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(targetPointAlias), "手动模式请选择目标点位");
        }
        if (!Strings.isNullOrEmpty(podCode)){
            //货架不为空，查询所在点位
            BasePodDetail basePodDetail = basePodDetailMapper.selectPodByPodCode(podCode);
            startPoint = basePodDetail.getBerCode();
        }
        if (!Strings.isNullOrEmpty(startPointAlias)){
            //起点不为空，查询点位正在执行任务的货架
            //如果货架为空，查询创建失败
            BaseMapBerth startBaseMapBerth = baseMapBerthMapper.selectByPointAlias(startPointAlias);
            podCode = startBaseMapBerth.getPodCode();
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode), "未查找到货架，任务创建失败！");
        }
        //当前货架所在楼层，对比用户登录楼层权限,如果不在一个楼层创建失败
        //String userAreaCode = SecurityUtils.getCurrentAreaCode();
        //Preconditions.checkBusinessError(!userAreaCode.equals(basePodDetail.getAreaCode()), "用户登录的楼层不能创建该货架任务");

        //校验货架点位是否正确
        Boolean isPointAgreement = iCommonService.checkPodPointAgreement(podCode);
        Preconditions.checkBusinessError(!isPointAgreement, "货架所在位置不正确，请现场确认修改");

        //更改货架空满状态
        basePodDetailMapper.updatePodInStock(podCode,NOT_EMPTY_POD);

        //创建任务
        PlToAgingRequest plToAgingRequest = new PlToAgingRequest();
        plToAgingRequest.setAreaCode(SecurityUtils.getCurrentAreaCode());
        plToAgingRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plToAgingRequest.setPriority(taskCreateRequest.getPriority());
        plToAgingRequest.setPodCode(podCode);
        plToAgingRequest.setStartPoint(startPoint);

        //手动选择目标点
        if (MANUAL_FIRST.equals(taskCreateRequest.getSubTaskBizProp())){
            //查询点位坐标，并上锁
            BaseMapBerth endBaseMapBerth =  baseMapBerthMapper.selectByPointAlias(taskCreateRequest.getTargetPointAlias());
            Preconditions.checkBusinessError(endBaseMapBerth == null, "目标点位信息为空");
            //加锁
            LockStorageDto lockStorageDto = new LockStorageDto();
            lockStorageDto.setMapCode(endBaseMapBerth.getMapCode());
            lockStorageDto.setBerCode(endBaseMapBerth.getBerCode());
            lockStorageDto.setVersion(endBaseMapBerth.getVersion());
            baseMapBerthMapper.lockMapBerth(lockStorageDto);
            plToAgingRequest.setTargetPoint(endBaseMapBerth.getBerCode());
        }
        plToAgingRequest.setSubTaskBizProp(taskCreateRequest.getSubTaskBizProp());
        iPlToAgingService.plagingToQuaInsp(plToAgingRequest);

    }

    /**
     * 任务：老化区前往检验点
     * taskTypeCode: agingToQuaInsp
     */
    public void agingToQuaInspFunction(TaskCreateRequest taskCreateRequest){
        logger.info("老化区前往检验点:{}",JSON.toJSONString(taskCreateRequest));
        String podCode = taskCreateRequest.getPodCode();
        String targetPoint = "";

        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode), "货架号不能为空");
        //根据货架号查询起始点
        BasePodDetail basePodDetail = basePodDetailMapper.selectPodByPodCode(podCode);

        //校验货架点位是否正确
        Boolean isPointAgreement = iCommonService.checkPodPointAgreement(podCode);
        Preconditions.checkBusinessError(!isPointAgreement, "货架所在位置不正确，请现场确认修改");

        //当前货架所在楼层，对比用户登录楼层权限//如果不在一个楼层创建失败
        String userAreaCode = SecurityUtils.getCurrentAreaCode();
        Preconditions.checkBusinessError(!userAreaCode.equals(basePodDetail.getAreaCode()), "用户登录的楼层不能创建该货架任务");

        //获取目标空闲点位，如果没有空闲点，任务创建失败
        //检验区先检验缓存区是否有空闲点，后获工作点是否有空闲
        LockMapBerthCondition cachelockMapBerthCondition = new LockMapBerthCondition();
        cachelockMapBerthCondition.setBizType(QUAINSPCACHEAREA);
        cachelockMapBerthCondition.setMapCode(basePodDetail.getMapCode());
        BaseMapBerth cacheLockMapBerth = iMapResouceService.caculateInspectionWorkAreaEmptyPoint(cachelockMapBerthCondition);
        if (cacheLockMapBerth != null){
            targetPoint = cacheLockMapBerth.getBerCode();
        }else {
            LockMapBerthCondition worklockMapBerthCondition = new LockMapBerthCondition();
            worklockMapBerthCondition.setBizType(QUAINSPWORKAREA);
            worklockMapBerthCondition.setMapCode(basePodDetail.getMapCode());
            BaseMapBerth workLockMapBerth = iMapResouceService.caculateInspectionWorkAreaEmptyPoint(worklockMapBerthCondition);
            if (workLockMapBerth != null){
                targetPoint = workLockMapBerth.getBerCode();
            }else{
                throw new BusinessException("创建任务失败，检验区没有空闲点位！");
            }
        }
        //创建任务
        AgingToQuaInspRequest agingToQuaInspRequest = new AgingToQuaInspRequest();
        agingToQuaInspRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        agingToQuaInspRequest.setPriority(taskCreateRequest.getPriority());
        agingToQuaInspRequest.setPodCode(podCode);
        agingToQuaInspRequest.setAreaCode(SecurityUtils.getCurrentAreaCode());
        agingToQuaInspRequest.setStartPoint(basePodDetail.getBerCode());
        agingToQuaInspRequest.setTargetPoint(targetPoint);
        iAgingToQuaInspService.agingToQuaInsp(agingToQuaInspRequest);

    }

    /**
     * 点到点
     * @param taskCreateRequest
     * @return
     */
    public void pTopFunction(TaskCreateRequest taskCreateRequest){
        logger.info("点到点:{}",JSON.toJSONString(taskCreateRequest));
        String podCode = taskCreateRequest.getPodCode();
        String startPointAlias = taskCreateRequest.getStartPointAlias();
        String targetPointAlias = taskCreateRequest.getTargetPointAlias();
        String startPoint = "";
        String targetPoint = "";

        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode) || Strings.isNullOrEmpty(startPointAlias), "货架号和起始点坐标不能为空");

        //校验货架点位是否正确
        Boolean isPointAgreement = iCommonService.checkPodPointAgreement(podCode);
        Preconditions.checkBusinessError(!isPointAgreement, "货架所在位置不正确，请现场确认修改");

        BaseMapBerth startBaseMapBerth = baseMapBerthMapper.selectByPointAlias(startPointAlias);
        startPoint = startBaseMapBerth.getBerCode();
        //初始化入库
        if (!Strings.isNullOrEmpty(taskCreateRequest.getpTopTaskSubTaskType()) && INIT_STORAGE.equals(taskCreateRequest.getpTopTaskSubTaskType())){
            //筛选目标点，并锁定
            LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
            lockMapBerthCondition.setMapCode(startBaseMapBerth.getMapCode());
            lockMapBerthCondition.setBizType(AGINGREA);
            List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectEmptyStorage(lockMapBerthCondition);
            Preconditions.checkBusinessError(baseMapBerthList.size() < 1, "未找到合适的目标点");
            BaseMapBerth baseMapBerth = baseMapBerthList.get(0);
            targetPoint = baseMapBerth.getBerCode();
        }else{
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(targetPointAlias), "目标点不能为空");
            //查询点位是否有任务或有货架，无，上锁
            BaseMapBerth endBaseMapBerth = baseMapBerthMapper.selectByPointAlias(startPointAlias);
            //TODO 查询模板，两个点是否允许搬运
            //加锁
            LockStorageDto lockStorageDto = new LockStorageDto();
            lockStorageDto.setMapCode(endBaseMapBerth.getMapCode());
            lockStorageDto.setBerCode(endBaseMapBerth.getBerCode());
            lockStorageDto.setVersion(endBaseMapBerth.getVersion());
            baseMapBerthMapper.lockMapBerth(lockStorageDto);
        }

        //当前货架所在楼层，对比用户登录楼层权限//如果不在一个楼层创建失败
        String userAreaCode = SecurityUtils.getCurrentAreaCode();
        Preconditions.checkBusinessError(!userAreaCode.equals(startBaseMapBerth.getAreaCode()), "用户登录的楼层不能创建该货架任务");

        //创建任务
        PToPRequest ptopRequest = new PToPRequest();
        ptopRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        ptopRequest.setPriority(taskCreateRequest.getPriority());
        ptopRequest.setAreaCode(startBaseMapBerth.getAreaCode());
        ptopRequest.setPodCode(podCode);
        ptopRequest.setStartPoint(startPoint);
        ptopRequest.setTargetPoint(targetPoint);
        ipToPService.pTop(ptopRequest);
    }
    
    /**
     * 检验缓冲区去检验点
     * @param  taskCreateRequest
     * @return 
     */
    public Result quaBufToQuaFunction(TaskCreateRequest taskCreateRequest){
        logger.info("检验缓冲区去检验点:{}",JSON.toJSONString(taskCreateRequest));
        String podCode = taskCreateRequest.getPodCode();
        String startPointAlias = taskCreateRequest.getStartPointAlias();
        String startPoint = "";

        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode) && Strings.isNullOrEmpty(startPointAlias), "货架号和起始点坐标不能为空");

        //校验货架点位是否正确
        Boolean isPointAgreement = iCommonService.checkPodPointAgreement(podCode);
        Preconditions.checkBusinessError(!isPointAgreement, "货架所在位置不正确，请现场确认修改");

        BaseMapBerth startBaseMapBerth = baseMapBerthMapper.selectByPointAlias(startPointAlias);
        startPoint = startBaseMapBerth.getBerCode();

        //创建任务
        QuaBufToQuaRequest quaBufToQuaRequest = new QuaBufToQuaRequest();
        quaBufToQuaRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        quaBufToQuaRequest.setPriority(taskCreateRequest.getPriority());
        quaBufToQuaRequest.setAreaCode(startBaseMapBerth.getAreaCode());
        quaBufToQuaRequest.setPodCode(podCode);
        quaBufToQuaRequest.setStartPoint(startPoint);
        iQuaBufToQuaService.quaBufToQua(quaBufToQuaRequest);
        return new Result();
    }

    /**
     * 添加主任务
     * @param
     * @return
     */
    @Override
    public String mainTaskCommonAdd(String taskTypeCode, String areaCode, Integer priority){
        String mainTaskNum = "";
        //创建主任务
        MainTask mainTaskCreate = new MainTask();
        mainTaskNum = CodeBuilder.codeBuilder("M");
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setCreateDate(new Date());
        mainTaskCreate.setPriority(priority);
        mainTaskCreate.setMainTaskTypeCode(taskTypeCode);
        mainTaskCreate.setAreaCode(areaCode);
        mainTaskCreate.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskMapper.insertSelective(mainTaskCreate);

        return mainTaskNum;
    }

    /**
     * 添加子任务条件
     * @param mainTaskTypeCode,subTaskTypeCode,subTaskNum
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
