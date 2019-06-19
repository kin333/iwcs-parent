package com.wisdom.iwcs.service.common;

import com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum;
import com.wisdom.iwcs.commonDto.fliterCondition.PodFliterCondition;
import com.wisdom.iwcs.commonDto.podPutaway.AbstractPutawayPlan;
import com.wisdom.iwcs.commonDto.strategy.PodStrategyEnum;

import java.util.List;

/**
 * 货架计算
 *
 * @author ted
 * @create 2019-02-28 上午10:11
 **/
public interface IPodCal {
    /**
     * 根据传入的货架搜索条件计算货架并返回货架号
     *
     * @param podFliterCondition
     * @return
     */
    List<String> calPodByPodFliterCondition(PodFliterCondition podFliterCondition);

    List<String> calPodByPodFliterCondition(PodFliterCondition podFliterCondition, List<PodStrategyEnum> podPriorityStrategies);

    /**
     * 根据传入的货架搜索条件计算货架并返回货架号
     *
     * @param podTypeCode
     * @param areaCode
     * @param stkStatus
     * @return
     */
    List<String> calPodByPodFliterCondition(String podTypeCode, String areaCode, String stkStatus);

    /**
     * 根据传入的货架搜索条件计算货架并返回货架号
     *
     * @param podTypeCode
     * @param areaCode
     * @param stkStatus
     * @param count
     * @return
     */
    List<String> calPodByPodFliterCondition(String podTypeCode, String areaCode, String stkStatus, Integer count);

    /**
     * 根据传入的货架搜索条件及业务类型计算货架并返回货架号
     *
     * @param podTaskLockEnum
     * @param podTypeCode
     * @param areaCode
     * @param stkStatus
     * @param count
     * @return
     */
    List<String> calPodByPodFliterCondition(PodTaskLockEnum podTaskLockEnum, String podTypeCode, String areaCode, String stkStatus, Integer count);

    /**
     * 根据传入的货架搜索条件计算货架并返回上架方案
     *
     * @param podFliterCondition
     * @return
     */
    List<AbstractPutawayPlan> calPutawayPlanByPodFliterCondition(PodFliterCondition podFliterCondition);

    /**
     * 根据传入的货架搜索条件及优先级策略计算货架并返回货架号
     *
     * @param podFliterCondition
     * @return
     */
    List<String> calPodByPodFliterConditionWithOrderStrategy(PodFliterCondition podFliterCondition, List<PodStrategyEnum> podPriorityStrategies);

    /**
     * 根据传入的货架搜索条件及优先级策略计算货架并返回货上架方案
     *
     * @param podFliterCondition
     * @return
     */
    List<AbstractPutawayPlan> calPutawayPlanByPodFliterConditionWithOrderStrategy(PodFliterCondition podFliterCondition, List<PodStrategyEnum> podPriorityStrategies);
}
