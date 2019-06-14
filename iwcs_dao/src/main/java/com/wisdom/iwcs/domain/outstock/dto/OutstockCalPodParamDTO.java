package com.wisdom.iwcs.domain.outstock.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 出库计算货架参数DTO
 *
 * @Author: cecilia.yang
 * @Date: 2019/3/1 15:35
 */
public class OutstockCalPodParamDTO {
    /**
     * 库区
     */
    private String stgAreaCode;

    /**
     * 货主
     */
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
     * 需要出库的数量
     */
    private BigDecimal outQty;

    /**
     * 容器编号
     */
    private String containerCode;

    /**
     * 规格编号
     */
    private String specCode;


    private Integer excludeLock;

    private String wbTaskNo;


    private List<String> pointSns;

    private boolean havePreSn;

    /**
     * 重写equals方法，库存特征值相等的可为一条属性
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutstockCalPodParamDTO)) return false;
        OutstockCalPodParamDTO that = (OutstockCalPodParamDTO) o;
        return Objects.equals(stgAreaCode, that.stgAreaCode) &&
                Objects.equals(cargoOwner, that.cargoOwner) &&
                Objects.equals(matCode, that.matCode) &&
                Objects.equals(batchNum, that.batchNum) &&
                Objects.equals(stkCharacter1, that.stkCharacter1) &&
                Objects.equals(stkCharacter2, that.stkCharacter2) &&
                Objects.equals(stkCharacter3, that.stkCharacter3) &&
                Objects.equals(stkCharacter4, that.stkCharacter4) &&
                Objects.equals(stkCharacter5, that.stkCharacter5) &&
                Objects.equals(containerCode, that.containerCode) &&
                Objects.equals(specCode, that.specCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(stgAreaCode, cargoOwner, matCode, batchNum, stkCharacter1, stkCharacter2, stkCharacter3, stkCharacter4, stkCharacter5, containerCode, specCode);
    }

    @Override
    public String toString() {
        return "OutstockCalPodParamDTO{" +
                "stgAreaCode='" + stgAreaCode + '\'' +
                ", cargoOwner='" + cargoOwner + '\'' +
                ", matCode='" + matCode + '\'' +
                ", batchNum='" + batchNum + '\'' +
                ", stkCharacter1='" + stkCharacter1 + '\'' +
                ", stkCharacter2='" + stkCharacter2 + '\'' +
                ", stkCharacter3='" + stkCharacter3 + '\'' +
                ", stkCharacter4='" + stkCharacter4 + '\'' +
                ", stkCharacter5='" + stkCharacter5 + '\'' +
                ", outQty=" + outQty +
                ", containerCode='" + containerCode + '\'' +
                ", specCode='" + specCode + '\'' +
                ", excludeLock=" + excludeLock +
                ", wbTaskNo='" + wbTaskNo + '\'' +
                '}';
    }

    public List<String> getPointSns() {
        return pointSns;
    }

    public void setPointSns(List<String> pointSns) {
        this.pointSns = pointSns;
    }

    public boolean isHavePreSn() {
        return havePreSn;
    }

    public void setHavePreSn(boolean havePreSn) {
        this.havePreSn = havePreSn;
    }

    public String getWbTaskNo() {
        return wbTaskNo;
    }

    public void setWbTaskNo(String wbTaskNo) {
        this.wbTaskNo = wbTaskNo;
    }

    public String getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getStgAreaCode() {
        return stgAreaCode;
    }

    public void setStgAreaCode(String stgAreaCode) {
        this.stgAreaCode = stgAreaCode;
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

    public BigDecimal getOutQty() {
        return outQty;
    }

    public void setOutQty(BigDecimal outQty) {
        this.outQty = outQty;
    }

    public Integer getExcludeLock() {
        return excludeLock;
    }

    public void setExcludeLock(Integer excludeLock) {
        this.excludeLock = excludeLock;
    }
}
