package com.wisdom.iwcs.commonDto.fliterCondition;

import com.wisdom.iwcs.commonDto.fliterConOptions.BasePodDetailConOptions;
import com.wisdom.iwcs.commonDto.fliterConOptions.PodLayerStkStaConOptions;

/**
 * 货架过滤条件
 *
 * @author ted
 * @create 2019-02-28 上午10:36
 **/
public class PodFliterCondition extends BaseFliterCondition {
    /**
     * 层级库存
     */
    private PodLayerStkStaConOptions podLayerStkStaCon;

    /**
     * 动态货架属性可选项
     */
    private BasePodDetailConOptions basePodDetailCon;

    public PodLayerStkStaConOptions getPodLayerStkStaCon() {
        return podLayerStkStaCon;
    }

    public void setPodLayerStkStaCon(PodLayerStkStaConOptions podLayerStkStaCon) {
        this.podLayerStkStaCon = podLayerStkStaCon;
    }

    public BasePodDetailConOptions getBasePodDetailCon() {
        return basePodDetailCon;
    }

    public void setBasePodDetailCon(BasePodDetailConOptions basePodDetailCon) {
        this.basePodDetailCon = basePodDetailCon;
    }
}
