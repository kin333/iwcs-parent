package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.MapPodFilterStrategy;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.MapPodFilterStrategyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用的通过属性配置计算货架号的前置条件
 * @author han
 */
@Service
public class PublicComputePodHandler implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(PublicComputePodHandler.class);
    @Autowired
    BaseLockEmptyPodService baseLockEmptyPodService;
    @Autowired
    MapPodFilterStrategyMapper mapPodFilterStrategyMapper;
    @Autowired
    PublicComputeEndPointHandler publicComputeEndPointHandler;
    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("子任务{}开始通过属性计算货架", subTaskCondition.getSubTaskNum());
        //查询策略信息
        List<AreaCondition> areaConditions = publicComputeEndPointHandler.getAreaConditionByProp(subTaskCondition);
        List<MapPodFilterStrategy> mapPodFilterStrategies = mapPodFilterStrategyMapper.selectMapPodByCode(subTaskCondition.getStrategyCode());
        //获取第一个策略的货架空满状态
        Integer podStock = mapPodFilterStrategies.get(0).getPodStock();
        if (podStock == null) {
            //默认查询空货架
            podStock = 0;
        }
        //获取货架,锁定,添加到子任务
        return baseLockEmptyPodService.handleConditionService(subTaskCondition, areaConditions, podStock.toString());
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyPodService.rollbackConditionService(subTaskCondition);
    }
}
