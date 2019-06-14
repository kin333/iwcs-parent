package com.wisdom.iwcs.domain.outstock.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/22 18:13
 */
public class InitOutstockOrderDataDTO {

    /**
     * 出库材料编号，维护订单配置关系用
     */
    private String orderMatGenCode;

    /**
     * 配置号，如派车单号
     */
    private String configCode;

    /**
     * 配置类型：派车单等
     */
    private String configType;

    /**
     * 配置参数
     */
    private String configProp1;

    /**
     * 配置参数
     */
    private String configProp2;

    /**
     * 配置参数
     */
    private String configProp3;

    /**
     * 配置参数
     */
    private String configProp4;

    /**
     * 配置参数
     */
    private String configProp5;

    /**
     * 业务订单号
     */
    private String bizOrderCode;

    /**
     * 上游item
     */
    private String srcOrderItem;

    /**
     * 物料号
     */
    private String matCode;

    /**
     * 计划出库数量
     */
    private BigDecimal outQty;

    /**
     * 批次
     */
    private String batchNum;

    /**
     * 货主
     */
    private String cargoOwner;

    /**
     * 库区
     */
    private String areaCode;

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
     * 出库单位:计量单位、容器
     */
    private String outUnit;

    /**
     * 是否指定SN出库，0为否，1为是
     */
    private String preSnFlag;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 容器编号
     */
    private String containerCode;

    /**
     * 规格编号
     */
    private String specCode;


    /**
     * 指定SN出库时的条码集合
     */
    private List<String> sns;

    @Override
    public String toString() {
        return "InitOutstockOrderDataDTO{" +
                "orderMatGenCode='" + orderMatGenCode + '\'' +
                ", configCode='" + configCode + '\'' +
                ", configType='" + configType + '\'' +
                ", configProp1='" + configProp1 + '\'' +
                ", configProp2='" + configProp2 + '\'' +
                ", configProp3='" + configProp3 + '\'' +
                ", configProp4='" + configProp4 + '\'' +
                ", configProp5='" + configProp5 + '\'' +
                ", bizOrderCode='" + bizOrderCode + '\'' +
                ", srcOrderItem='" + srcOrderItem + '\'' +
                ", matCode='" + matCode + '\'' +
                ", outQty=" + outQty +
                ", batchNum='" + batchNum + '\'' +
                ", cargoOwner='" + cargoOwner + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", stkCharacter1='" + stkCharacter1 + '\'' +
                ", stkCharacter2='" + stkCharacter2 + '\'' +
                ", stkCharacter3='" + stkCharacter3 + '\'' +
                ", stkCharacter4='" + stkCharacter4 + '\'' +
                ", stkCharacter5='" + stkCharacter5 + '\'' +
                ", outUnit='" + outUnit + '\'' +
                ", preSnFlag='" + preSnFlag + '\'' +
                ", orderType='" + orderType + '\'' +
                ", containerCode='" + containerCode + '\'' +
                ", specCode='" + specCode + '\'' +
                ", sns=" + sns +
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderMatGenCode() {
        return orderMatGenCode;
    }

    public void setOrderMatGenCode(String orderMatGenCode) {
        this.orderMatGenCode = orderMatGenCode;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigProp1() {
        return configProp1;
    }

    public void setConfigProp1(String configProp1) {
        this.configProp1 = configProp1;
    }

    public String getConfigProp2() {
        return configProp2;
    }

    public void setConfigProp2(String configProp2) {
        this.configProp2 = configProp2;
    }

    public String getConfigProp3() {
        return configProp3;
    }

    public void setConfigProp3(String configProp3) {
        this.configProp3 = configProp3;
    }

    public String getConfigProp4() {
        return configProp4;
    }

    public void setConfigProp4(String configProp4) {
        this.configProp4 = configProp4;
    }

    public String getConfigProp5() {
        return configProp5;
    }

    public void setConfigProp5(String configProp5) {
        this.configProp5 = configProp5;
    }

    public String getBizOrderCode() {
        return bizOrderCode;
    }

    public void setBizOrderCode(String bizOrderCode) {
        this.bizOrderCode = bizOrderCode;
    }

    public String getSrcOrderItem() {
        return srcOrderItem;
    }

    public void setSrcOrderItem(String srcOrderItem) {
        this.srcOrderItem = srcOrderItem;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public BigDecimal getOutQty() {
        return outQty;
    }

    public void setOutQty(BigDecimal outQty) {
        this.outQty = outQty;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getCargoOwner() {
        return cargoOwner;
    }

    public void setCargoOwner(String cargoOwner) {
        this.cargoOwner = cargoOwner;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    public String getPreSnFlag() {
        return preSnFlag;
    }

    public void setPreSnFlag(String preSnFlag) {
        this.preSnFlag = preSnFlag;
    }

    public List<String> getSns() {
        return sns;
    }

    public void setSns(List<String> sns) {
        this.sns = sns;
    }
}
