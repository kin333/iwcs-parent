package com.wisdom.iwcs.commonDto.fliterCondition;

/**
 * 基础货架静态属性可筛选项
 *
 * @author ted
 * @create 2019-02-28 上午10:57
 **/
public class BasePodConOptions {
    /**
     * 仓库编码
     */
    private String whCode;

    /**
     * 货架编号
     */
    private String podCode;


    /**
     * 货架类型编码
     */
    private String podTypeCode;

    /**
     * bincode数量
     */
    private Integer binCnt;

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodTypeCode() {
        return podTypeCode;
    }

    public void setPodTypeCode(String podTypeCode) {
        this.podTypeCode = podTypeCode;
    }

    public Integer getBinCnt() {
        return binCnt;
    }

    public void setBinCnt(Integer binCnt) {
        this.binCnt = binCnt;
    }
}
