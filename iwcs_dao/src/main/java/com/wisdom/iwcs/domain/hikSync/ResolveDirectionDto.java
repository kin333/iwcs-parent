package com.wisdom.iwcs.domain.hikSync;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/20 9:17
 */
public class ResolveDirectionDto {

    private Integer layer;

    private String bincodeNum;

    private String binTypeCode;

    private Integer direction;

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

    public String getBincodeNum() {
        return bincodeNum;
    }

    public void setBincodeNum(String bincodeNum) {
        this.bincodeNum = bincodeNum;
    }

    public String getBinTypeCode() {
        return binTypeCode;
    }

    public void setBinTypeCode(String binTypeCode) {
        this.binTypeCode = binTypeCode;
    }
}
