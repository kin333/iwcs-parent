package com.wisdom.iwcs.commonDto.fliterCondition;

import com.wisdom.iwcs.commonDto.fliterConOptions.BaseBincodeDetailConOptions;
import com.wisdom.iwcs.commonDto.fliterConOptions.BasePodBincodeConOptions;
import com.wisdom.iwcs.commonDto.fliterConOptions.BasePodDetailConOptions;

/**
 * 出库呼叫过滤货架
 *
 * @author ted
 * @create 2019-02-28 上午10:36
 **/
public class OutStockPodFliterCondition extends BaseFliterCondition {


    /**
     * 货架库存情况：
     * 全空
     */
    private String podStockLayerSta;


    /**
     * 静态货架属性可选项
     */
    private BasePodConOptions basePodConOptions;

    /**
     * 动态货架属性可选项
     */
    private BasePodDetailConOptions basePodDetailConOptions;

    private BasePodBincodeConOptions basePodBincodeConOptions;

    private BaseBincodeDetailConOptions baseBincodeDetailConOptions;


    public BasePodConOptions getBasePodConOptions() {
        return basePodConOptions;
    }

    public void setBasePodConOptions(BasePodConOptions basePodConOptions) {
        this.basePodConOptions = basePodConOptions;
    }

    public BasePodDetailConOptions getBasePodDetailConOptions() {
        return basePodDetailConOptions;
    }

    public void setBasePodDetailConOptions(BasePodDetailConOptions basePodDetailConOptions) {
        this.basePodDetailConOptions = basePodDetailConOptions;
    }

    public BasePodBincodeConOptions getBasePodBincodeConOptions() {
        return basePodBincodeConOptions;
    }

    public void setBasePodBincodeConOptions(BasePodBincodeConOptions basePodBincodeConOptions) {
        this.basePodBincodeConOptions = basePodBincodeConOptions;
    }

    public BaseBincodeDetailConOptions getBaseBincodeDetailConOptions() {
        return baseBincodeDetailConOptions;
    }

    public void setBaseBincodeDetailConOptions(BaseBincodeDetailConOptions baseBincodeDetailConOptions) {
        this.baseBincodeDetailConOptions = baseBincodeDetailConOptions;
    }


}
