package com.wisdom.iwcs.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.FloorMapEnum;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.common.utils.taskUtils.TaskContextUtils;
import com.wisdom.iwcs.common.utils.taskUtils.TaskPriorityEnum;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.BaseWhArea;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.elevator.Elevator;
import com.wisdom.iwcs.domain.elevator.ElevatorTaskRequest;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.domain.task.dto.ContextDTO;
import com.wisdom.iwcs.domain.upstream.mes.AgvHandlingTaskCreateRequest;
import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.base.BaseWhAreaMapper;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.mapper.elevator.ElevatorMapper;
import com.wisdom.iwcs.mapper.task.*;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.intf.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.PongMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.AgingAreaPriorityProp.MANUAL_FIRST;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.*;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskWorkType.ELE_DOWN;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.*;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PodInStockConstants.EMPTY_POD;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PodInStockConstants.NOT_EMPTY_POD;
import static com.wisdom.iwcs.common.utils.TaskConstants.mainTaskStatus.MAIN_NOT_ISSUED;
import static com.wisdom.iwcs.common.utils.TaskConstants.pTopTaskSubTaskTypeConstants.INIT_STORAGE;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.*;
import static com.wisdom.iwcs.domain.upstream.mes.MesResult.NG;

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
    @Autowired
    private TaskPointBlackRuleMapper taskPointBlackRuleMapper;
    @Autowired
    private BaseWhAreaMapper baseWhAreaMapper;
    @Autowired
    private IQuaInspToElvBufService iQuaInspToElvBufService;
    @Autowired
    private IElevatorTaskService iElevatorTaskService;
    @Autowired
    private IPackWbCallPodService iPackWbCallPodService;
    @Autowired
    private ElevatorMapper elevatorMapper;
    @Autowired
    private EleControlTaskMapper eleControlTaskMapper;
    @Autowired
    private BaseConnectionPointMapper baseConnectionPointMapper;
    @Autowired
    private TaskRelMapper taskRelMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private ITaskCreateService iTaskCreateService;
    @Autowired
    private TaskContextMapper taskContextMapper;

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
        taskCreateRequest.setPriority(mainTaskType.getPriority());
        switch (taskTypeCode){
            case PLAUTOWBCALLPOD:
                plAutoWbCallPodFunction(taskCreateRequest);
                break;
            case PLBUFSUPPLY:
                plBufSupplyFunction(taskCreateRequest);
                break;
            case PLTOAGING:
                plToAgingFunction(taskCreateRequest);
                break;
            case AGINGTOQUAINSP:
                agingToQuaInspFunction(taskCreateRequest);
                break;
            case PTOP:
                pTopFunction(taskCreateRequest);
                break;
            case QUABUFTOQUA:
                quaBufToQuaFunction(taskCreateRequest);
                break;
            case QUAINSPTOELVBUF:
                quaInspToElvBufFunction(taskCreateRequest);
                break;
            case ELVBUFTOPACKBUF:
                elevatorTaskFunction(taskCreateRequest);
                break;
            case PACKWBCALLPOD:
                packWbCallPodFunction(taskCreateRequest);
                break;
            case PACKTOPLORAGING:
                taskCreateRequest.setTaskTypeCode(ELVBUFTOPACKBUF);
                elevatorTaskFunction(taskCreateRequest);
                break;
            case PTOPWITHOUTPODCHECK:
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
     * 任务：产线作业区呼叫空货架
     * 参数：目标点(上锁)
     * 后置条件：计算起点，货架(上锁)
     * taskTypeCode: plAutoWbCallPod
     */
    public void plAutoWbCallPodFunction(TaskCreateRequest taskCreateRequest){
        logger.info("产线作业区呼叫空货架:{}",JSON.toJSONString(taskCreateRequest));
        String targetPoint = "";
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getTargetPointAlias()), "请填写点位编号");
        //查询点位坐标
        BaseMapBerth baseMapBerth =  baseMapBerthMapper.selectByPointAlias(taskCreateRequest.getTargetPointAlias());
        Preconditions.checkBusinessError(baseMapBerth == null, "无效目标点编码" + taskCreateRequest.getTargetPointAlias());
        targetPoint = baseMapBerth.getBerCode();

        Preconditions.checkBusinessError(!LINEAREA.equals(baseMapBerth.getOperateAreaCode()), "点位不属于线体区域");

        Preconditions.checkBusinessError(iCommonService.checkBerTask(targetPoint), baseMapBerth.getPointAlias() + " 该目标点有正在执行的任务或已经有货架！");

        //校验目标点位和用户登录的点位是否在同一楼层
//        Preconditions.checkBusinessError(baseMapBerth.getAreaCode() != SecurityUtils.getCurrentAreaCode(), "请选择点位楼层创建任务");

        PlAutoWbCallPodRequest plAutoWbCallPodRequest = new PlAutoWbCallPodRequest();
        plAutoWbCallPodRequest.setPriority(taskCreateRequest.getPriority());
        plAutoWbCallPodRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plAutoWbCallPodRequest.setTargetPoint(baseMapBerth.getBerCode());
        plAutoWbCallPodRequest.setAreaCode(baseMapBerth.getAreaCode());
        plAutoWbCallPodRequest.setEndAlias(taskCreateRequest.getTargetPointAlias());
        iPlAutoWbCallPodService.plAutoWbCallPod(plAutoWbCallPodRequest);
    }

    /**
     * 任务：补充产线空货架缓存区
     * 参数：areaCode
     * 后置条件：计算起点，目标点(上锁),货架(上锁)
     * taskTypeCode: plBufSupply
     */
    public void plBufSupplyFunction(TaskCreateRequest taskCreateRequest){
        logger.info("补充产线空货架缓存区:{}",JSON.toJSONString(taskCreateRequest));
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getAreaCode()), "请填写需补充货架的楼层");
        //查询楼层是否存在
        BaseWhArea baseWhArea = baseWhAreaMapper.selectByAreaCodeAndDeleteFlag(taskCreateRequest.getAreaCode(),0);
        Preconditions.checkBusinessError(baseWhArea == null, "楼层不存在");

        //校验目标点位和用户登录的点位是否在同一楼层
//        Preconditions.checkBusinessError(taskCreateRequest.getAreaCode() != SecurityUtils.getCurrentAreaCode(), "请选择点位楼层创建任务");

        PlBufSupplyRequest plBufSupplyRequest = new PlBufSupplyRequest();
        plBufSupplyRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plBufSupplyRequest.setPriority(taskCreateRequest.getPriority());
        plBufSupplyRequest.setAreaCode(taskCreateRequest.getAreaCode());
        iPlBufSupplyService.plBufSupply(plBufSupplyRequest);
    }



    /**
     * 任务：产线去老化区搬运
     * 参数：货架号或起始点，目标点手动有、自动无
     * 前置条件：
     * 后置条件：
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
            startPoint = basePodDetailMapper.selectBerCodeByPodCode(podCode);
            Preconditions.checkBusinessError(startPoint == null, "货架位置信息错误");
        }
        if (!Strings.isNullOrEmpty(startPointAlias)){
            //起点不为空，查询点位正在执行任务的货架
            //如果货架为空，查询创建失败
            podCode = baseMapBerthMapper.selectBerCodeByPodCode(startPointAlias);
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode), "该点位未查找到货架，任务创建失败！");
            startPoint = basePodDetailMapper.selectBerCodeByPodCode(podCode);
        }

        //当前货架所在楼层，对比用户登录楼层权限,如果不在一个楼层创建失败
        BasePodDetail basePodDetail = basePodDetailMapper.selectPodByPodCode(podCode);
        String userAreaCode = SecurityUtils.getCurrentAreaCode();
        if (!Strings.isNullOrEmpty(userAreaCode)){
            Preconditions.checkBusinessError(!userAreaCode.equals(basePodDetail.getAreaCode()), "用户登录的楼层不能创建该货架任务");
        }

        //校验货架点位是否正确
        Boolean isPointAgreement = iCommonService.checkPodPointAgreement(podCode);
        Preconditions.checkBusinessError(!isPointAgreement, "货架所在位置不正确，请现场确认修改");
        Preconditions.checkBusinessError(iCommonService.checkPodTask(podCode), "该货架正在执行任务！");

        //更改货架空满状态
        basePodDetailMapper.updateInStock(podCode,NOT_EMPTY_POD);

        //创建任务
        PlToAgingRequest plToAgingRequest = new PlToAgingRequest();
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(startPoint);
        plToAgingRequest.setAreaCode(baseMapBerth.getAreaCode());
        plToAgingRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        plToAgingRequest.setPriority(taskCreateRequest.getPriority());
        plToAgingRequest.setPodCode(podCode);
        plToAgingRequest.setStartPoint(startPoint);
        plToAgingRequest.setStartPointAlias(taskCreateRequest.getStartPointAlias());
        plToAgingRequest.setEndBercodeAuto("1");
        //手动选择目标点
        if (MANUAL_FIRST.equals(taskCreateRequest.getSubTaskBizProp())){
            //查询点位坐标，并上锁
            BaseMapBerth endBaseMapBerth =  baseMapBerthMapper.selectByPointAlias(targetPointAlias);
            Preconditions.checkBusinessError(endBaseMapBerth == null, "目标点位信息为空");

            Preconditions.checkBusinessError(!AGINGREA.equals(endBaseMapBerth.getOperateAreaCode()), "选择的点位不属于老化区");

            Preconditions.checkBusinessError(iCommonService.checkBerTask(endBaseMapBerth.getBerCode()), targetPointAlias + " 该目标点有正在执行的任务或已经有货架！");
            //加锁
            LockStorageDto lockStorageDto = new LockStorageDto();
            lockStorageDto.setMapCode(endBaseMapBerth.getMapCode());
            lockStorageDto.setBerCode(endBaseMapBerth.getBerCode());
            lockStorageDto.setVersion(endBaseMapBerth.getVersion());
            baseMapBerthMapper.lockMapBerth(lockStorageDto);

            plToAgingRequest.setTargetPoint(endBaseMapBerth.getBerCode());
            plToAgingRequest.setTargetPointAlias(taskCreateRequest.getStartPointAlias());
            plToAgingRequest.setEndBercodeAuto("0");
        }
        plToAgingRequest.setSubTaskBizProp(taskCreateRequest.getSubTaskBizProp());
        iPlToAgingService.plagingToQuaInsp(plToAgingRequest);

    }

    /**
     * 任务：老化区前往检验点
     * 优先去检验区，检验区无空，去检验缓存区
     * 参数：计算起点，目标点(上锁),货架(上锁)
     * 前置条件：
     * 后置条件：
     * taskTypeCode: agingToQuaInsp
     */
    public void agingToQuaInspFunction(TaskCreateRequest taskCreateRequest){
        logger.info("老化区前往检验点:{}",JSON.toJSONString(taskCreateRequest));
        String podCode = taskCreateRequest.getPodCode();
        String startPoint = "";
        String targetPoint = "";
        String targetPointAlias = "";

        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode), "货架号不能为空");
        //根据货架号查询起始点
        BasePodDetail basePodDetail = basePodDetailMapper.selectPodByPodCode(podCode);
        Preconditions.checkBusinessError(basePodDetail == null, "未查询到该货架号:" + podCode);
        startPoint = basePodDetail.getBerCode();

        //校验货架点位是否正确
        Boolean isPointAgreement = iCommonService.checkPodPointAgreement(podCode);
        Preconditions.checkBusinessError(!isPointAgreement, "货架所在位置不正确，请现场确认修改");

        Preconditions.checkBusinessError(iCommonService.checkPodTask(podCode), "该货架正在执行任务！");

        //当前货架所在楼层，对比用户登录楼层权限//如果不在一个楼层创建失败
        String userAreaCode = SecurityUtils.getCurrentAreaCode();
        if (!Strings.isNullOrEmpty(userAreaCode)){
            Preconditions.checkBusinessError(!userAreaCode.equals(basePodDetail.getAreaCode()), "用户登录的楼层不能创建该货架任务");
        }

        //获取目标空闲点位，如果没有空闲点，任务创建失败
        //检验区先检验缓存区是否有空闲点，后获工作点是否有空闲

        LockMapBerthCondition worklockMapBerthCondition = new LockMapBerthCondition();
        worklockMapBerthCondition.setBizType(QUAINSPWORKAREA);
        worklockMapBerthCondition.setMapCode(basePodDetail.getMapCode());
        BaseMapBerth workLockMapBerth = iMapResouceService.caculateInspectionWorkAreaEmptyPoint(worklockMapBerthCondition);
        String areaCode;
        if (workLockMapBerth != null){
            targetPoint = workLockMapBerth.getBerCode();
            targetPointAlias = workLockMapBerth.getPointAlias();
            areaCode = workLockMapBerth.getAreaCode();
        }else{
            LockMapBerthCondition cachelockMapBerthCondition = new LockMapBerthCondition();
            cachelockMapBerthCondition.setBizType(QUAINSPCACHEAREA);
            cachelockMapBerthCondition.setPodCode(taskCreateRequest.getPodCode());
            cachelockMapBerthCondition.setMapCode(basePodDetail.getMapCode());
            BaseMapBerth cacheLockMapBerth = iMapResouceService.caculateInspectionWorkAreaEmptyPoint(cachelockMapBerthCondition);
            if (cacheLockMapBerth != null){
                targetPoint = cacheLockMapBerth.getBerCode();
                targetPointAlias = cacheLockMapBerth.getPointAlias();
                areaCode = cacheLockMapBerth.getAreaCode();
            }else {
                throw new BusinessException("创建任务失败，检验区没有空闲点位！");
            }
        }

        Preconditions.checkBusinessError(iCommonService.checkBerTask(targetPoint), targetPointAlias+" 该目标点有正在执行的任务或已经有货架！");
        //创建任务
        AgingToQuaInspRequest agingToQuaInspRequest = new AgingToQuaInspRequest();
        agingToQuaInspRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        agingToQuaInspRequest.setPriority(taskCreateRequest.getPriority());
        agingToQuaInspRequest.setPodCode(podCode);
        agingToQuaInspRequest.setAreaCode(areaCode);
        agingToQuaInspRequest.setStartPoint(startPoint);
        agingToQuaInspRequest.setTargetPoint(targetPoint);
        agingToQuaInspRequest.setMapCode(basePodDetail.getMapCode());
        iAgingToQuaInspService.agingToQuaInsp(agingToQuaInspRequest);

    }

    /**
     * 点到点 / 初始化入库
     * 参数：计算起点，目标点(上锁),货架(上锁)
     * 参数：起点、货架号、目标点
     * 初始化入库：参数 货架号、起始点 必传
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

        Preconditions.checkBusinessError(Strings.isNullOrEmpty(startPointAlias), "起始点不能为空");

        BaseMapBerth startBaseMapBerth = new BaseMapBerth();
        if (!Strings.isNullOrEmpty(startPointAlias)){
            startBaseMapBerth = baseMapBerthMapper.selectByPointAlias(startPointAlias);
            Preconditions.checkBusinessError(startBaseMapBerth == null, "根据起点点位编号获取点位信息为空");
            startPoint = startBaseMapBerth.getBerCode();
        }

        //初始化入库
        if (!Strings.isNullOrEmpty(taskCreateRequest.getpTopTaskSubTaskType()) && INIT_STORAGE.equals(taskCreateRequest.getpTopTaskSubTaskType())){
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode), "货架号不能为空");
            //校验货架号是否为未初始化货架
            BasePodDetail basePodDetail = basePodDetailMapper.selectUnInitPodByPodCode(podCode);
            Preconditions.checkBusinessError(basePodDetail==null, "初始化货架：IWCS中未查找到该货架");
            //更新货架原始楼层
            basePodDetailMapper.updateSourceMapByPodCode(podCode,startBaseMapBerth.getMapCode(), startBaseMapBerth.getAreaCode());

            //筛选目标点，锁定放在创建子任务中
            LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
            lockMapBerthCondition.setMapCode(startBaseMapBerth.getMapCode());
            lockMapBerthCondition.setOperateAreaCode(AGINGREA);
            List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectEmptyStorage(lockMapBerthCondition);
            Preconditions.checkBusinessError(baseMapBerthList.size() < 1, "未找到合适的目标点");
            BaseMapBerth baseMapBerth = baseMapBerthList.get(0);
            targetPoint = baseMapBerth.getBerCode();
        }else{
            //当前货架所在楼层，对比用户登录楼层权限//如果不在一个楼层创建失败
            String userAreaCode = SecurityUtils.getCurrentAreaCode();
            if (!Strings.isNullOrEmpty(userAreaCode)){
                Preconditions.checkBusinessError(!userAreaCode.equals(startBaseMapBerth.getAreaCode()), "用户登录的楼层不能创建该货架任务");
            }

            podCode = startBaseMapBerth.getPodCode();
            Preconditions.checkBusinessError(podCode == null, "根据起点点位编号获取货架信息为空");

            Preconditions.checkBusinessError(Strings.isNullOrEmpty(targetPointAlias), "目标点不能为空");
            //查询点位是否有任务或有货架，无，上锁
            BaseMapBerth endBaseMapBerth = baseMapBerthMapper.selectByPointAlias(targetPointAlias);
            Preconditions.checkBusinessError(endBaseMapBerth == null, "根据目标点位编号获取点位信息为空");
            targetPoint = endBaseMapBerth.getBerCode();

            Preconditions.checkBusinessError(iCommonService.checkBerTask(targetPoint), endBaseMapBerth.getPointAlias() + " 该目标点有正在执行的任务或已经有货架！");

            //TODO 查询模板，两个点是否允许搬运
            TaskPointBlackRule taskPointBlackRule = new TaskPointBlackRule();
            taskPointBlackRule.setStartOperateArea(startBaseMapBerth.getOperateAreaCode());
            taskPointBlackRule.setStartBizType(startBaseMapBerth.getBizType());
            taskPointBlackRule.setStartBizSecondArea(startBaseMapBerth.getBizSecondAreaCode());
            taskPointBlackRule.setTargetOperateArea(endBaseMapBerth.getOperateAreaCode());
            taskPointBlackRule.setTargetBizType(endBaseMapBerth.getBizType());
            taskPointBlackRule.setTargetBizSecondArea(endBaseMapBerth.getBizSecondAreaCode());
            List<TaskPointBlackRule> taskPointBlackRuleList = taskPointBlackRuleMapper.selectBlackRule(taskPointBlackRule);
            Preconditions.checkBusinessError(taskPointBlackRuleList.size() > 0, "目标点不允许创建该起点的搬运任务");

            //加锁
            LockStorageDto lockStorageDto = new LockStorageDto();
            lockStorageDto.setMapCode(endBaseMapBerth.getMapCode());
            lockStorageDto.setBerCode(endBaseMapBerth.getBerCode());
            lockStorageDto.setVersion(endBaseMapBerth.getVersion());
            baseMapBerthMapper.lockMapBerth(lockStorageDto);
        }

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
     * 参数：计算起点/货架(上锁)
     * 前置条件：货架没任务
     * 后置条件：目标点(上锁)
     * @param  taskCreateRequest
     * @return 
     */
    public Result quaBufToQuaFunction(TaskCreateRequest taskCreateRequest){
        logger.info("检验缓冲区去检验点:{}",JSON.toJSONString(taskCreateRequest));
//        String podCode = taskCreateRequest.getPodCode();
//        String startPointAlias = taskCreateRequest.getStartPointAlias();
//        String startPoint = "";

//        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode) || Strings.isNullOrEmpty(startPointAlias), "货架号和起始点坐标不能为空");
//
//        //校验货架点位是否正确
//        Boolean isPointAgreement = iCommonService.checkPodPointAgreement(podCode);
//        Preconditions.checkBusinessError(!isPointAgreement, "货架所在位置不正确，请现场确认修改");
//
//        BaseMapBerth startBaseMapBerth = baseMapBerthMapper.selectByPointAlias(startPointAlias);
//        Preconditions.checkBusinessError(startBaseMapBerth == null, "根据点位编号获取点位信息为空");
//        startPoint = startBaseMapBerth.getBerCode();

        Preconditions.checkBusinessError(Strings.isNullOrEmpty(taskCreateRequest.getAreaCode()), "请填写需补充货架的楼层");
        //查询楼层是否存在
        BaseWhArea baseWhArea = baseWhAreaMapper.selectByAreaCodeAndDeleteFlag(taskCreateRequest.getAreaCode(),0);
        Preconditions.checkBusinessError(baseWhArea == null, "楼层不存在");

        //创建任务
        QuaBufToQuaRequest quaBufToQuaRequest = new QuaBufToQuaRequest();
        quaBufToQuaRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        quaBufToQuaRequest.setPriority(taskCreateRequest.getPriority());
        quaBufToQuaRequest.setAreaCode(taskCreateRequest.getAreaCode());
        iQuaBufToQuaService.quaBufToQua(quaBufToQuaRequest);
        return new Result();
    }

    /**
     * 检验区到电梯缓存区
     * 前置条件：必须同楼层，电梯缓存区有空位置
     * 必要参数：货架号
     * 触发：PDA
     * @param taskCreateRequest
     * @return
     */
    public Result quaInspToElvBufFunction(TaskCreateRequest taskCreateRequest){
        String podCode = taskCreateRequest.getPodCode();
        String targetPoint = "";
        String startPoint = "";
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode), "货架号不能为空");

        //根据货架号查询起始点
        BasePodDetail basePodDetail = basePodDetailMapper.selectPodByPodCode(podCode);
        Preconditions.checkBusinessError(basePodDetail == null, "未查询到该货架号");

        Preconditions.checkBusinessError(iCommonService.checkPodTask(podCode), "该货架正在执行任务！");

        BaseMapBerth startPointMapBerth = baseMapBerthMapper.selectOneByBercode(basePodDetail.getBerCode());
        startPoint = startPointMapBerth.getBerCode();

        //校验货架点位是否正确
        Boolean isPointAgreement = iCommonService.checkPodPointAgreement(podCode);
        Preconditions.checkBusinessError(!isPointAgreement, "货架所在位置不正确，请现场确认修改");

        //当前货架所在楼层，对比用户登录楼层权限//如果不在一个楼层创建失败
        String userAreaCode = SecurityUtils.getCurrentAreaCode();
        if (!Strings.isNullOrEmpty(userAreaCode)){
            Preconditions.checkBusinessError(!userAreaCode.equals(basePodDetail.getAreaCode()), "用户登录的楼层不能创建该货架任务");
        }
        //筛选目标点，锁定放在创建子任务中
        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
        lockMapBerthCondition.setMapCode(startPointMapBerth.getMapCode());
        lockMapBerthCondition.setOperateAreaCode(ELEVATORAREA);
        lockMapBerthCondition.setBizType(ELEVATORCACHEAREA);
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectEmptyStorage(lockMapBerthCondition);
        Preconditions.checkBusinessError(baseMapBerthList.size() < 1, "电梯缓存区未找到空闲位置");
        BaseMapBerth baseMapBerth = baseMapBerthList.get(0);
        targetPoint = baseMapBerth.getBerCode();

        //创建任务
        QuaInspToElvBufRequest quaInspToElvBufRequest = new QuaInspToElvBufRequest();
        quaInspToElvBufRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        quaInspToElvBufRequest.setPriority(taskCreateRequest.getPriority());
        quaInspToElvBufRequest.setAreaCode(basePodDetail.getAreaCode());
        quaInspToElvBufRequest.setPodCode(podCode);
        quaInspToElvBufRequest.setStartPoint(startPoint);
        quaInspToElvBufRequest.setTargetPoint(targetPoint);
        iQuaInspToElvBufService.quaInspToElvBuf(quaInspToElvBufRequest);

        return new Result();
    }

    /**
     * 电梯缓存区到一楼包装线体缓存区 / 一楼包装线体区到货架原有楼层线体缓存区或老化区
     * 前置条件：包装缓存区空，无进行中的电梯任务 / 计算线
     * 体缓存区/老化区的空点、锁定，无进行中的电梯任务 packToPlorAging
     * 必要参数：货架号,起始楼层,目标楼层，终点
     * 触发：自动 传目标点 /  手动 筛选目标点
     * @param taskCreateRequest
     * @return
     */
    public Result elevatorTaskFunction(TaskCreateRequest taskCreateRequest){
        String podCode = taskCreateRequest.getPodCode();
        String sourceFloor = taskCreateRequest.getSourceFloor();
        String destFloor = "";
        String eleWorkType = taskCreateRequest.getEleWorkType();
        String startPoint = "";
        String targetPoint = "";
        String targetPointAlias = taskCreateRequest.getTargetPointAlias();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode), "货架号不能为空");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(sourceFloor), "起始楼层不能为空");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(eleWorkType), "电梯作业类型不能为空");

        Preconditions.checkBusinessError(iCommonService.checkPodTask(podCode), "该货架正在执行任务！");

        //校验货架点位是否正确
        Boolean isPointAgreement = iCommonService.checkPodPointAgreement(podCode);
        Preconditions.checkBusinessError(!isPointAgreement, "货架所在位置不正确，请现场确认修改");

        //通过梯控 校验是否有正在进行中的任务
        long countUnEndTask = eleControlTaskMapper.countUnEndTask();
        Preconditions.checkBusinessError(countUnEndTask > 0, "电梯有未完结的任务，不能创建");

        //查询电梯状态是否正常
        Elevator elevator = elevatorMapper.selectEleStatus("1");
        Preconditions.checkBusinessError(!elevator.getEleStatus().equals("1"), "电梯故障，不能执行电梯任务");

        //根据货架号查询起始点
        BasePodDetail basePodDetail = basePodDetailMapper.selectPodByPodCode(podCode);
        Preconditions.checkBusinessError(basePodDetail == null, "未查询到该货架号");
        startPoint = basePodDetail.getBerCode();

        //根据上楼或下楼和货架号计算进电梯子任务目标点
        if (eleWorkType.equals(ELE_DOWN)){
            //查询一楼的电梯交接点
            String mapCode = FloorMapEnum.returnTypeByMapValue(1);
            destFloor = "1";
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(targetPointAlias), "自动创建电梯任务，目标点不可为空");
            BaseMapBerth targetBaseMapBerth = baseMapBerthMapper.selectByPointAlias(targetPointAlias);
            targetPoint = targetBaseMapBerth.getBerCode();

        }else{
            //查询货架的初始楼层
            BasePodDetail basePodDetail1 = basePodDetailMapper.selectByPodCode(podCode);
            Integer floor = FloorMapEnum.returnMapValueByType(basePodDetail1.getPodProp2());
            destFloor = floor.toString();

            //更改货架空满状态
            basePodDetailMapper.updateInStock(podCode,EMPTY_POD);

            //根据货架原所在楼层的mapCode筛选目标点，锁定放在创建子任务中
            LockMapBerthCondition lineLockMapBerthCondition = new LockMapBerthCondition();
            lineLockMapBerthCondition.setMapCode(basePodDetail1.getPodProp2());
            lineLockMapBerthCondition.setOperateAreaCode(LINEAREA);
            lineLockMapBerthCondition.setBizType(LINECACHEAREA);
            List<BaseMapBerth> lineMapBerthList = baseMapBerthMapper.selectEmptyStorage(lineLockMapBerthCondition);
            if (lineMapBerthList.size() < 1 ){
                LockMapBerthCondition agingLockMapBerthCondition = new LockMapBerthCondition();
                agingLockMapBerthCondition.setMapCode(basePodDetail1.getPodProp2());
                agingLockMapBerthCondition.setOperateAreaCode(AGINGREA);
                List<BaseMapBerth> agingMapBerthList = baseMapBerthMapper.selectEmptyStorage(agingLockMapBerthCondition);
                Preconditions.checkBusinessError(agingMapBerthList.size() < 1, "货架所在楼层没有空储位了");
                targetPoint = agingMapBerthList.get(0).getBerCode();
            }else{
                targetPoint = lineMapBerthList.get(0).getBerCode();
            }
        }

        //创建任务
        ElevatorTaskRequest elevatorTaskRequest = new ElevatorTaskRequest();
        elevatorTaskRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        elevatorTaskRequest.setPriority(taskCreateRequest.getPriority());
        elevatorTaskRequest.setPodCode(podCode);
        elevatorTaskRequest.setStartPoint(startPoint);
        elevatorTaskRequest.setTargetPoint(targetPoint);
        elevatorTaskRequest.setSourceFloor(sourceFloor);
        elevatorTaskRequest.setDestFloor(destFloor);
        elevatorTaskRequest.setEleWorkType(eleWorkType);
        iElevatorTaskService.elevatorTask(elevatorTaskRequest);

        return new Result();
    }

    /**
     * 一楼包装线体区呼叫满货货架
     * 前置条件：必须同楼层，包装区点位置空
     * 必要参数：起始点位,
     * 触发：PDA
     * @param taskCreateRequest
     * @return
     */
    public Result packWbCallPodFunction(TaskCreateRequest taskCreateRequest){
        String startPoint = "";
        String podCode = "";
        String targetPoint = "";

        String startPointAlias = taskCreateRequest.getStartPointAlias();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(startPointAlias), "起始点不能为空");

        //校验起始点是否有货，获取货架
        BaseMapBerth startBaseMapBerth = baseMapBerthMapper.selectByPointAlias(startPointAlias);
        Preconditions.checkBusinessError(startBaseMapBerth == null || startBaseMapBerth.getPodCode() == null, "根据起点点位编号获取点位信息为空");
        startPoint = startBaseMapBerth.getBerCode();
        podCode = startBaseMapBerth.getPodCode();

        Preconditions.checkBusinessError(iCommonService.checkPodTask(podCode), "该货架正在执行任务！");

        //一楼包装线体1个点，自动获取目标点
        //todo 多个要修改
        //校验目标点 有货架或有任务返错
        List<BaseMapBerth> endBaseMapBerths = baseMapBerthMapper.selectByBizTye(PAGEWORKAREA);
        Preconditions.checkBusinessError(endBaseMapBerths == null || endBaseMapBerths.size() <= 0, "无包装体工作区");
        BaseMapBerth endBaseMapBerth = endBaseMapBerths.get(0);
        Preconditions.checkBusinessError(!Strings.isNullOrEmpty(endBaseMapBerth.getPodCode()), "包装线货架不能有货架");
        targetPoint = endBaseMapBerth.getBerCode();
        Preconditions.checkBusinessError(iCommonService.checkBerTask(targetPoint), endBaseMapBerth.getPointAlias()+"该目标点有正在执行的任务或已经有货架！");

        //当前货架所在楼层，对比用户登录楼层权限//如果不在一个楼层创建失败
        String userAreaCode = SecurityUtils.getCurrentAreaCode();
        if (!Strings.isNullOrEmpty(userAreaCode)){
            Preconditions.checkBusinessError(!userAreaCode.equals(startBaseMapBerth.getAreaCode()), "用户登录的楼层不能创建该货架任务");
        }

        //创建任务
        PackWbCallPodRequest packWbCallPodRequest = new PackWbCallPodRequest();
        packWbCallPodRequest.setTaskTypeCode(taskCreateRequest.getTaskTypeCode());
        packWbCallPodRequest.setPriority(taskCreateRequest.getPriority());
        packWbCallPodRequest.setAreaCode(startBaseMapBerth.getAreaCode());
        packWbCallPodRequest.setPodCode(podCode);
        packWbCallPodRequest.setStartPoint(startPoint);
        packWbCallPodRequest.setTargetPoint(targetPoint);
        iPackWbCallPodService.elevatorTask(packWbCallPodRequest);

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
    /**
     * 添加子任务条件
     * @param
     * @return
     */
    public void addSubTaskCondition(String templCode, String subTaskNum){
        //通过主任务编号和子任务编号查询
        List<TaskRelCondition> taskRelConditionList = taskRelConditionMapper.selectByTemplCode(templCode);
        for (TaskRelCondition taskRelCondition: taskRelConditionList){
            SubTaskCondition subTaskCondition = new SubTaskCondition();
            subTaskCondition.setSubTaskNum(subTaskNum);
            subTaskCondition.setSubscribeEvent(taskRelCondition.getSubscribeEvent());
            subTaskCondition.setConditonTriger(taskRelCondition.getConditonTriger());
            subTaskCondition.setConditonHandler(taskRelCondition.getConditonHandler());
            subTaskCondition.setCreateDate(new Date());
            subTaskConditionMapper.insertSelective(subTaskCondition);
        }
    }



    /**
     * 创建基础滚筒AGV移动任务
     * @param createTaskRequest
     * @return
     */
    public MesResult rollerTaskCreate(CreateTaskRequest createTaskRequest, String mainTaskType) {
        //1.参数校验
        publicCheckIsBlank(createTaskRequest);

        //2.创建主任务
        String mainTaskNum = createTaskRequest.getTaskCode();
        MainTask mainTaskCreate = new MainTask();
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setCreateDate(new Date());
        mainTaskCreate.setPriority(TaskPriorityEnum.getPriorityByCode(createTaskRequest.getTaskPri()));
        mainTaskCreate.setMainTaskTypeCode(mainTaskType);
        mainTaskCreate.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskCreate.setStaticPodCode(createTaskRequest.getStaticPodCode());
        mainTaskCreate.setStaticViaPaths(createTaskRequest.getStaticViaPaths());
        mainTaskMapper.insertSelective(mainTaskCreate);

//        //3.创建子任务
//        List<TaskRel> taskRelList = taskRelMapper.selectByMainTaskType(mainTaskType);
//        TaskRel taskRel = taskRelList.get(0);
//        SubTask subTaskCreate = new SubTask();
//        String subTaskNum = CodeBuilder.codeBuilder("S");
//        subTaskCreate.setMainTaskNum(mainTaskNum);
//        subTaskCreate.setSubTaskNum(subTaskNum);
//        subTaskCreate.setSubTaskTyp(taskRel.getSubTaskTypeCode());
//        subTaskCreate.setCreateDate(new Date());
//        subTaskCreate.setMainTaskSeq(taskRel.getSubTaskSeq());
//        subTaskCreate.setMainTaskType(taskRel.getMainTaskTypeCode());
//        subTaskCreate.setThirdType(taskRel.getThirdType());
//        subTaskCreate.setSendStatus(SUB_NOT_ISSUED);
//        subTaskCreate.setTaskStatus(SUB_NOT_ISSUED);
//        subTaskCreate.setWorkerTaskCode(subTaskNum);
//        subTaskCreate.setEndBercode(createTaskRequest.getSupplyLoadWb() != null ? createTaskRequest.getSupplyLoadWb(): createTaskRequest.getSrcWbCode());
//        subTaskMapper.insertSelective(subTaskCreate);
//
//        //4.创建子任务前置后置条件
//        iTaskCreateService.subTaskConditionCommonAdd(taskRel.getMainTaskTypeCode(), taskRel.getSubTaskTypeCode(), subTaskNum);

        //5.创建子任务共享数据区域--任务上下文
        TaskContext taskContext = new TaskContext();
        taskContext.setMainTaskNum(mainTaskNum);
        taskContext.setCreateTime(new Date());
        taskContextMapper.insertSelective(taskContext);

        //6.向消息队列发送消息
//        MainTaskType tmpMainTaskType = mainTaskTypeMapper.selectByMainTaskTypeCode(mainTaskType);
//        String message = tmpMainTaskType.getMainTaskTypeName() + "任务创建完成,主任务号:" + mainTaskNum;
//        RabbitMQPublicService.successTaskLog(new TaskOperationLog(subTaskNum, TaskConstants.operationStatus.CREATE_TASK,message));

        return new MesResult();
    }

    /**
     * 创建自动产线供料、回收任务时独有的创建动作
     * @param createTaskRequest
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MesResult supplyAndRecycle(CreateTaskRequest createTaskRequest, String mainTaskType) {
        logger.info("自动产线供料、回收任务{}开始创建任务", createTaskRequest.getTaskCode());
        //参数校验
        if (StringUtils.isBlank(createTaskRequest.getSupplyLoadWb())) {
            throw new MesBusinessException(createTaskRequest.getTaskCode(), "供料点点位不能为空");
        }
        if (createTaskRequest.getSupplyLoadNum() == null) {
            throw new MesBusinessException(createTaskRequest.getTaskCode(), "供料点数量不能为空");
        }
        //将点位信息转换为berCode
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(createTaskRequest.getSupplyLoadWb());
        Preconditions.checkMesBusinessError(baseMapBerth == null, createTaskRequest.getSupplyLoadWb() + "找不到别名对应的地图编码");
        createTaskRequest.setSupplyLoadWb(baseMapBerth.getBerCode());

        //写入站点集合
        String jsonString = JSONArray.toJSONString(Arrays.asList(createTaskRequest.getSupplyLoadWb()));
        createTaskRequest.setStaticViaPaths(jsonString);
        //公用的任务创建流程
        rollerTaskCreate(createTaskRequest, mainTaskType);
        //生成context列的数据信息
        ContextDTO contextDTO = new ContextDTO();
        contextDTO.setSupplyLoadWb(createTaskRequest.getSupplyLoadWb());
        contextDTO.setSupplyLoadNum(createTaskRequest.getSupplyLoadNum());
        String contextJson = TaskContextUtils.objectToJson(contextDTO);
        //更新task_context表
        TaskContext taskContext = new TaskContext();
        taskContext.setMainTaskNum(createTaskRequest.getTaskCode());
        taskContext.setContext(contextJson);
        taskContextMapper.updateByMainTaskNum(taskContext);

        logger.info("自动产线供料、回收任务{}创建任务结束", createTaskRequest.getTaskCode());
        return new MesResult();
    }
    /**
     * 创建自动化产线空箱回收任务时独有的创建动作
     * @param createTaskRequest
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MesResult emptyRecyleTask(CreateTaskRequest createTaskRequest, String mainTaskType) {
        logger.info("自动产线回收任务{}开始创建任务", createTaskRequest.getTaskCode());
        //参数校验
        if (StringUtils.isBlank(createTaskRequest.getSrcWbCode())) {
            throw new MesBusinessException(createTaskRequest.getTaskCode(), "空料箱回收上箱点编码不能为空");
        }
        if (StringUtils.isBlank(createTaskRequest.getTargetEmptyRecyleWb())) {
            throw new MesBusinessException(createTaskRequest.getTaskCode(), "空框回收下箱点不能为空");
        }
        if (createTaskRequest.getEmptyRecyleNum() == null) {
            throw new MesBusinessException(createTaskRequest.getTaskCode(), "空框回收数量不能为空");
        }
        //将点位信息转换为berCode
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(createTaskRequest.getTargetEmptyRecyleWb());
        Preconditions.checkMesBusinessError(baseMapBerth == null, createTaskRequest.getTargetEmptyRecyleWb() + "找不到别名对应的地图编码");
        createTaskRequest.setTargetEmptyRecyleWb(baseMapBerth.getBerCode());
        //回收点
        BaseMapBerth srcBaseMapBerth = baseMapBerthMapper.selectByPointAlias(createTaskRequest.getSrcWbCode());
        Preconditions.checkMesBusinessError(srcBaseMapBerth == null, createTaskRequest.getSrcWbCode() + "找不到别名对应的地图编码");
        createTaskRequest.setSrcWbCode(srcBaseMapBerth.getBerCode());

        //写入站点集合
        String jsonString = JSONArray.toJSONString(Arrays.asList(createTaskRequest.getTargetEmptyRecyleWb()));
        createTaskRequest.setStaticViaPaths(jsonString);
        //公用的任务创建流程
        rollerTaskCreate(createTaskRequest, mainTaskType);
        //生成context列的数据信息
        ContextDTO contextDTO = new ContextDTO();
        contextDTO.setSrcWbCode(createTaskRequest.getSrcWbCode());
        contextDTO.setEmptyRecyleWb(createTaskRequest.getTargetEmptyRecyleWb());
        contextDTO.setEmptyRecyleNum(createTaskRequest.getEmptyRecyleNum());
        String contextJson = TaskContextUtils.objectToJson(contextDTO);
        //更新task_context表
        TaskContext taskContext = new TaskContext();
        taskContext.setMainTaskNum(createTaskRequest.getTaskCode());
        taskContext.setContext(contextJson);
        taskContextMapper.updateByMainTaskNum(taskContext);

        logger.info("自动产线回收任务{}创建任务结束", createTaskRequest.getTaskCode());
        return new MesResult();
    }

    /**
     * Mes公用的参数检查
     * @param createTaskRequest
     */
    public void publicCheckIsBlank(CreateTaskRequest createTaskRequest) {
        if (Strings.isNullOrEmpty(createTaskRequest.getTaskCode())) {
            throw new MesBusinessException(createTaskRequest.getTaskCode(), "任务号不能为空");
        }
        if (Strings.isNullOrEmpty(createTaskRequest.getTaskPri())) {
            throw new MesBusinessException(createTaskRequest.getTaskCode(), "优先级不能为空");
        }
    }

    /**
     * US_Inspur
     * 工产线、测试区、叉车AGV搬运任务
     * @param agvHandlingTaskCreateRequest
     * @return MesResult
     */
    @Override
    public MesResult agvHandlingTaskCreate(AgvHandlingTaskCreateRequest agvHandlingTaskCreateRequest){
        String srcBerCode = baseMapBerthMapper.selectBerCodeByAlias(agvHandlingTaskCreateRequest.getSrcWb());
        if (Strings.isNullOrEmpty(srcBerCode)) {
            throw new MesBusinessException(agvHandlingTaskCreateRequest.getSrcWb()+"该起始点在地图中未找到对应的地码！");
        }
        String destBerCode = baseMapBerthMapper.selectBerCodeByAlias(agvHandlingTaskCreateRequest.getSrcWb());
        if (Strings.isNullOrEmpty(destBerCode)) {
            throw new MesBusinessException(agvHandlingTaskCreateRequest.getSrcWb()+"该目标点在地图中未找到对应的地码！");
        }

        //查询终点是否有关联点
        List<String> point = baseConnectionPointMapper.selectPointByMapCodeBerCode(destBerCode);
        String mainTaskTypeCode = "USpTop";
        if (point.size() > 0){
            mainTaskTypeCode = "USpTopWait";
        }
        //2.创建主任务
        String mainTaskNum = agvHandlingTaskCreateRequest.getTaskCode();
        MainTask mainTaskCreate = new MainTask();
        mainTaskCreate.setCreateDate(new Date());
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setPriority(TaskPriorityEnum.getPriorityByCode(agvHandlingTaskCreateRequest.getTaskPri()));
        mainTaskCreate.setMainTaskTypeCode(mainTaskTypeCode);
        mainTaskCreate.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskCreate.setStaticPodCode(agvHandlingTaskCreateRequest.getPodCode());
        //写入站点集合
        String jsonString = JSONArray.toJSONString(Arrays.asList(srcBerCode,destBerCode));
        mainTaskCreate.setStaticViaPaths(jsonString);
        mainTaskMapper.insertSelective(mainTaskCreate);

        //创建子任务共享数据区域--任务上下文
        TaskContext taskContext = new TaskContext();
        taskContext.setMainTaskNum(mainTaskNum);
        taskContext.setCreateTime(new Date());
        taskContextMapper.insertSelective(taskContext);

        return new MesResult();
    }
}
