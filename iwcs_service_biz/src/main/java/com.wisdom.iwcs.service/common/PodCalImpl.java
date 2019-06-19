package com.wisdom.iwcs.service.common;

import com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum;
import com.wisdom.iwcs.commonDto.fliterCondition.PodFliterCondition;
import com.wisdom.iwcs.commonDto.podPutaway.AbstractPutawayPlan;
import com.wisdom.iwcs.commonDto.strategy.PodStrategyEnum;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.base.BasePodMapper;
import com.wisdom.iwcs.service.base.IBaseBizConCurrentRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 货架计算实现
 *
 * @author ted
 * @create 2019-03-01 下午2:53
 **/
@Service
public class PodCalImpl implements IPodCal {
    @Autowired
    private BasePodMapper podMapper;
    @Autowired
    private BasePodDetailMapper podDetailMapper;
    @Autowired
    private IBaseBizConCurrentRulesService IBaseBizConCurrentRulesService;


    /**
     * 根据货架条件计算货架
     *
     * @param podFliterCondition
     * @return
     */
    @Override
    public List<String> calPodByPodFliterCondition(PodFliterCondition podFliterCondition) {
        //获取不可并发的任务类型
        Integer typeValuesSum = IBaseBizConCurrentRulesService.returnUnableConcurrentBizTypeValuesSum(PodTaskLockEnum.INSTOCK_TASK);
        podFliterCondition.setExculdePodTaskLockValueSum(typeValuesSum);
        List<BasePodDetail> basePodDetails = podDetailMapper.selectPodByPodFliterCon(podFliterCondition);
        //TODO 排序策略
        //获取货架号（并去重）
        List<String> podCodeList = basePodDetails.stream().map(d -> d.getPodCode()).distinct().collect(Collectors.toList());
        if (podCodeList.size() > podFliterCondition.getRequiredCount()) {
            return podCodeList.subList(0, podFliterCondition.getRequiredCount());
        } else {
            return podCodeList;
        }

    }

    @Override
    public List<String> calPodByPodFliterCondition(PodFliterCondition podFliterCondition, List<PodStrategyEnum> podPriorityStrategies) {
        return this.calPodByPodFliterCondition(podFliterCondition);
    }

    @Override
    public List<String> calPodByPodFliterCondition(String podTypeCode, String areaCode, String stkStatus) {
        List<BasePodDetail> basePodDetails = podDetailMapper.selectByTypeAndAreaCodeAndStkStatus(podTypeCode, areaCode, stkStatus, null);
        List<String> podCodeList = basePodDetails.stream().map(d -> d.getPodCode()).distinct().collect(Collectors.toList());
        return podCodeList;
    }

    @Override
    public List<String> calPodByPodFliterCondition(String podTypeCode, String areaCode, String stkStatus, Integer count) {
        Integer typeValuesSum = IBaseBizConCurrentRulesService.returnUnableConcurrentBizTypeValuesSum(PodTaskLockEnum.INSTOCK_TASK);
        List<BasePodDetail> basePodDetails = podDetailMapper.selectEmptyPodByTypeAndAreaCodeAndStkStatus(podTypeCode, areaCode, stkStatus, count, typeValuesSum);
        List<String> podCodeList = basePodDetails.stream().map(d -> d.getPodCode()).distinct().collect(Collectors.toList());
        return podCodeList;
    }

    @Override
    public List<String> calPodByPodFliterCondition(PodTaskLockEnum podTaskLockEnum, String podTypeCode, String areaCode, String stkStatus, Integer count) {
        List<BasePodDetail> basePodDetails = podDetailMapper.selectByTypeAndAreaCodeAndStkStatus(podTypeCode, areaCode, stkStatus, count);
        List<String> podCodeList = basePodDetails.stream().map(d -> d.getPodCode()).distinct().collect(Collectors.toList());
        if (podCodeList.size() > count) {
            return podCodeList.subList(0, count);
        } else {
            return podCodeList;
        }

    }

    @Override
    public List<AbstractPutawayPlan> calPutawayPlanByPodFliterCondition(PodFliterCondition podFliterCondition) {
        return null;
    }

    @Override
    public List<String> calPodByPodFliterConditionWithOrderStrategy(PodFliterCondition podFliterCondition, List<PodStrategyEnum> podPriorityStrategies) {
        return null;
    }

    @Override
    public List<AbstractPutawayPlan> calPutawayPlanByPodFliterConditionWithOrderStrategy(PodFliterCondition podFliterCondition, List<PodStrategyEnum> podPriorityStrategies) {
        return null;
    }
}
