package com.wisdom.iwcs.domain.outstock.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 返回出库计算的结果详情
 *
 * @Author: cecilia.yang
 * @Date: 2019/3/1 15:35
 */
public class OutstockCalPodResultDataDTO {

    private String bincode;
    /**
     * 库存编号
     */
    private String stkCode;
    /**
     * 应出数量
     */
    private BigDecimal outstockQty;

    /**
     * 应出SN
     */
    private List<String> outSns;

    @Override
    public String toString() {
        return "OutstockCalPodResultDataDTO{" +
                "bincode='" + bincode + '\'' +
                ", stkCode='" + stkCode + '\'' +
                ", outstockQty=" + outstockQty +
                ", outSns=" + outSns +
                '}';
    }

    public List<String> getOutSns() {
        return outSns;
    }

    public void setOutSns(List<String> outSns) {
        this.outSns = outSns;
    }

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public String getStkCode() {
        return stkCode;
    }

    public void setStkCode(String stkCode) {
        this.stkCode = stkCode;
    }

    public BigDecimal getOutstockQty() {
        return outstockQty;
    }

    public void setOutstockQty(BigDecimal outstockQty) {
        this.outstockQty = outstockQty;
    }
}
