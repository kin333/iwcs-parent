package com.wisdom.iwcs.domain.stock.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 加减库存：targetBincode、adjustQty/adjustSns、target开头的库存属性
 * 条码替换：targetBincode、sourceSn、targetSn、target开头的库存属性
 * 库存移动：sourceBincode、targetBincode、adjustQty+source开头的库存属性/adjustSns
 * 库存属性变更：targetBincode、adjustQty/adjustSns、source开头的库存属性、target开头的库存属性
 *
 * @Author: cecilia.yang
 * @Date: 2019/3/21 15:50
 */
public class StockAdjustDataDTO {
    /**
     * 源货架
     */
    private String sourceBincode;
    /**
     * 目标货架
     */
    private String targetBincode;
    /**
     * 源SN
     */
    private String sourceSn;
    /**
     * 目标SN
     */
    private String targetSn;
    /**
     * 调整数量
     */
    private BigDecimal adjustQty;
    /**
     * 调整条码
     */
    private List<String> adjustSns;

    /**
     * 源货主
     */
    private String sourceCargoOwner;

    /**
     * 源物料号
     */
    private String sourceMatCode;
    /**
     * 源批次号
     */
    private String sourceBatchNum;

    /**
     * 源容器编号
     */
    private String sourceContainerCode;

    /**
     * 源规格编号
     */
    private String sourceSpecCode;

    /**
     * 源特征值1
     */
    private String sourceStkCharacter1;

    /**
     * 源特征值2
     */
    private String sourceStkCharacter2;

    /**
     * 源特征值3
     */
    private String sourceStkCharacter3;

    /**
     * 源特征值4
     */
    private String sourceStkCharacter4;

    /**
     * 源特征值5
     */
    private String sourceStkCharacter5;
    /**
     * 目标货主
     */
    private String targetCargoOwner;

    /**
     * 目标物料号
     */
    private String targetMatCode;
    /**
     * 目标批次号
     */
    private String targetBatchNum;

    /**
     * 目标容器编号
     */
    private String targetContainerCode;

    /**
     * 目标规格编号
     */
    private String targetSpecCode;

    /**
     * 目标特征值1
     */
    private String targetStkCharacter1;

    /**
     * 目标特征值2
     */
    private String targetStkCharacter2;

    /**
     * 目标特征值3
     */
    private String targetStkCharacter3;

    /**
     * 目标特征值4
     */
    private String targetStkCharacter4;

    /**
     * 目标特征值5
     */
    private String targetStkCharacter5;

    @Override
    public String toString() {
        return "StockAdjustDataDTO{" +
                "sourceBincode='" + sourceBincode + '\'' +
                ", targetBincode='" + targetBincode + '\'' +
                ", sourceSn='" + sourceSn + '\'' +
                ", targetSn='" + targetSn + '\'' +
                ", adjustQty=" + adjustQty +
                ", adjustSns=" + adjustSns +
                ", sourceCargoOwner='" + sourceCargoOwner + '\'' +
                ", sourceMatCode='" + sourceMatCode + '\'' +
                ", sourceBatchNum='" + sourceBatchNum + '\'' +
                ", sourceContainerCode='" + sourceContainerCode + '\'' +
                ", sourceSpecCode='" + sourceSpecCode + '\'' +
                ", sourceStkCharacter1='" + sourceStkCharacter1 + '\'' +
                ", sourceStkCharacter2='" + sourceStkCharacter2 + '\'' +
                ", sourceStkCharacter3='" + sourceStkCharacter3 + '\'' +
                ", sourceStkCharacter4='" + sourceStkCharacter4 + '\'' +
                ", sourceStkCharacter5='" + sourceStkCharacter5 + '\'' +
                ", targetCargoOwner='" + targetCargoOwner + '\'' +
                ", targetMatCode='" + targetMatCode + '\'' +
                ", targetBatchNum='" + targetBatchNum + '\'' +
                ", targetContainerCode='" + targetContainerCode + '\'' +
                ", targetSpecCode='" + targetSpecCode + '\'' +
                ", targetStkCharacter1='" + targetStkCharacter1 + '\'' +
                ", targetStkCharacter2='" + targetStkCharacter2 + '\'' +
                ", targetStkCharacter3='" + targetStkCharacter3 + '\'' +
                ", targetStkCharacter4='" + targetStkCharacter4 + '\'' +
                ", targetStkCharacter5='" + targetStkCharacter5 + '\'' +
                '}';
    }

    public String getSourceBincode() {
        return sourceBincode;
    }

    public void setSourceBincode(String sourceBincode) {
        this.sourceBincode = sourceBincode;
    }

    public String getTargetBincode() {
        return targetBincode;
    }

    public void setTargetBincode(String targetBincode) {
        this.targetBincode = targetBincode;
    }

    public String getSourceSn() {
        return sourceSn;
    }

    public void setSourceSn(String sourceSn) {
        this.sourceSn = sourceSn;
    }

    public String getTargetSn() {
        return targetSn;
    }

    public void setTargetSn(String targetSn) {
        this.targetSn = targetSn;
    }

    public BigDecimal getAdjustQty() {
        return adjustQty;
    }

    public void setAdjustQty(BigDecimal adjustQty) {
        this.adjustQty = adjustQty;
    }

    public List<String> getAdjustSns() {
        return adjustSns;
    }

    public void setAdjustSns(List<String> adjustSns) {
        this.adjustSns = adjustSns;
    }

    public String getSourceCargoOwner() {
        return sourceCargoOwner;
    }

    public void setSourceCargoOwner(String sourceCargoOwner) {
        this.sourceCargoOwner = sourceCargoOwner;
    }

    public String getSourceMatCode() {
        return sourceMatCode;
    }

    public void setSourceMatCode(String sourceMatCode) {
        this.sourceMatCode = sourceMatCode;
    }

    public String getSourceBatchNum() {
        return sourceBatchNum;
    }

    public void setSourceBatchNum(String sourceBatchNum) {
        this.sourceBatchNum = sourceBatchNum;
    }

    public String getSourceContainerCode() {
        return sourceContainerCode;
    }

    public void setSourceContainerCode(String sourceContainerCode) {
        this.sourceContainerCode = sourceContainerCode;
    }

    public String getSourceSpecCode() {
        return sourceSpecCode;
    }

    public void setSourceSpecCode(String sourceSpecCode) {
        this.sourceSpecCode = sourceSpecCode;
    }

    public String getSourceStkCharacter1() {
        return sourceStkCharacter1;
    }

    public void setSourceStkCharacter1(String sourceStkCharacter1) {
        this.sourceStkCharacter1 = sourceStkCharacter1;
    }

    public String getSourceStkCharacter2() {
        return sourceStkCharacter2;
    }

    public void setSourceStkCharacter2(String sourceStkCharacter2) {
        this.sourceStkCharacter2 = sourceStkCharacter2;
    }

    public String getSourceStkCharacter3() {
        return sourceStkCharacter3;
    }

    public void setSourceStkCharacter3(String sourceStkCharacter3) {
        this.sourceStkCharacter3 = sourceStkCharacter3;
    }

    public String getSourceStkCharacter4() {
        return sourceStkCharacter4;
    }

    public void setSourceStkCharacter4(String sourceStkCharacter4) {
        this.sourceStkCharacter4 = sourceStkCharacter4;
    }

    public String getSourceStkCharacter5() {
        return sourceStkCharacter5;
    }

    public void setSourceStkCharacter5(String sourceStkCharacter5) {
        this.sourceStkCharacter5 = sourceStkCharacter5;
    }

    public String getTargetCargoOwner() {
        return targetCargoOwner;
    }

    public void setTargetCargoOwner(String targetCargoOwner) {
        this.targetCargoOwner = targetCargoOwner;
    }

    public String getTargetMatCode() {
        return targetMatCode;
    }

    public void setTargetMatCode(String targetMatCode) {
        this.targetMatCode = targetMatCode;
    }

    public String getTargetBatchNum() {
        return targetBatchNum;
    }

    public void setTargetBatchNum(String targetBatchNum) {
        this.targetBatchNum = targetBatchNum;
    }

    public String getTargetContainerCode() {
        return targetContainerCode;
    }

    public void setTargetContainerCode(String targetContainerCode) {
        this.targetContainerCode = targetContainerCode;
    }

    public String getTargetSpecCode() {
        return targetSpecCode;
    }

    public void setTargetSpecCode(String targetSpecCode) {
        this.targetSpecCode = targetSpecCode;
    }

    public String getTargetStkCharacter1() {
        return targetStkCharacter1;
    }

    public void setTargetStkCharacter1(String targetStkCharacter1) {
        this.targetStkCharacter1 = targetStkCharacter1;
    }

    public String getTargetStkCharacter2() {
        return targetStkCharacter2;
    }

    public void setTargetStkCharacter2(String targetStkCharacter2) {
        this.targetStkCharacter2 = targetStkCharacter2;
    }

    public String getTargetStkCharacter3() {
        return targetStkCharacter3;
    }

    public void setTargetStkCharacter3(String targetStkCharacter3) {
        this.targetStkCharacter3 = targetStkCharacter3;
    }

    public String getTargetStkCharacter4() {
        return targetStkCharacter4;
    }

    public void setTargetStkCharacter4(String targetStkCharacter4) {
        this.targetStkCharacter4 = targetStkCharacter4;
    }

    public String getTargetStkCharacter5() {
        return targetStkCharacter5;
    }

    public void setTargetStkCharacter5(String targetStkCharacter5) {
        this.targetStkCharacter5 = targetStkCharacter5;
    }
}
