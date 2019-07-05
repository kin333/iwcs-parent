package com.wisdom.iwcs.service.task.conditions.conditonHandler;


import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockPodCondition;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import liquibase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 锁定某个区域的一个货架的公共服务(工具类)
 * @author han
 */
@Service
public class BaseLockEmptyPodService {
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    SubTaskMapper subTaskMapper;


    /**
     * handleCondition 工具方法
     * @param subTaskCondition
     * @param areaConditions 包含点位目标的区域以及次级区域
     * @param inStock 是否有货
     * @return
     */
    public boolean handleConditionService(SubTaskCondition subTaskCondition, List<AreaCondition> areaConditions, String inStock) {
        Long subTaskId = subTaskCondition.getId();
        SubTask subTask = subTaskMapper.selectByPrimaryKey(subTaskId);

        //添加锁定条件
        List<LockPodCondition> lockPodConditions = new ArrayList<>();
        for (AreaCondition areaCondition : areaConditions) {
            //优先条件
            LockPodCondition lockPodCondition = new LockPodCondition();
            lockPodCondition.setMapCode(subTask.getMapCode());
            lockPodCondition.setOperateAreaCode(areaCondition.getArea());
            lockPodCondition.setLockSource(subTask.getSubTaskNum());
            lockPodCondition.setInStock(inStock);
            lockPodConditions.add(lockPodCondition);
            if (StringUtils.isNotEmpty(areaCondition.getBizSecondArea())) {
                lockPodCondition.setBizSecondAreaCode(areaCondition.getBizSecondArea());
            }
            lockPodConditions.add(lockPodCondition);
        }

        //锁定货架
        Result result = mapResouceService.lockPodByCondition(lockPodConditions);
        if (result.getReturnCode() != HttpStatus.OK.value()) {
            return false;
        }
        BasePodDetail basePodDetail = (BasePodDetail)result.getReturnData();
        //更新子任务单中的货架号
        subTaskMapper.updatePodCodeBySubTaskCode(subTask.getSubTaskNum(), basePodDetail.getPodCode());
        return true;
    }

    /**
     * rollbackCondition 工具方法
     * @param subTaskCondition
     * @return
     */
    public boolean rollbackConditionService(SubTaskCondition subTaskCondition) {
        //还原子任务单中的货架号
        subTaskMapper.updatePodCodeBySubTaskCode(subTaskCondition.getSubTaskNum(), "");
        //还原货架状态
        return mapResouceService.unlockPod(subTaskCondition.getSubTaskNum());
    }
}
