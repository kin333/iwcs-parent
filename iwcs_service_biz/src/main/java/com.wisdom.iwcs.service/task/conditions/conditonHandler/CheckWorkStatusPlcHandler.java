package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.taskUtils.CreateRouteKeyUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.log.ResPodEvt;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.linebody.impl.LineNotifyService;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.task.impl.SubTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 后置条件检查,检查任务是否完成（通知plc到位信息）
 */
@Service
public class CheckWorkStatusPlcHandler implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(BaseLockEmptyMapService.class);

    @Autowired
    SubTaskMapper subTaskMapper;

    @Autowired
    SubTaskService subTaskService;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    LineNotifyService lineNotifyService;


    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("任务单{}CheckWorkStatusHandler后置条件检查开始", subTaskCondition.getSubTaskNum());
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());

        //判断实际工作状态已结束
        if (TaskConstants.workTaskStatus.END.equals(subTask.getWorkTaskStatus())) {
            //解锁货架
            BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(subTask.getPodCode());
            BasePodDetail tmpBasePodDetail = new BasePodDetail();
            tmpBasePodDetail.setId(basePodDetail.getId());
            tmpBasePodDetail.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
            tmpBasePodDetail.setLockSource("");
            tmpBasePodDetail.setLastModifiedTime(new Date());
            //更新货架信息表
            int changeRows = basePodDetailMapper.updateByPrimaryKeySelective(tmpBasePodDetail);
            if (changeRows <= 0) {
                logger.error("子任务{}后置条件未解锁{}货架", subTask.getSubTaskNum(), subTask.getPodCode());
            }
            logger.info("子任务{}已解锁货架{}", subTask.getSubTaskNum(), subTask.getPodCode());
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(subTask.getEndBercode());

            logger.info("通知线体,小车已经到达{} ",baseMapBerth.getPointAlias());
            lineNotifyService.agvStatusIne(baseMapBerth.getPointAlias(), TaskConstants.agvTaskType.ENTER);

            //发送释放货架消息
            ResPodEvt resPodEvt = new ResPodEvt();
            resPodEvt.setPodCode(subTask.getPodCode());
            resPodEvt.setCreateTime(new Date());
            resPodEvt.setAreaCode(baseMapBerth.getOperateAreaCode());
            resPodEvt.setMapCode(baseMapBerth.getMapCode());
            resPodEvt.setResourcesType(TaskConstants.resourceType.POD_RELEASE);
            resPodEvt.setSubTaskNum(subTask.getSubTaskNum());
            String routeKey = CreateRouteKeyUtils.createPodRelease(baseMapBerth.getMapCode(), baseMapBerth.getOperateAreaCode());
            RabbitMQPublicService.sendInfoByRouteKey(routeKey, resPodEvt);

            logger.info("任务单{}后置条件检查成功", subTaskCondition.getSubTaskNum());
            return true;
        }
        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
