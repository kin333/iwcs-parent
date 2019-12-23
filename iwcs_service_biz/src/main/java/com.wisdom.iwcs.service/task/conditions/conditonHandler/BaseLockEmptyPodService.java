package com.wisdom.iwcs.service.task.conditions.conditonHandler;


import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockPodCondition;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import liquibase.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(BaseLockEmptyPodService.class);
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
        logger.info("子任务{},开始锁定货架", subTaskCondition.getSubTaskNum());
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);

        logger.debug("子任务{},开始生成锁定条件", subTaskCondition.getSubTaskNum());
        //添加锁定条件
        List<LockPodCondition> lockPodConditions = new ArrayList<>();
        for (AreaCondition areaCondition : areaConditions) {
            //优先条件
            LockPodCondition lockPodCondition = new LockPodCondition();
            lockPodCondition.setMapCode(subTask.getMapCode());
            lockPodCondition.setLockSource(subTask.getSubTaskNum());
            lockPodCondition.setOperateAreaCode(areaCondition.getArea());
            lockPodCondition.setBizType(areaCondition.getBizType());
            lockPodCondition.setInStock(inStock);
            lockPodCondition.setPodType(areaCondition.getPodType());
            if (StringUtils.isNotEmpty(areaCondition.getBizSecondArea())) {
                lockPodCondition.setBizSecondAreaCode(areaCondition.getBizSecondArea());
            }
            lockPodConditions.add(lockPodCondition);
        }

        //锁定货架
        Result result = mapResouceService.lockPodByCondition(lockPodConditions);
        if (result.getReturnCode() != HttpStatus.OK.value()) {
            logger.warn("子任务{}锁定空货架失败", subTaskCondition.getSubTaskNum());
            return false;
        }
        BasePodDetail basePodDetail = (BasePodDetail)result.getReturnData();
        logger.info("子任务{},已锁定空货架,开始同步子任务单信息", subTaskCondition.getSubTaskNum());
        //更新子任务单中的货架号
        subTaskMapper.updatePodAndBerBySubTaskCode(subTask.getSubTaskNum(), basePodDetail.getPodCode(), basePodDetail.getBerCode());
        return true;
    }


    /**
     * handleCondition
     * @param subTaskCondition
     * @param areaConditions 包含点位目标的区域以及次级区域（超越项目）
     * @param inStock 是否有货
     * @return
     */
    public boolean handleConditionServices(SubTaskCondition subTaskCondition, List<AreaCondition> areaConditions, String inStock) {
        logger.info("子任务{},开始锁定货架", subTaskCondition.getSubTaskNum());
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);

        logger.debug("子任务{},开始生成锁定条件", subTaskCondition.getSubTaskNum());
        //添加锁定条件
        List<LockPodCondition> lockPodConditions = new ArrayList<>();
        for (AreaCondition areaCondition : areaConditions) {
            //优先条件
            LockPodCondition lockPodCondition = new LockPodCondition();
            lockPodCondition.setMapCode(subTask.getMapCode());
            lockPodCondition.setLockSource(subTask.getSubTaskNum());
            lockPodCondition.setOperateAreaCode(areaCondition.getArea());
            lockPodCondition.setBizType(areaCondition.getBizType());
            lockPodCondition.setInStock(inStock);
            if (StringUtils.isNotEmpty(areaCondition.getBizSecondArea())) {
                lockPodCondition.setBizSecondAreaCode(areaCondition.getBizSecondArea());
            }
            lockPodConditions.add(lockPodCondition);
        }

        //锁定货架
        Result result = mapResouceService.lockPodByConditions(lockPodConditions);
        if (result.getReturnCode() != HttpStatus.OK.value()) {
            logger.warn("子任务{}锁定空货架失败", subTaskCondition.getSubTaskNum());
            return false;
        }
        BasePodDetail basePodDetail = (BasePodDetail)result.getReturnData();
        logger.info("子任务{},已锁定空货架,开始同步子任务单信息", subTaskCondition.getSubTaskNum());
        //更新子任务单中的货架号
        subTaskMapper.updatePodAndBerBySubTaskCode(subTask.getSubTaskNum(), basePodDetail.getPodCode(), basePodDetail.getBerCode());
        return true;
    }



    /**
     * rollbackCondition 工具方法
     * @param subTaskCondition
     * @return
     */
    public boolean rollbackConditionService(SubTaskCondition subTaskCondition) {
        logger.info("子任务{}锁定货架回滚开始", subTaskCondition.getSubTaskNum());
        //还原子任务单中的货架号
        subTaskMapper.updatePodCodeBySubTaskCode(subTaskCondition.getSubTaskNum());
        //还原货架状态
        boolean result = mapResouceService.unlockPod(subTaskCondition.getSubTaskNum());
        if (result) {
            logger.info("子任务{}回滚成功", subTaskCondition.getSubTaskNum());
        } else {
            logger.info("子任务{}回滚失败", subTaskCondition.getSubTaskNum());
        }
        return result;
    }
}
