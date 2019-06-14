package com.wisdom.iwcs.commonDto.fliterCondition.stockCondition;

/**
 * 基础库存过滤
 *
 * @author ted
 * @create 2019-03-04 下午3:00
 **/
public class BaseStockFliterCondition {
    /**
     * 仓库编码
     */
    private String whCode;
    /**
     * 库区编码
     */
    private String areaCode;

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
