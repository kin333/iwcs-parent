package com.wisdom.iwcs.commonDto.fliterConOptions;

import java.util.List;

/**
 * 货架层级库存状态条件
 *
 * @author ted
 * @create 2019-03-12 上午9:47
 **/
public class PodLayerStkStaConOptions {
    /**
     * 层级条件类型
     * specLayers
     * specLayerUp
     * specLayerDown
     * specLayerBetween
     */
    private LayerConTypeEnum layerConType;
    private List<Integer> specificLayers;
    private Integer fromLayer;
    private Integer toLayer;
    /**
     * 库存状态，已满，全空
     */
    private String layerStkSta;

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

    public Integer getFromLayer() {
        return fromLayer;
    }

    public void setFromLayer(Integer fromLayer) {
        this.fromLayer = fromLayer;
    }

    public Integer getToLayer() {
        return toLayer;
    }

    public void setToLayer(Integer toLayer) {
        this.toLayer = toLayer;
    }

}
