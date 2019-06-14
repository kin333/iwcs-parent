package com.wisdom.iwcs.commonDto.strategy;

/**
 * 货架策略
 *
 * @author ted
 * @create 2019-03-01 上午11:08
 **/
public enum PodStrategyEnum {
    MIN_POD_COUNT("货架数最少", "MIN_POD_COUNT", "货架最少"),
    LAYER_LOWER_FIRST("货架底层空优先", "LAYER_LOWER_FIRST", "货架底层空优先");

    private String stragegyName;
    private String stragegyCode;
    private String remark;

    PodStrategyEnum(String stragegyName, String stragegyCode, String remark) {
        this.stragegyName = stragegyName;
        this.stragegyCode = stragegyCode;
        this.remark = remark;
    }

    public static PodStrategyEnum getByStragegyCode(String stragegyCode) {
        for (PodStrategyEnum podStrategyEnum : PodStrategyEnum.values()) {
            if (podStrategyEnum.getStragegyCode().equals(stragegyCode)) {
                return podStrategyEnum;
            }
        }
        return null;
    }

    public String getStragegyCode() {
        return stragegyCode;
    }
}
