package com.wisdom.iwcs.domain.instock.dto.instockrequest;

import java.math.BigDecimal;
import java.util.List;

public class DataInboundDTO {
    /**
     * 托盘码，扫描得到，必填项
     */
    private String bincode;
    /**
     * SKU号，必填项
     */
    private String sku;
    /**
     * 入库单号，选填
     */
    private String orderNo;

    /**
     * 入库子单号
     */
    private String subOrderNo;
    /**
     * 件数，必填项
     */
    private BigDecimal inQty;
    /**
     * SN码1，选填项
     */
    private List<String> sn;
    /**
     * 货主
     */
    private String cargoOwner;
    /**
     * 批次
     */
    private String batchNum;
    /**
     * 工厂
     */
    private String plantCode;
    /**
     * 库存属性1
     */
    private String stkCharacter1;
    /**
     * 库存属性2
     */
    private String stkCharacter2;
    /**
     * 库存属性3
     */
    private String stkCharacter3;
    /**
     * 库存属性4
     */
    private String stkCharacter4;
    /**
     * 库存属性5
     */
    private String stkCharacter5;

    @Override
    public String toString() {
        return "DataInboundDTO{" +
                "bincode='" + bincode + '\'' +
                ", sku='" + sku + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", subOrderNo='" + subOrderNo + '\'' +
                ", inQty=" + inQty +
                ", sn=" + sn +
                ", cargoOwner='" + cargoOwner + '\'' +
                ", batchNum='" + batchNum + '\'' +
                ", plantCode='" + plantCode + '\'' +
                ", stkCharacter1='" + stkCharacter1 + '\'' +
                ", stkCharacter2='" + stkCharacter2 + '\'' +
                ", stkCharacter3='" + stkCharacter3 + '\'' +
                ", stkCharacter4='" + stkCharacter4 + '\'' +
                ", stkCharacter5='" + stkCharacter5 + '\'' +
                '}';
    }

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getInQty() {
        return inQty;
    }

    public void setInQty(BigDecimal inQty) {
        this.inQty = inQty;
    }

    public List<String> getSn() {
        return sn;
    }

    public void setSn(List<String> sn) {
        this.sn = sn;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public String getCargoOwner() {
        return cargoOwner;
    }

    public void setCargoOwner(String cargoOwner) {
        this.cargoOwner = cargoOwner;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getStkCharacter1() {
        return stkCharacter1;
    }

    public void setStkCharacter1(String stkCharacter1) {
        this.stkCharacter1 = stkCharacter1;
    }

    public String getStkCharacter2() {
        return stkCharacter2;
    }

    public void setStkCharacter2(String stkCharacter2) {
        this.stkCharacter2 = stkCharacter2;
    }

    public String getStkCharacter3() {
        return stkCharacter3;
    }

    public void setStkCharacter3(String stkCharacter3) {
        this.stkCharacter3 = stkCharacter3;
    }

    public String getStkCharacter4() {
        return stkCharacter4;
    }

    public void setStkCharacter4(String stkCharacter4) {
        this.stkCharacter4 = stkCharacter4;
    }

    public String getStkCharacter5() {
        return stkCharacter5;
    }

    public void setStkCharacter5(String stkCharacter5) {
        this.stkCharacter5 = stkCharacter5;
    }
}
