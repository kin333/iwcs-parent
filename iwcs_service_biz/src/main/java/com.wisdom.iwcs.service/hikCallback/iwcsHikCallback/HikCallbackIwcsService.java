package com.wisdom.iwcs.service.hikCallback.iwcsHikCallback;

import com.alibaba.fastjson.JSON;
import com.greenpineyu.fel.function.operator.Sub;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.taskUtils.CreateRouteKeyUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.hikSync.HikCallBackAgvMove;
import com.wisdom.iwcs.domain.hikSync.HikReachCheckArea;
import com.wisdom.iwcs.domain.hikSync.HikSyncResponse;
import com.wisdom.iwcs.domain.log.ResPosEvt;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.mes.ArriveDestWbInfoDto;
import com.wisdom.iwcs.domain.mes.ArriveDestWbWaitPortInfoDTO;
import com.wisdom.iwcs.domain.mes.ArriveSrcWbInfoDto;
import com.wisdom.iwcs.domain.mes.LeaveSrcWbInfoDto;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.domain.task.dto.SubTaskStatusEnum;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.mapper.task.*;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.callHikImpl.ContinueTaskService;
import com.wisdom.iwcs.service.elevator.impl.ElevatorNotifyService;
import com.wisdom.iwcs.service.linebody.impl.LineNotifyService;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.task.scheduler.CheckEleArrivedThread;
import com.wisdom.iwcs.service.task.template.TemplateRelatedServer;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizSecondAreaCodeTypeConstants.LINEAREAAUTOPOINT;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizSecondAreaCodeTypeConstants.LINEAREAMANUALPOINT;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskAgvAction.AGV_RECEIVE;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskAgvAction.AGV_SEND;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.HikCallbackMethod.LIFT_POD_END;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.LINEAREA;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_HIK;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;
import static com.wisdom.iwcs.common.utils.TaskConstants.actionStatus.*;
import static com.wisdom.iwcs.common.utils.TaskConstants.createNode.*;
import static com.wisdom.iwcs.common.utils.TaskConstants.eleFloor.SOURCE_FLOOR;
import static com.wisdom.iwcs.common.utils.TaskConstants.executeMode.NO_PROMISE_ARRIVE;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskType.ROLLER_CONTINUE;
import static com.wisdom.iwcs.common.utils.TaskConstants.workTaskStatus.ARRIVE_WITE;
import static com.wisdom.iwcs.common.utils.TaskConstants.yesOrNo.YES;

/**
 * Hik的回调方法
 */
@Service
public class HikCallbackIwcsService {
    private static final Logger logger = LoggerFactory.getLogger(HikCallbackIwcsService.class);


    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    ElevatorNotifyService elevatorNotifyService;
    @Autowired
    EleControlTaskMapper eleControlTaskMapper;
    @Autowired
    BaseConnectionPointMapper baseConnectionPointMapper;
    @Autowired
    private LineNotifyService lineNotifyService;
    @Autowired
    private BaseMsgSendMapper baseMsgSendMapper;
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private TemplateRelatedServer templateRelatedServer;
    @Autowired
    ContinueTaskService continueTaskService;
    @Autowired
    ICommonService iCommonService;
    @Autowired
    TaskRelActionMapper taskRelActionMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    SubTaskActionMapper subTaskActionMapper;

    /**
     * 小车开始任务的基础修改
     * @return
     */
    private SubTask taskStartBaseChange(HikCallBackAgvMove hikCallBackAgvMove) {
        logger.debug("任务{}的搬运任务开始", hikCallBackAgvMove.getTaskCode());
        SubTask subTask  = new SubTask();
        subTask.setRobotCode(hikCallBackAgvMove.getRobotCode());
        subTask.setWorkTaskStatus(TaskConstants.workTaskStatus.START);
        subTask.setWorkerTaskCode(hikCallBackAgvMove.getTaskCode());
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            subTask.setTaskStartTime(timeFormat.parse(hikCallBackAgvMove.getReqTime()));
        } catch (ParseException e) {
            logger.error("时间格式不正确:" + hikCallBackAgvMove.getReqTime());
            subTask.setTaskStartTime(new Date());
        }
        //更新子任务的执行AGV和实际任务状态以及实际任务开始时间
        subTaskMapper.updateRobotCodeByBerCode(subTask);

        subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
        //向消息队列发送消息
        String message = "子任务回调:子任务已开始搬运";
        if (subTask != null && subTask.getStartBercode() != null && !subTask.getStartBercode().equals(hikCallBackAgvMove.getWbCode())) {
            message += " (任务起始点异常, 子任务起始点为:{" + subTask.getStartBercode() + "},实际起始点为:{" + hikCallBackAgvMove.getWbCode() + "})";
        }
        RabbitMQPublicService.successTaskLog(new TaskOperationLog(hikCallBackAgvMove.getTaskCode(), TaskConstants.operationStatus.CALLBACK_START,message));

        if (subTask != null) {
            //节点动作
            nodeAction(subTask, PTOP_START);
        }

        return subTask;
    }

    /**
     * 离开储位的基础修改
     * @param hikCallBackAgvMove
     * @return
     */
    private SubTask taskLeaveBaseChange(HikCallBackAgvMove hikCallBackAgvMove) {
        logger.debug("任务{}已走出储位", hikCallBackAgvMove.getTaskCode());
        //1. 查询子任务信息
        //使用多个条件进行检查,防止因为网络延时等原因,没有及时接受到消息而造成的异常操作
        SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
        if (subTask != null) {
            if (!SubTaskStatusEnum.Executing.getStatusCode().equals(subTask.getTaskStatus())) {
                logger.error(hikCallBackAgvMove.getTaskCode() + "任务异常: 任务状态不匹配");
//                throw new BusinessException(hikCallBackAgvMove.getTaskCode() + "任务异常: 任务状态不匹配");
            }
            // 更新子任务实际离开储位时间
            try {
                SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                subTask.setTaskLeaveTime(timeFormat.parse(hikCallBackAgvMove.getReqTime()));
            } catch (ParseException e) {
                logger.error("时间格式不正确:" + hikCallBackAgvMove.getReqTime());
                subTask.setTaskLeaveTime(new Date());
            }
            subTaskMapper.updateRobotCodeByBerCode(subTask);
            nodeAction(subTask, PTOP_LEAVE);
        } else {
            //subTask == null时说明没有生成任务单,这里认为此次请求为人工调用
            logger.warn("任务号" + hikCallBackAgvMove.getTaskCode() + "没有匹配的任务");
        }
        return subTask;
    }

    /**
     * 小车移动结束时的基础修改
     * 主要更改子任务表中的结束时间,修改任务状态,完成时间
     * @param hikCallBackAgvMove
     * @return
     */
    private SubTask taskFinishedBaseChange(HikCallBackAgvMove hikCallBackAgvMove) {
        logger.info("任务{}已结束", hikCallBackAgvMove.getTaskCode());
        //1. 查询子任务信息
        //当subTask = null 时认为此次调用为人工调用,没有生成任务单
        SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
        //使用多个条件进行检查,防止因为网络延时等原因,没有及时接受到消息而造成的异常操作
        if (subTask != null) {
            publicCheckSubTask(hikCallBackAgvMove, subTask);
            //更新子任务状态以及实际任务结束时间
            subTask.setWorkTaskStatus(TaskConstants.workTaskStatus.END);
            try {
                SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                subTask.setTaskEndTime(timeFormat.parse(hikCallBackAgvMove.getReqTime()));
            } catch (ParseException e) {
                logger.error("时间格式不正确:" + hikCallBackAgvMove.getReqTime());
                subTask.setTaskEndTime(new Date());
            }
            subTaskMapper.updateRobotCodeByBerCode(subTask);
            //节点动作
            nodeAction(subTask, PTOP_END);
        }

        //向消息队列发送消息
        String message = "子任务回调:子任务已结束";
        RabbitMQPublicService.successTaskLog(new TaskOperationLog(hikCallBackAgvMove.getTaskCode(), TaskConstants.operationStatus.CALLBACK_END,message));
        return subTask;
    }

    /**
     * 节点动作
     * @param
     * @param nodeCode
     */
    private void nodeAction(SubTask subTask, String nodeCode) {
        //1.获取节点动作模板信息
        List<TaskRelAction> taskRelActionList = taskRelActionMapper.selectByTempCodeAndNode(subTask.getTemplCode(), nodeCode);
        for (TaskRelAction taskRelAction : taskRelActionList) {
            //2.生成消息体和地址
            String jsonStr = templateRelatedServer.actionTemplateInfo(subTask, taskRelAction.getActionCode());
            String address = addressMapper.selectAddressByCode(taskRelAction.getApp());
            String url = address + taskRelAction.getUrl();
            //3.获取生成子任务节点动作
            SubTaskAction subTaskAction = new SubTaskAction();
            subTaskAction.setSubTaskNum(subTask.getSubTaskNum());
            subTaskAction.setActionCode(taskRelAction.getActionCode());
            subTaskAction.setTemplCode(taskRelAction.getTemplCode());
            subTaskAction.setActionType(taskRelAction.getActionType());
            subTaskAction.setContent(jsonStr);
            subTaskAction.setUrl(url);
            subTaskAction.setApp(taskRelAction.getApp());
            subTaskAction.setThirdInvokeType(taskRelAction.getThirdInvokeType());
            subTaskAction.setExecuteMode(taskRelAction.getExecuteMode());
            subTaskAction.setPreActions(taskRelAction.getPreActions());
            subTaskAction.setCreateTime(new Date());
            subTaskAction.setCreateNode(nodeCode);
            subTaskAction.setResponseHandler(taskRelAction.getResponseHandler());
            subTaskAction.setActionStatus(CREATE);
            //插入请求信息
            subTaskActionMapper.insertSelective(subTaskAction);


            //4.如果不保证必达,且无前置请求,则发送
//            try {
//                if (NO_PROMISE_ARRIVE.equals(taskRelAction.getExecuteMode()) && StringUtils.isEmpty(taskRelAction.getPreActions())) {
//                    String resultBody = "";
//                    if (SRC_HIK.equals(taskRelAction.getApp())) {
//                        //如果执行者类型是海康,则调用海康的接口
//                        resultBody = NetWorkUtil.transferContinueTask(jsonStr, url);
//                        JSONObject obj = new JSONObject(resultBody);
//                        if (obj.getString("code").equals("0")) {
//                            subTaskAction.setActionType(SEND_SUCCESS);
//                        } else {
//                            subTaskAction.setActionType(SEND_ERROR);
//                        }
//                    } else if (SRC_MES.equals(taskRelAction.getApp())) {
//                        //调用MES
//                        logger.info("MES发送任务:{}", jsonStr);
//                        resultBody = NetWorkUtil.transferContinueTask(jsonStr, url);
//                        JSONObject obj = new JSONObject(resultBody);
//                        if (obj.getString("code").equals(MesResult.OK)) {
//                            subTaskAction.setActionType(SEND_SUCCESS);
//                        } else {
//                            subTaskAction.setActionType(SEND_ERROR);
//                        }
//                        logger.info("MES返回的消息体为:" + resultBody);
//                    }
//                    subTaskAction.setResultBody(resultBody);
//                } else {
//                    subTaskAction.setActionType(CREATE);
//                }
//                //插入请求信息
//                subTaskActionMapper.insertSelective(subTaskAction);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }

    }


    public HikSyncResponse taskNotify(HikCallBackAgvMove hikCallBackAgvMove) {
        switch (hikCallBackAgvMove.getMethod()) {
            //任务开始
            case InspurBizConstants.HikCallbackMethod.TASK_START:
                taskStart(hikCallBackAgvMove); break;
            //走出储位
            case InspurBizConstants.HikCallbackMethod.TASK_LEAVE_POINT:
                taskLeavePoint(hikCallBackAgvMove); break;
            //任务结束
            case InspurBizConstants.HikCallbackMethod.TASK_FINISHED:
                taskFinished(hikCallBackAgvMove); break;
            //到达检查点
            case InspurBizConstants.HikCallbackMethod.APPLY_RESOURCE:
                 break;
            default: break;
        }
        HikSyncResponse hikSyncResponse = new HikSyncResponse();
        hikSyncResponse.setReqCode(hikCallBackAgvMove.getReqCode());
         return hikSyncResponse;
    }

    /**
     * 任务开始时回调的方法
     * @param hikCallBackAgvMove
     */
    @Transactional(rollbackFor = Exception.class)
    void taskStart(HikCallBackAgvMove hikCallBackAgvMove) {
        //小车开始任务的基础修改
        taskStartBaseChange(hikCallBackAgvMove);
    }


    /**
     * 走出储位时回调的方法
     * @param hikCallBackAgvMove
     */
    private void taskLeavePoint(HikCallBackAgvMove hikCallBackAgvMove) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        BaseMapBerth baseMapBerth = null;
        try {
            //使用多个条件进行检查,防止因为网络延时等原因,没有及时接受到消息而造成的异常操作
            SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
            if (subTask != null) {
                publicCheckSubTask(hikCallBackAgvMove, subTask);
            }
            taskLeaveBaseChange(hikCallBackAgvMove);


            baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikCallBackAgvMove.getWbCode());
            if (baseMapBerth == null) {
                throw new BusinessException(hikCallBackAgvMove.getWbCode() + "此地码的信息不存在");
            }
            logger.info("子任务{}开始清空地码{}的货架编号", hikCallBackAgvMove.getTaskCode(), hikCallBackAgvMove.getWbCode());
            BaseMapBerth tmpBaseMapBerth = new BaseMapBerth();
            tmpBaseMapBerth.setId(baseMapBerth.getId());
            tmpBaseMapBerth.setPodCode("");
            //更新储位信息,加货架号,解锁
            int changeRows = baseMapBerthMapper.updateByPrimaryKeySelective(tmpBaseMapBerth);
            if (changeRows <= 0) {
                logger.error("子任务{}在清空地码{}的货架编号{}时失败", hikCallBackAgvMove.getTaskCode(),
                        hikCallBackAgvMove.getWbCode(), baseMapBerth.getPodCode());
            }
            logger.info("子任务{}在清空地码{}的货架编号{}时成功", hikCallBackAgvMove.getTaskCode(),
                    hikCallBackAgvMove.getWbCode(), baseMapBerth.getPodCode());
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new BusinessException(e.getMessage());
        }

        //向消息队列发送消息
        String message = "子任务回调:子任务已离开储位";
        RabbitMQPublicService.successTaskLog(new TaskOperationLog(hikCallBackAgvMove.getTaskCode(), TaskConstants.operationStatus.CALLBACK_LEAVE,message));

        //发送释放储位消息
        ResPosEvt resPosEvt = new ResPosEvt();
        resPosEvt.setBerCode(hikCallBackAgvMove.getMapDataCode());
        resPosEvt.setCreateTime(new Date());
        resPosEvt.setAreaCode(baseMapBerth.getOperateAreaCode());
        resPosEvt.setResourcesType(TaskConstants.resourceType.POS_RELEASE);
        resPosEvt.setMapCode(baseMapBerth.getMapCode());
        resPosEvt.setSubTaskNum(hikCallBackAgvMove.getTaskCode());
        String routeKey = CreateRouteKeyUtils.createPosRelease(baseMapBerth.getMapCode(), baseMapBerth.getOperateAreaCode());
        RabbitMQPublicService.sendInfoByRouteKey(routeKey, resPosEvt);

        String bizSecondAreaCode = baseMapBerth.getBizSecondAreaCode();
        //如果离开的是线体工作区,则发送线体离开消息
        if (LINEAREA.equals(baseMapBerth.getOperateAreaCode())
                && (LINEAREAAUTOPOINT.equals(bizSecondAreaCode) || LINEAREAMANUALPOINT.equals(bizSecondAreaCode))) {
            logger.info("通知线体,小车已经离开{} ",baseMapBerth.getPointAlias());
            lineNotifyService.agvStatusIne(baseMapBerth.getPointAlias(), TaskConstants.agvTaskType.LEAVE);
        }
    }

    /**
     * 更新地码信息
     * @param hikCallBackAgvMove
     */
    private void updateMapInfo(HikCallBackAgvMove hikCallBackAgvMove , SubTask subTask) {
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikCallBackAgvMove.getMapDataCode());
        if (baseMapBerth == null) {
            throw new BusinessException(hikCallBackAgvMove.getMapDataCode() + "此地码的信息不存在");
        }
        if (subTask != null && !subTask.getSubTaskNum().equals(baseMapBerth.getLockSource())) {
            logger.error("地码锁定源与子任务号不匹配,子任务号:{} 地码编号:{} 地码锁定源:{} 锁定标识:{}", subTask.getSubTaskNum(),
                    hikCallBackAgvMove.getMapDataCode(), baseMapBerth.getLockSource(), baseMapBerth.getInLock());
            throw new BusinessException("地码锁定源与子任务号不匹配,子任务号:" + subTask.getSubTaskNum()
                    + " 地码编号:" + hikCallBackAgvMove.getMapDataCode());
        }
        //解锁这个储位
        baseMapBerth.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
        baseMapBerth.setPodCode(hikCallBackAgvMove.getPodCode());
        baseMapBerth.setLockSource("");
        //更新储位信息,加货架号,解锁
        baseMapBerthMapper.updateByPrimaryKeySelective(baseMapBerth);
        logger.info("子任务{}解锁地码{}成功", hikCallBackAgvMove.getTaskCode(), hikCallBackAgvMove.getPodCode());
    }
    /**
     * 清除地码的货架信息(无点位检查)
     * @param hikCallBackAgvMove
     */
    private void updateMapInfoNoCheck(HikCallBackAgvMove hikCallBackAgvMove) {
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikCallBackAgvMove.getWbCode());
        if (baseMapBerth == null) {
            throw new BusinessException(hikCallBackAgvMove.getWbCode() + "此地码的信息不存在");
        }
        //解锁这个储位
        baseMapBerth.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
        baseMapBerth.setPodCode("");
        //更新储位信息,加货架号,解锁
        baseMapBerthMapper.updateByPrimaryKeySelective(baseMapBerth);
        logger.info("子任务{}解锁地码{}成功", hikCallBackAgvMove.getTaskCode(), hikCallBackAgvMove.getPodCode());
    }

    private void publicCheckSubTask(HikCallBackAgvMove hikCallBackAgvMove, SubTask subTask) {
        if (hikCallBackAgvMove.getPodCode() != null && !hikCallBackAgvMove.getPodCode().equals(subTask.getPodCode())) {
            throw new BusinessException(hikCallBackAgvMove.getTaskCode() + "任务异常: 货架号不匹配");
        }
        if (!hikCallBackAgvMove.getRobotCode().equals(subTask.getRobotCode())) {
            logger.error("实际AGV型号{}与任务AGV型号{}不匹配", hikCallBackAgvMove.getRobotCode(),subTask.getRobotCode());
//            throw new BusinessException(hikCallBackAgvMove.getTaskCode() + "任务异常: 机器人编号不匹配");
        }
    }


    /**
     * 任务完成时回调的方法
     * @param hikCallBackAgvMove
     */
    private void taskFinished(HikCallBackAgvMove hikCallBackAgvMove) {
        logger.info("开始执行任务完成回调方法,任务号:{}", hikCallBackAgvMove.getTaskCode());
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            //1.小车移动结束时的基础修改
            SubTask subTask = taskFinishedBaseChange(hikCallBackAgvMove);

            //2. 更新地码信息
            updateMapInfo(hikCallBackAgvMove, subTask);

            //3.更新货架信息
            BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(hikCallBackAgvMove.getPodCode());
            if (basePodDetail == null) {
                throw new BusinessException(hikCallBackAgvMove.getPodCode() + "货架号无对应货架信息");
            }
            if (subTask != null && !subTask.getSubTaskNum().equals(basePodDetail.getLockSource())) {
                logger.error("货架锁定源与子任务号不匹配,子任务号:{}  地码编号:{}  锁定源:{} 锁定标识:{}", subTask.getSubTaskNum(),
                        hikCallBackAgvMove.getMapDataCode(), basePodDetail.getLockSource(), basePodDetail.getInLock());
                throw new BusinessException("货架锁定源与子任务号不匹配,子任务号:" + subTask.getSubTaskNum()
                    + " 货架编号:" + hikCallBackAgvMove.getPodCode());
            }
            BasePodDetail tmpBasePodDetail = new BasePodDetail();
            tmpBasePodDetail.setId(basePodDetail.getId());
            tmpBasePodDetail.setCoox(hikCallBackAgvMove.getCooX());
            tmpBasePodDetail.setCooy(hikCallBackAgvMove.getCooY());
            tmpBasePodDetail.setBerCode(hikCallBackAgvMove.getMapDataCode());
            tmpBasePodDetail.setMapCode(hikCallBackAgvMove.getMapCode());
            tmpBasePodDetail.setLastModifiedTime(new Date());
            //更新货架信息表
            int changeRows = basePodDetailMapper.updateByPrimaryKeySelective(tmpBasePodDetail);
            if (changeRows <= 0) {
                logger.error("子任务{}在更新货架的地码编号{}时失败", hikCallBackAgvMove.getTaskCode(),
                        hikCallBackAgvMove.getWbCode());
            }
            logger.info("子任务{}在更新货架的地码编号{}时成功 ", hikCallBackAgvMove.getTaskCode(),
                    hikCallBackAgvMove.getWbCode());

            //检查走出储位时回调是否执行
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikCallBackAgvMove.getWbCode());
            Preconditions.checkBusinessError(baseMapBerth == null, hikCallBackAgvMove.getWbCode() + "此地码的信息不存在");
            if (hikCallBackAgvMove.getPodCode().equals(baseMapBerth.getPodCode())) {
                logger.info("子任务{}开始修复走出储位回调未执行成功情况,清空地码{}的货架编号", hikCallBackAgvMove.getTaskCode(), hikCallBackAgvMove.getWbCode());
                BaseMapBerth tmpBaseMapBerth = new BaseMapBerth();
                tmpBaseMapBerth.setId(baseMapBerth.getId());
                tmpBaseMapBerth.setPodCode("");
                //更新储位信息,加货架号,解锁
                baseMapBerthMapper.updateByPrimaryKeySelective(tmpBaseMapBerth);
            }

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new BusinessException(e.getMessage());
        }
        //向消息队列发送消息
//        String message = "子任务回调:子任务已结束";
//        RabbitMQPublicService.successTaskLog(new TaskOperationLog(hikCallBackAgvMove.getTaskCode(), TaskConstants.operationStatus.CALLBACK_END,message));

        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikCallBackAgvMove.getMapDataCode());
        //发送释放储位消息
        ResPosEvt resPosEvt = new ResPosEvt();
        resPosEvt.setBerCode(hikCallBackAgvMove.getMapDataCode());
        resPosEvt.setCreateTime(new Date());
        resPosEvt.setAreaCode(baseMapBerth.getOperateAreaCode());
        resPosEvt.setMapCode(baseMapBerth.getMapCode());
        resPosEvt.setResourcesType(TaskConstants.resourceType.POS_RELEASE);
        resPosEvt.setSubTaskNum(hikCallBackAgvMove.getTaskCode());
        String routeKey = CreateRouteKeyUtils.createPosRelease(baseMapBerth.getMapCode(), baseMapBerth.getOperateAreaCode());
        RabbitMQPublicService.sendInfoByRouteKey(routeKey, resPosEvt);
    }

    /**
     * 小车送货架进电梯后,agv出电梯回调接口
     * @param hikReachCheckArea
     * @return
     */
    public HikSyncResponse excuteTask(HikReachCheckArea hikReachCheckArea) {
        logger.info("小车送货架进电梯后,已出电梯,任务:{}", hikReachCheckArea.getTaskDetailKey());
        SubTask subTask = subTaskMapper.selectBySubTaskNum(hikReachCheckArea.getTaskDetailKey());
        EleControlTask eleControlTask = eleControlTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikReachCheckArea.getSrcPosCode());
        //通知电梯小车已离开电梯
        elevatorNotifyService.notifyEleAgvLeave(eleControlTask.getEleTaskCode(), baseMapBerth.getMapCode(), AGV_SEND);
        //开启电梯到达线程,如果到达,则呼叫小车
        Thread thread = new Thread(new CheckEleArrivedThread(eleControlTask.getEleTaskCode(), hikReachCheckArea.getDestFloor(), subTask.getSubTaskNum()));
        thread.start();
        return new HikSyncResponse();
    }

    /**
     * 小车到达检验点回调
     * @param hikReachCheckArea
     * @return
     */
    public HikSyncResponse applyResource(HikReachCheckArea hikReachCheckArea) {
        SubTask subTask = subTaskMapper.selectBySubTaskNum(hikReachCheckArea.getTaskDetailKey());
        EleControlTask eleControlTask = eleControlTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        //检查是否已经通知检查点了
        if (!YES.equals(eleControlTask.getWcsNotifyEntrySource())) {
            logger.info("小车已到达电梯{}对应的检验点", hikReachCheckArea.getSrcPosCode());
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikReachCheckArea.getSrcPosCode());
            String berCode = baseConnectionPointMapper.selectCheckBerCodeByMapCode(baseMapBerth.getMapCode());
            //通知检查点货架到检查点了
            elevatorNotifyService.notifyEleCheckPod(eleControlTask.getEleTaskCode(), berCode, SOURCE_FLOOR);
        }

        //如果检查点通过,则返回正确
        if (YES.equals(eleControlTask.getPlcNotifyEntrySource())) {
            logger.info("电梯{}的检验点检查完成", hikReachCheckArea.getSrcPosCode());
            //清空储位货架
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(subTask.getStartBercode());
            BaseMapBerth tmpBaseMapBerth = new BaseMapBerth();
            tmpBaseMapBerth.setId(baseMapBerth.getId());
            tmpBaseMapBerth.setPodCode("");
            //更新储位信息,加货架号,解锁
            baseMapBerthMapper.updateByPrimaryKeySelective(tmpBaseMapBerth);
            logger.info("地码{}已清空货架{}", subTask.getStartBercode(), baseMapBerth.getPodCode());
            return new HikSyncResponse();
        }
        return new HikSyncResponse("99", "检查点未完成检查");
    }

    /**
     * agv接货架出电梯时,小车出电梯回调
     * @param hikReachCheckArea
     * @return
     */
    public HikSyncResponse releaseResource(HikReachCheckArea hikReachCheckArea) {
        logger.info("小车接货架出电梯,已离开电梯,任务:{}", hikReachCheckArea.getTaskDetailKey());
        SubTask subTask = subTaskMapper.selectBySubTaskNum(hikReachCheckArea.getTaskDetailKey());
        EleControlTask eleControlTask = eleControlTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikReachCheckArea.getSrcPosCode());
        //通知电梯小车已离开电梯
        elevatorNotifyService.notifyEleAgvLeave(eleControlTask.getEleTaskCode(), baseMapBerth.getMapCode(), AGV_RECEIVE);

        return new HikSyncResponse();
    }

    /**
     * 滚筒AGV回调
     * @param hikCallBackAgvMove
     * @return
     */
    public HikSyncResponse rollerNotify(HikCallBackAgvMove hikCallBackAgvMove) {
        logger.info("接收到滚筒AGV回调,子任务{}", hikCallBackAgvMove.getTaskCode());
        switch (hikCallBackAgvMove.getMethod()) {
            //滚筒AGV开始移动
            case InspurBizConstants.HikCallbackMethod.MOVE_START:
                moveStart(hikCallBackAgvMove); break;
            //滚筒AGV到达终点
            case InspurBizConstants.HikCallbackMethod.MOVE_END:
                moveEnd(hikCallBackAgvMove); break;
            //滚筒AGV开始滚动
            case InspurBizConstants.HikCallbackMethod.ROLL_START:
                rollStart(hikCallBackAgvMove); break;
            //滚筒AGV结束滚动
            case InspurBizConstants.HikCallbackMethod.ROLL_END:
                rollEnd(hikCallBackAgvMove); break;
            default: break;
        }
        logger.info("滚筒AGV回调处理结束,子任务{}", hikCallBackAgvMove.getTaskCode());
        return new HikSyncResponse();
    }

    /**
     * 滚筒AGV开始移动
     */
    private void moveStart(HikCallBackAgvMove hikCallBackAgvMove) {
        taskStartBaseChange(hikCallBackAgvMove);
    }
    /**
     * 滚筒AGV到达终点
     */
    private void moveEnd(HikCallBackAgvMove hikCallBackAgvMove) {
        taskFinishedBaseChange(hikCallBackAgvMove);
    }
    /**
     * 滚筒AGV开始滚动
     */
    private void rollStart(HikCallBackAgvMove hikCallBackAgvMove) {
        try {
            JSONObject jsonObject = new JSONObject(hikCallBackAgvMove.getData());
            String taskCode = jsonObject.getString("taskCode");
            hikCallBackAgvMove.setTaskCode(taskCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        taskStartBaseChange(hikCallBackAgvMove);
    }
    /**
     * 滚筒AGV结束滚动
     */
    private void rollEnd(HikCallBackAgvMove hikCallBackAgvMove) {
        SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
        List<SubTask> subTaskList = subTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        subTask = subTaskList.get(subTaskList.size() - 1);
        if (ROLLER_CONTINUE.equals(subTask.getSubTaskTyp())) {
            hikCallBackAgvMove.setTaskCode(subTask.getSubTaskNum());
            taskFinishedBaseChange(hikCallBackAgvMove);
        }

    }

    /**
     * 获取data中的子任务号
     * @param hikCallBackAgvMove
     */
    private void getTaskCode(HikCallBackAgvMove hikCallBackAgvMove) {
        try {
            JSONObject jsonObject = new JSONObject(hikCallBackAgvMove.getData());
            String taskCode = jsonObject.getString("taskCode");
            hikCallBackAgvMove.setTaskCode(taskCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 美国浪潮点到点回调
     * @param hikCallBackAgvMove
     * @return
     */
    public HikSyncResponse carryNotify(HikCallBackAgvMove hikCallBackAgvMove) {
        switch (hikCallBackAgvMove.getMethod()) {
            //任务开始
            case InspurBizConstants.HikCallbackMethod.TASK_START:
                arriveStartPoint(hikCallBackAgvMove);
                break;
            //走出储位
            case InspurBizConstants.HikCallbackMethod.TASK_LEAVE_POINT:
                leaveStartPoint(hikCallBackAgvMove);
                break;
            //到达机械臂等待点(起点有等待点)
            case InspurBizConstants.HikCallbackMethod.ARRIVE_START_WAIT:
                arrivedStartWaitPoint(hikCallBackAgvMove);
                break;
            //到达机械臂等待点(终点有等待点)
            case InspurBizConstants.HikCallbackMethod.ARRIVE_END_WAIT:
                arrivedEndWaitPoint(hikCallBackAgvMove);
                break;
            //任务结束
            case InspurBizConstants.HikCallbackMethod.TASK_FINISHED:
                arriveEndPoint(hikCallBackAgvMove);
                break;
            //AGV举升完成
            case LIFT_POD_END:
                liftPodEnd(hikCallBackAgvMove);
                break;
            default:
                break;
        }
        HikSyncResponse hikSyncResponse = new HikSyncResponse();
        hikSyncResponse.setReqCode(hikCallBackAgvMove.getReqCode());
        return hikSyncResponse;
    }

    /**
     * 小车到达起始点
     */
    public void arriveStartPoint(HikCallBackAgvMove hikCallBackAgvMove){
        taskStartBaseChange(hikCallBackAgvMove);
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikCallBackAgvMove.getWbCode());
        if (baseMapBerth == null) {
            throw new BusinessException(hikCallBackAgvMove.getWbCode() + "此地码的信息不存在");
        }
        SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
        //使用多个条件进行检查,防止因为网络延时等原因,没有及时接受到消息而造成的异常操作
        if (subTask != null) {

            ArriveSrcWbInfoDto arriveSrcWbInfoDto = new ArriveSrcWbInfoDto();
            arriveSrcWbInfoDto.setAgvCode(hikCallBackAgvMove.getRobotCode());
            arriveSrcWbInfoDto.setTaskCode(subTask.getMainTaskNum());
            arriveSrcWbInfoDto.setSrcWb(baseMapBerth.getPointAlias());
            arriveSrcWbInfoDto.setArriveTime(new Date());
            Object msg = arriveSrcWbInfoDto;
            sendMsgNotifyMES(msg,"arriveSrcWb", hikCallBackAgvMove.getTaskCode());

        }
    }

    /**
     * 举起货架完成回调
     * @param hikCallBackAgvMove
     */
    public void liftPodEnd(HikCallBackAgvMove hikCallBackAgvMove) {
        SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
        //使用多个条件进行检查,防止因为网络延时等原因,没有及时接受到消息而造成的异常操作
        if (subTask != null) {
            //检查起点是否是等待点
            List<String> points = baseConnectionPointMapper.selectPointByMapCodeBerCode(hikCallBackAgvMove.getWbCode());
            if (points.size() > 0) {
                //发送消息
                if (noticeMesOpenDoor(subTask.getMainTaskNum(), subTask.getStartAlias(), hikCallBackAgvMove.getRobotCode())) {
                    //如果返回OK
                    continueTaskService.continueTask(hikCallBackAgvMove.getTaskCode());
                }
            }
        }
    }

    /**
     * 小车离开储位
     */
    public void leaveStartPoint(HikCallBackAgvMove hikCallBackAgvMove){
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikCallBackAgvMove.getWbCode());
        if (baseMapBerth == null) {
            throw new BusinessException(hikCallBackAgvMove.getWbCode() + "此地码的信息不存在");
        }
        SubTask subTask = taskLeaveBaseChange(hikCallBackAgvMove);

        //清空储位
        BaseMapBerth tmpBaseMapBerth = new BaseMapBerth();
        tmpBaseMapBerth.setId(baseMapBerth.getId());
        tmpBaseMapBerth.setPodCode("");
        //更新储位信息,加货架号,解锁
        baseMapBerthMapper.updateByPrimaryKeySelective(tmpBaseMapBerth);

        LeaveSrcWbInfoDto leaveSrcWbInfoDto = new LeaveSrcWbInfoDto();
        leaveSrcWbInfoDto.setAgvCode(hikCallBackAgvMove.getRobotCode());
        leaveSrcWbInfoDto.setSrcWb(baseMapBerth.getPointAlias());
        leaveSrcWbInfoDto.setTaskCode(subTask.getMainTaskNum());
        leaveSrcWbInfoDto.setLeaveTime(new Date());
        Object msg = leaveSrcWbInfoDto;
        sendMsgNotifyMES(msg, "leaveSrcWb", hikCallBackAgvMove.getTaskCode());

        updateMapInfoNoCheck(hikCallBackAgvMove);
    }
    /**
     * 小车到达终点
     */
    public void arriveEndPoint(HikCallBackAgvMove hikCallBackAgvMove){
        taskFinishedBaseChange(hikCallBackAgvMove);
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikCallBackAgvMove.getWbCode());
        if (baseMapBerth == null) {
            throw new BusinessException(hikCallBackAgvMove.getWbCode() + "此地码的信息不存在");
        }
        SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
        if (subTask != null) {
            //更新子任务状态以及实际任务结束时间
            ArriveDestWbInfoDto arriveDestWbInfoDto = new ArriveDestWbInfoDto();
            arriveDestWbInfoDto.setAgvCode(hikCallBackAgvMove.getRobotCode());
            arriveDestWbInfoDto.setTaskCode(subTask.getMainTaskNum());
            arriveDestWbInfoDto.setDestWb(baseMapBerth.getPointAlias());
            arriveDestWbInfoDto.setArriveTime(new Date());
            sendMsgNotifyMES(arriveDestWbInfoDto, "arriveDestWb", hikCallBackAgvMove.getTaskCode());
            //检查终点是否是等待点
            List<String> points = baseConnectionPointMapper.selectPointByMapCodeBerCode(hikCallBackAgvMove.getMapDataCode());
            if (points.size() > 0) {
                //发送消息
                if (noticeMesOpenDoor(subTask.getMainTaskNum(), baseMapBerth.getPointAlias(), hikCallBackAgvMove.getRobotCode())) {
                    //如果返回OK
                    continueTaskService.continueTask(hikCallBackAgvMove.getTaskCode());
                }
            }
        }
        updateMapInfoAndPod(hikCallBackAgvMove);

    }

    /**
     * 到达起点是等待点的位置
     * @param hikCallBackAgvMove
     */
    public void arrivedStartWaitPoint(HikCallBackAgvMove hikCallBackAgvMove){
        arrivedWaitPoint(hikCallBackAgvMove.getTaskCode(), hikCallBackAgvMove.getWbCode());
    }
    /**
     * 到达终点是等待点的位置
     * @param hikCallBackAgvMove
     */
    public void arrivedEndWaitPoint(HikCallBackAgvMove hikCallBackAgvMove){
        arrivedWaitPoint(hikCallBackAgvMove.getTaskCode(), hikCallBackAgvMove.getMapDataCode());
    }

    /**
     * 小车到达机械臂关联点
     */
    public void arrivedWaitPoint(String taskCode,String berCode){
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(berCode);
        if (baseMapBerth == null) {
            throw new BusinessException(berCode + "此地码的信息不存在");
        }
        SubTask subTask = subTaskMapper.selectByTaskCode(taskCode);
        if (subTask != null) {
            SubTask tmpSubTask = new SubTask();
            tmpSubTask.setId(subTask.getId());
            tmpSubTask.setWorkTaskStatus(ARRIVE_WITE);
            subTaskMapper.updateByPrimaryKeySelective(tmpSubTask);
            noticeMesOpenDoor(subTask.getMainTaskNum(), baseMapBerth.getPointAlias(), subTask.getRobotCode());
            //发送消息
//            if (noticeMesOpenDoor(subTask.getMainTaskNum(), baseMapBerth.getPointAlias(), subTask.getRobotCode())) {
                //如果返回OK
//                continueTaskService.continueTask(taskCode);
//            }
        }
    }

    /**
     * 通知MES 发送消息统一接口
     */
    public void sendMsgNotifyMES(Object msg, String method, String taskCode){
        String address = addressMapper.selectAddressByCode(SRC_MES);
        String url = address + applicationProperties.getMesParam().getAgvHandlingTaskUrl();

        MesBaseRequest mesBaseRequest = new MesBaseRequest();
        mesBaseRequest.setReqcode(templateRelatedServer.getRequestInfo().getReqCode());
        mesBaseRequest.setData(msg);

        BaseMsgSend baseMsgSend = new BaseMsgSend();
        baseMsgSend.setCreatedTime(new Date());
        baseMsgSend.setMethod(method);
        baseMsgSend.setMsgFrom("192.168.102.95");
        baseMsgSend.setMsgType(SRC_MES);
        baseMsgSend.setRcptStatus("0");
        baseMsgSend.setSendStatus("0");
        baseMsgSend.setSendMsg(JSON.toJSONString(mesBaseRequest));
        baseMsgSend.setTaskCode(taskCode);
        String allUrl = url+"/" + method;
        baseMsgSend.setUrl(allUrl);
        baseMsgSendMapper.insertSelective(baseMsgSend);
    }

    /**
     * 统一更新货架和地码信息
     */
    public void updateMapInfoAndPod(HikCallBackAgvMove hikCallBackAgvMove){
        //1. 更新地码信息
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikCallBackAgvMove.getMapDataCode());
        if (baseMapBerth == null) {
            throw new BusinessException(hikCallBackAgvMove.getMapDataCode() + "此地码的信息不存在");
        }
        //更新储位货架号
        baseMapBerth.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
        baseMapBerth.setPodCode(hikCallBackAgvMove.getPodCode());
        baseMapBerth.setLockSource("");
        //更新储位信息,加货架号,解锁
        baseMapBerthMapper.updateByPrimaryKeySelective(baseMapBerth);
        logger.info("子任务{}更新储位货架{}成功", hikCallBackAgvMove.getTaskCode(), hikCallBackAgvMove.getPodCode());

        //2.更新货架信息
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(hikCallBackAgvMove.getPodCode());
        if (basePodDetail == null) {
            throw new BusinessException(hikCallBackAgvMove.getPodCode() + "货架号无对应货架信息");
        }
        BasePodDetail tmpBasePodDetail = new BasePodDetail();
        tmpBasePodDetail.setId(basePodDetail.getId());
        tmpBasePodDetail.setCoox(hikCallBackAgvMove.getCooX());
        tmpBasePodDetail.setCooy(hikCallBackAgvMove.getCooY());
        tmpBasePodDetail.setLastModifiedTime(new Date());
        tmpBasePodDetail.setMapCode(hikCallBackAgvMove.getMapCode());
        tmpBasePodDetail.setBerCode(hikCallBackAgvMove.getMapDataCode());
        //更新货架信息表
        int changeRows = basePodDetailMapper.updateByPrimaryKeySelective(tmpBasePodDetail);
        if (changeRows <= 0) {
            logger.error("子任务{}在更新货架的地码编号{}时失败", hikCallBackAgvMove.getTaskCode(),
                    hikCallBackAgvMove.getWbCode());
        }
        logger.info("子任务{}在更新货架的地码编号{}时成功 ", hikCallBackAgvMove.getTaskCode(),
                hikCallBackAgvMove.getWbCode());
    }

    /**
     * 通知mes开门
     * @param
     * @param
     * @return
     */
    public boolean noticeMesOpenDoor(String mainTaskNum, String pointAlias, String robotCode) {
        ArriveDestWbWaitPortInfoDTO arriveDestWbWaitPortInfoDTO = new ArriveDestWbWaitPortInfoDTO();
        arriveDestWbWaitPortInfoDTO.setAgvCode(robotCode);
        arriveDestWbWaitPortInfoDTO.setTaskCode(mainTaskNum);
        arriveDestWbWaitPortInfoDTO.setWaitPort(pointAlias);
        arriveDestWbWaitPortInfoDTO.setArriveTime(new Date());

        MesBaseRequest mesBaseRequest = new MesBaseRequest();
        mesBaseRequest.setReqcode(templateRelatedServer.getRequestInfo().getReqCode());
        mesBaseRequest.setData(arriveDestWbWaitPortInfoDTO);
        String address = addressMapper.selectAddressByCode(SRC_MES);
        String url = address + applicationProperties.getMesParam().getArriveDestWbWaitPortUrl();


        try {
//            String resultBody = NetWorkUtil.transferContinueTask(JSON.toJSONString(mesBaseRequest), url);
//            iCommonService.handleMesResponse(resultBody);
        } catch (BusinessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
