package com.wisdom.iwcs.commonDto.fliterCondition.stockCondition;

import com.wisdom.iwcs.commonDto.strategy.PodStrategyEnum;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodParamDTO;

import java.util.List;

/**
 * 出库计算仓位条件
 *
 * @author ted
 * @create 2019-03-04 下午3:10
 **/
public class OutstockCalConditon {
    /**
     * 出库库存需求参数，描述需要的库存情况
     */
    private List<OutstockCalPodParamDTO> outstockCalPodParamDTOS;


    private List<PodStrategyEnum> podStrategyEnums;

    private List<String> excludePods;


    public List<OutstockCalPodParamDTO> getOutstockCalPodParamDTOS() {
        return outstockCalPodParamDTOS;
    }

    public void setOutstockCalPodParamDTOS(List<OutstockCalPodParamDTO> outstockCalPodParamDTOS) {
        this.outstockCalPodParamDTOS = outstockCalPodParamDTOS;
    }

    public List<PodStrategyEnum> getPodStrategyEnums() {
        return podStrategyEnums;
    }

    public void setPodStrategyEnums(List<PodStrategyEnum> podStrategyEnums) {
        this.podStrategyEnums = podStrategyEnums;
    }

    public List<String> getExcludePods() {
        return excludePods;
    }

    public void setExcludePods(List<String> excludePods) {
        this.excludePods = excludePods;
    }
}
