package com.wisdom.iwcs.service.hikCallback.iwcsHikCallback;

import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.hikSync.HikCallBackAgvMove;
import com.wisdom.iwcs.domain.hikSync.HikSyncResponse;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.SubTaskStatusEnum;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hik的回调方法
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HikCallbackIwcsService {
    private static final Logger logger = LoggerFactory.getLogger(HikCallbackIwcsService.class);
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;

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
            default: break;
        }
        return new HikSyncResponse();
    }

    /**
     * 任务开始时回调的方法
     * @param hikCallBackAgvMove
     */
    private void taskStart(HikCallBackAgvMove hikCallBackAgvMove) {
        logger.debug("任务{}的搬运任务开始", hikCallBackAgvMove.getTaskCode());
        subTaskMapper.updateRobotCodeByBerCode(hikCallBackAgvMove.getTaskCode(), hikCallBackAgvMove.getRobotCode());
    }

    /**
     * 走出储位时回调的方法
     * @param hikCallBackAgvMove
     */
    private void taskLeavePoint(HikCallBackAgvMove hikCallBackAgvMove) {
        logger.debug("任务{}已走出储位", hikCallBackAgvMove.getTaskCode());
        //1. 查询子任务信息
        //使用多个条件进行检查,防止因为网络延时等原因,没有及时接受到消息而造成的异常操作
        SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
        if (subTask != null) {
            //subTask == null时说明没有生成任务单,这里认为此次请求为人工调用
            logger.warn("任务号" + hikCallBackAgvMove.getTaskCode() + "没有匹配的任务");
            publicCheckSubTask(hikCallBackAgvMove, subTask);
            if (!SubTaskStatusEnum.Executing.getStatusCode().equals(subTask.getTaskStatus())) {
                throw new BusinessException(hikCallBackAgvMove.getTaskCode() + "任务异常: 任务状态不匹配");
            }
        }

        //2. 更新地码信息,因为此时使用的是起始点地码,为了代码复用,这里将起始点地码赋值给终点地码
        hikCallBackAgvMove.setMapDataCode(hikCallBackAgvMove.getWbCode());
        hikCallBackAgvMove.setPodCode("");
        updateMapInfo(hikCallBackAgvMove, subTask);


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
            throw new BusinessException("地码锁定源与子任务号不匹配,子任务号:" + subTask.getSubTaskNum()
                    + " 地码编号:" + hikCallBackAgvMove.getMapDataCode());
        }
        //解锁这个储位
        baseMapBerth.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
        baseMapBerth.setPodCode(hikCallBackAgvMove.getPodCode());
        baseMapBerth.setLockSource("");
        //更新储位信息,加货架号,解锁
        baseMapBerthMapper.updateByPrimaryKeySelective(baseMapBerth);
    }

    private void publicCheckSubTask(HikCallBackAgvMove hikCallBackAgvMove, SubTask subTask) {
        if (!hikCallBackAgvMove.getPodCode().equals(subTask.getPodCode())) {
            throw new BusinessException(hikCallBackAgvMove.getTaskCode() + "任务异常: 货架号不匹配");
        }
        if (!hikCallBackAgvMove.getRobotCode().equals(subTask.getRobotCode())) {
            throw new BusinessException(hikCallBackAgvMove.getTaskCode() + "任务异常: 机器人编号不匹配");
        }
    }

    /**
     * 任务完成时回调的方法
     * @param hikCallBackAgvMove
     */
    private void taskFinished(HikCallBackAgvMove hikCallBackAgvMove) {
        logger.debug("任务{}已结束", hikCallBackAgvMove.getTaskCode());
        //1. 查询子任务信息
        //当subTask = null 时认为此次调用为人工调用,没有生成任务单
        SubTask subTask = subTaskMapper.selectByTaskCode(hikCallBackAgvMove.getTaskCode());
        //使用多个条件进行检查,防止因为网络延时等原因,没有及时接受到消息而造成的异常操作
        if (subTask != null) {
            publicCheckSubTask(hikCallBackAgvMove, subTask);
            if (!SubTaskStatusEnum.Finished.getStatusCode().equals(subTask.getTaskStatus())) {
                throw new BusinessException(hikCallBackAgvMove.getTaskCode() + "任务异常: 任务状态不匹配");
            }
        }

        //2. 更新地码信息
        updateMapInfo(hikCallBackAgvMove, subTask);

        //3.更新货架信息
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(hikCallBackAgvMove.getPodCode());
        if (basePodDetail == null) {
            throw new BusinessException(hikCallBackAgvMove.getPodCode() + "货架号无对应货架信息");
        }
        if (subTask != null && !subTask.getSubTaskNum().equals(basePodDetail.getLockSource())) {
            throw new BusinessException("货架锁定源与子任务号不匹配,子任务号:" + subTask.getSubTaskNum()
                    + " 货架编号:" + hikCallBackAgvMove.getPodCode());
        }
        basePodDetail.setCoox(hikCallBackAgvMove.getCooX());
        basePodDetail.setCooy(hikCallBackAgvMove.getCooY());
        basePodDetail.setBerCode(hikCallBackAgvMove.getMapDataCode());
        basePodDetail.setMapCode(hikCallBackAgvMove.getMapCode());
        basePodDetail.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
        basePodDetail.setLockSource("");
        //更新货架信息表
        basePodDetailMapper.updateByPrimaryKeySelective(basePodDetail);
    }
}
