package com.wisdom.iwcs.domain.outstock.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/5 15:11
 */
public class SyncOuterTaskProcessDTO {
    /**
     * 出库货架
     */
    private String bincode;
    /**
     * 应拿数量
     */
    private BigDecimal takeQty;

    private String cargoOwner;

    /**
     * 物料号
     */
    private String matCode;

    /**
     * 批次号
     */
    private String batchNum;
    /**
     * 特征值1
     */
    private String stkCharacter1;

    /**
     * 特征值2
     */
    private String stkCharacter2;

    /**
     * 特征值3
     */
    private String stkCharacter3;

    /**
     * 特征值4
     */
    private String stkCharacter4;

    /**
     * 特征值5
     */
    private String stkCharacter5;

    /**
     * 出库SN
     */
    private List<String> outSns;

    @Override
    public String toString() {
        return "SyncOuterTaskProcessDTO{" +
                "bincode='" + bincode + '\'' +
                ", takeQty=" + takeQty +
                ", cargoOwner='" + cargoOwner + '\'' +
                ", matCode='" + matCode + '\'' +
                ", batchNum='" + batchNum + '\'' +
                ", stkCharacter1='" + stkCharacter1 + '\'' +
                ", stkCharacter2='" + stkCharacter2 + '\'' +
                ", stkCharacter3='" + stkCharacter3 + '\'' +
                ", stkCharacter4='" + stkCharacter4 + '\'' +
                ", stkCharacter5='" + stkCharacter5 + '\'' +
                ", outSns=" + outSns +
                '}';
    }

    public List<String> getOutSns() {
        return outSns;
    }

    public void setOutSns(List<String> outSns) {
        this.outSns = outSns;
    }

    public String getCargoOwner() {
        return cargoOwner;
    }

    public void setCargoOwner(String cargoOwner) {
        this.cargoOwner = cargoOwner;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
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

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public BigDecimal getTakeQty() {
        return takeQty;
    }

    public void setTakeQty(BigDecimal takeQty) {
        this.takeQty = takeQty;
    }
}
