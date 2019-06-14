package com.wisdom.iwcs.commonDto.fliterConOptions;

/**
 * 仓位基础信息
 *
 * @author ted
 * @create 2019-03-01 上午10:18
 **/
public class BasePodBincodeConOptions {
    /**
     * 仓位编号
     */
    private String bincode;

    /**
     * 货架编号
     */
    private String podCode;

    /**
     * 仓位类型code
     */
    private String binTypeCode;

    /**
     * 方向，冗余，方便查询
     */
    private Integer direction;

    /**
     * 层数，冗余，方便查询
     */
    private Integer layer;


    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getBinTypeCode() {
        return binTypeCode;
    }

    public void setBinTypeCode(String binTypeCode) {
        this.binTypeCode = binTypeCode;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }
}
