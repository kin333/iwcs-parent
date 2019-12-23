package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.MapPodFilterStrategy;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.MapPodFilterStrategyMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的通过属性配置计算点位的前置条件
 * @author han
 */
@Service
public class PublicComputeEndPointHandler implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(PublicComputeEndPointHandler.class);

    @Autowired
    MapPodFilterStrategyMapper mapPodFilterStrategyMapper;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BaseLockEmptyMapService baseLockEmptyMapService;
    @Autowired
    SubTaskMapper subTaskMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("子任务{}开始通过属性计算点位", subTaskCondition.getSubTaskNum());
        List<AreaCondition> areaConditions = getAreaConditionByProp(subTaskCondition);
        //获取终点,锁定,添加到子任务
        return baseLockEmptyMapService.handleConditionService(subTaskCondition, areaConditions);
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyMapService.rollbackConditionService(subTaskCondition);
    }

    /**
     * 查询当前策略,并生成对应的查询策略
     */
    public List<AreaCondition> getAreaConditionByProp(SubTaskCondition subTaskCondition) {
        //根据策略编号查找对应策略
        List<MapPodFilterStrategy> mapPodFilterStrategies = mapPodFilterStrategyMapper.selectMapPodByCode(subTaskCondition.getStrategyCode());
        if (mapPodFilterStrategies == null || mapPodFilterStrategies.size() <= 0) {
            throw new BusinessException(subTaskCondition.getStrategyCode() + "策略不存在!");
        }
        //生成查找的策略
        List<AreaCondition> areaConditions = new ArrayList<>();
        for (MapPodFilterStrategy mapPodFilterStrategy : mapPodFilterStrategies) {
            AreaCondition areaCondition = new AreaCondition();
            //点位业务类型
            if (StringUtils.isNotEmpty(mapPodFilterStrategy.getBizType())) {
                areaCondition.setBizType(mapPodFilterStrategy.getBizType());
            }
            //作业区域
            if (StringUtils.isNotEmpty(mapPodFilterStrategy.getOperateAreaCode())) {
                areaCondition.setArea(mapPodFilterStrategy.getOperateAreaCode());
            }
            //业务次级区域
            if (StringUtils.isNotEmpty(mapPodFilterStrategy.getBizSecondAreaCode())) {
                areaCondition.setBizSecondArea(mapPodFilterStrategy.getBizSecondAreaCode());
            }
            //货架类型
            if (StringUtils.isNotEmpty(mapPodFilterStrategy.getPodType())) {
                areaCondition.setPodType(mapPodFilterStrategy.getPodType());
            }
            areaConditions.add(areaCondition);
        }
        return areaConditions;
    }
}
