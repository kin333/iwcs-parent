package com.wisdom.iwcs.domain.inv.dto;

import com.wisdom.iwcs.domain.stock.dto.StockDTO;

/**
 * @Auther: panzun
 * @Date: 2019-3-21 11:17
 * @Description:
 */

public class InvTaskStartDto extends StockDTO {
    private String srcInvNo;
    private String wbCode;
    private Integer invTaskCount;
    private Integer invQty;
    private String bincode;
    private String bizOrderCode;

    public String getSrcInvNo() {
        return srcInvNo;
    }

    public void setSrcInvNo(String srcInvNo) {
        this.srcInvNo = srcInvNo;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public Integer getInvTaskCount() {
        return invTaskCount;
    }

    public void setInvTaskCount(Integer invTaskCount) {
        this.invTaskCount = invTaskCount;
    }

    public Integer getInvQty() {
        return invQty;
    }

    public void setInvQty(Integer invQty) {
        this.invQty = invQty;
    }

    @Override
    public String getBincode() {
        return bincode;
    }

    @Override
    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public String getBizOrderCode() {
        return bizOrderCode;
    }

    public void setBizOrderCode(String bizOrderCode) {
        this.bizOrderCode = bizOrderCode;
    }
}
