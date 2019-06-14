package com.wisdom.iwcs.domain.inv.dto;

import java.math.BigDecimal;

public class InvResultDataDTO {

    private BigDecimal invQty;

    private Integer invId;

    private String bincode;

    private String invSn;

    private Integer perSn;

    public BigDecimal getInvQty() {
        return invQty;
    }

    public void setInvQty(BigDecimal invQty) {
        this.invQty = invQty;
    }

    public Integer getInvId() {
        return invId;
    }

    public void setInvId(Integer invId) {
        this.invId = invId;
    }

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public String getInvSn() {
        return invSn;
    }

    public void setInvSn(String invSn) {
        this.invSn = invSn;
    }

    public Integer getPerSn() {
        return perSn;
    }

    public void setPerSn(Integer perSn) {
        this.perSn = perSn;
    }

}
