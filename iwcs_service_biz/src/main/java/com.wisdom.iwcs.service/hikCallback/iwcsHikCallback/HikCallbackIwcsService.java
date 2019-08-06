package com.wisdom.iwcs.service.hikCallback.iwcsHikCallback;

import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.taskUtils.CreateRouteKeyUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.hikSync.HikCallBackAgvMove;
import com.wisdom.iwcs.domain.hikSync.HikReachCheckArea;
import com.wisdom.iwcs.domain.hikSync.HikSyncResponse;
import com.wisdom.iwcs.domain.log.ResPodEvt;
import com.wisdom.iwcs.domain.log.ResPosEvt;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.task.BaseConnectionPoint;
import com.wisdom.iwcs.domain.task.EleControlTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.SubTaskStatusEnum;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.mapper.task.BaseConnectionPointMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.elevator.impl.ElevatorNotifyService;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.task.scheduler.CheckEleArrivedThread;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskAgvAction.AGV_RECEIVE;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskAgvAction.AGV_SEND;
import static com.wisdom.iwcs.common.utils.TaskConstants.eleFloor.SOURCE_FLOOR;
import static com.wisdom.iwcs.common.utils.TaskConstants.yesOrNo.NO;
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
                ; break;
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

        //向消息队列发送消息
        String message = "子任务回调:子任务已开始搬运";
        RabbitMQPublicService.successTaskLog(new TaskOperationLog(hikCallBackAgvMove.getTaskCode(), TaskConstants.operationStatus.CALLBACK_START,message));
    }

    /**
     * 走出储位时回调的方法
     * @param hikCallBackAgvMove
     */
    private void taskLeavePoint(HikCallBackAgvMove hikCallBackAgvMove) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        BaseMapBerth baseMapBerth = null;
        try {
            logger.debug("任务{}已走出储位", hikCallBackAgvMove.getTaskCode());
            //1. 查询子任务信息
            //使用多个条件进行检查,防止因为网络延时等原因,没有及时接受到消息而造成的异常操作
            SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
            if (subTask != null) {
                publicCheckSubTask(hikCallBackAgvMove, subTask);
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
            } else {
                //subTask == null时说明没有生成任务单,这里认为此次请求为人工调用
                logger.warn("任务号" + hikCallBackAgvMove.getTaskCode() + "没有匹配的任务");
            }


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

    private void publicCheckSubTask(HikCallBackAgvMove hikCallBackAgvMove, SubTask subTask) {
        if (!hikCallBackAgvMove.getPodCode().equals(subTask.getPodCode())) {
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
            logger.debug("任务{}已结束", hikCallBackAgvMove.getTaskCode());
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
            }

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

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new BusinessException(e.getMessage());
        }
        //向消息队列发送消息
        String message = "子任务回调:子任务已结束";
        RabbitMQPublicService.successTaskLog(new TaskOperationLog(hikCallBackAgvMove.getTaskCode(), TaskConstants.operationStatus.CALLBACK_END,message));

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
        Thread thread = new Thread(new CheckEleArrivedThread(eleControlTask.getEleTaskCode(), hikReachCheckArea.getSrcFloor(), subTask.getSubTaskNum()));
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
        if (NO.equals(eleControlTask.getWcsNotifyEntrySource())) {
            logger.info("小车已到达电梯{}对应的检验点", hikReachCheckArea.getSrcPosCode());
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(hikReachCheckArea.getSrcPosCode());
            String berCode = baseConnectionPointMapper.selectCheckBerCodeByMapCode(baseMapBerth.getMapCode());
            //通知检查点货架到检查点了
            elevatorNotifyService.notifyEleCheckPod(eleControlTask.getEleTaskCode(), berCode, SOURCE_FLOOR);

            //更新电梯任务,已经通知电梯点了
//            EleControlTask tmpEleControlTask = new EleControlTask();
//            tmpEleControlTask.setId(eleControlTask.getId());
//            tmpEleControlTask.setWcsNotifyEntrySource(YES);
//            eleControlTaskMapper.updateByPrimaryKeySelective(tmpEleControlTask);
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
}
