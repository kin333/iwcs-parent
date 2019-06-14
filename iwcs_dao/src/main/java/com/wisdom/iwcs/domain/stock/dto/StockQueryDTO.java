package com.wisdom.iwcs.domain.stock.dto;

/**
 * 查询唯一一条库存的DTO
 * 库存的已知、未知属性
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/26 13:42
 */
public class StockQueryDTO {
    /**
     * 货主
     */
    private String cargoOwner;

    /**
     * 物料号
     */
    private String matCode;

    /**
     * 仓位号
     */
    private String bincode;
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
     * 库区
     */
    private String stgAreaCode;

    /**
     * 批次号
     */
    private String batchNum;

    /**
     * 容器编号
     */
    private String containerCode;

    /**
     * 规格编号
     */
    private String specCode;

    @Override
    public String toString() {
        return "StockQueryDTO{" +
                "cargoOwner='" + cargoOwner + '\'' +
                ", matCode='" + matCode + '\'' +
                ", bincode='" + bincode + '\'' +
                ", stkCharacter1='" + stkCharacter1 + '\'' +
                ", stkCharacter2='" + stkCharacter2 + '\'' +
                ", stkCharacter3='" + stkCharacter3 + '\'' +
                ", stkCharacter4='" + stkCharacter4 + '\'' +
                ", stkCharacter5='" + stkCharacter5 + '\'' +
                ", stgAreaCode='" + stgAreaCode + '\'' +
                ", batchNum='" + batchNum + '\'' +
                ", containerCode='" + containerCode + '\'' +
                ", specCode='" + specCode + '\'' +
                '}';
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

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
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

    public String getStgAreaCode() {
        return stgAreaCode;
    }

    public void setStgAreaCode(String stgAreaCode) {
        this.stgAreaCode = stgAreaCode;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
}
