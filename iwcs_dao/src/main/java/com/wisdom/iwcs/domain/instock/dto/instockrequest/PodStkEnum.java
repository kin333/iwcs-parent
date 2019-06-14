package com.wisdom.iwcs.domain.instock.dto.instockrequest;

/**
 * 货架库存状态
 * 货架整体库存：全空--CompletelyEmpty 有空(有空bincode)－－halfEmpty ,全满：full
 *
 * @author ted
 * @create 2019-03-12 下午7:24
 **/
public enum PodStkEnum {
    COMPLETELY_EMPTY("CompletelyEmpty", "全空", ""), HALF_EMPTY("halfEmpty", "有空bincode", ""), FULL("full", "全满", "");
    private String code;
    private String name;
    private String remark;

    PodStkEnum(String code, String name, String remark) {
        this.code = code;
        this.name = name;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }
}
