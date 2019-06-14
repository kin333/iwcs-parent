package com.wisdom.iwcs.domain.instock.dto.instockrequest;


import com.wisdom.iwcs.commonDto.fliterConOptions.LayerConTypeEnum;

import java.util.List;

/**
 * @Description: 入库呼叫策略参数明细
 * @Author: george
 * @CreateDate: 2019/3/19 16:59
 */
public class InstockCallStraParamDetail {

    /**
     * 层级条件类型
     * specLayers
     * specLayerUp
     * specLayerDown
     * specLayerBetween
     */
    private LayerConTypeEnum layerConType;
    /**
     * 层级
     */
    private List<Integer> specificLayers;
    /**
     * 库存状态，已满，全空
     */
    private String layerStkSta;
    /**
     * 货架预留属性，如货主
     */
    private String podProp1;
    /**
     * 存储区类型代码
     */
    private String stgTypeCode;

    /**
     * 存储区代码
     */
    private String stgCode;

    /**
     * 货架类型
     */
    private List<String> podTypeCodes;

    /**
     * 货架预留属性
     */
    private String podProp2;

    /**
     * 货架预留属性
     */
    private String podProp3;

    /**
     * 货架预留属性
     */
    private String podProp4;

    /**
     * 货架预留属性
     */
    private String podProp5;

    /**
     * 货架整体库存：全空--CompletelyEmpty 有空(有空bincode)－－halfEmpty ,全满：full
     */
    private PodStkEnum podStkSta;

    @Override
    public String toString() {
        return "InstockCallStraParamDetail{" +
                "layerConType=" + layerConType +
                ", specificLayers=" + specificLayers +
                ", layerStkSta='" + layerStkSta + '\'' +
                ", podProp1='" + podProp1 + '\'' +
                ", stgTypeCode='" + stgTypeCode + '\'' +
                ", stgCode='" + stgCode + '\'' +
                ", podTypeCodes=" + podTypeCodes +
                ", podProp2='" + podProp2 + '\'' +
                ", podProp3='" + podProp3 + '\'' +
                ", podProp4='" + podProp4 + '\'' +
                ", podProp5='" + podProp5 + '\'' +
                ", podStkSta=" + podStkSta +
                '}';
    }

    public LayerConTypeEnum getLayerConType() {
        return layerConType;
    }

    public void setLayerConType(LayerConTypeEnum layerConType) {
        this.layerConType = layerConType;
    }

    public List<Integer> getSpecificLayers() {
        return specificLayers;
    }

    public void setSpecificLayers(List<Integer> specificLayers) {
        this.specificLayers = specificLayers;
    }

    public String getLayerStkSta() {
        return layerStkSta;
    }

    public void setLayerStkSta(String layerStkSta) {
        this.layerStkSta = layerStkSta;
    }

    public String getPodProp1() {
        return podProp1;
    }

    public void setPodProp1(String podProp1) {
        this.podProp1 = podProp1;
    }

    public String getStgTypeCode() {
        return stgTypeCode;
    }

    public void setStgTypeCode(String stgTypeCode) {
        this.stgTypeCode = stgTypeCode;
    }

    public String getStgCode() {
        return stgCode;
    }

    public void setStgCode(String stgCode) {
        this.stgCode = stgCode;
    }

    public String getPodProp2() {
        return podProp2;
    }

    public void setPodProp2(String podProp2) {
        this.podProp2 = podProp2;
    }

    public String getPodProp3() {
        return podProp3;
    }

    public void setPodProp3(String podProp3) {
        this.podProp3 = podProp3;
    }

    public String getPodProp4() {
        return podProp4;
    }

    public void setPodProp4(String podProp4) {
        this.podProp4 = podProp4;
    }

    public String getPodProp5() {
        return podProp5;
    }

    public void setPodProp5(String podProp5) {
        this.podProp5 = podProp5;
    }

    public List<String> getPodTypeCodes() {
        return podTypeCodes;
    }

    public void setPodTypeCodes(List<String> podTypeCodes) {
        this.podTypeCodes = podTypeCodes;
    }

    public PodStkEnum getPodStkSta() {
        return podStkSta;
    }

    public void setPodStkSta(PodStkEnum podStkSta) {
        this.podStkSta = podStkSta;
    }
}
