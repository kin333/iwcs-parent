package com.wisdom.iwcs.domain.hikSync;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/19 9:50
 */
public class SyncNotifyDataDto {

    /**
     * 仓库号
     */
    private String whCode;

    /**
     * 存储类型
     */
    private String stgTypCode;

    /**
     * 存储类型描述
     */
    private String stgTypText;

    /**
     * A是新增或者更新，D是删除
     */
    private String operType;
    /**
     * 仓位类型
     */
    private String podTypCode;

    /**
     * 仓位类型描述
     */
    private String podTypText;

    /**
     * 东
     */
    private String east;

    /**
     * 南
     */
    private String south;

    /**
     * 西
     */
    private String west;

    /**
     * 北
     */
    private String north;

    /**
     * 中
     */
    private String middle;

    /**
     * 货架重量
     */
    private String podWei;

    /**
     * 请求编号
     */
    private String reqCode;

    /**
     * 仓位类型
     */
    private String stgBinTypCode;

    /**
     * 仓位类型描述
     */
    private String stgBinTypText;

    /**
     * 长度
     */
    private String length;

    /**
     * 宽度
     */
    private String width;

    /**
     * 高度
     */
    private String height;
    /**
     * 存储区编号
     */
    private String stgSecCode;

    /**
     * 存储区描述
     */
    private String stgSecText;

    /**
     * 默认为0
     */
    private String maskNum;

    /**
     * 优先级
     */
    private String priority;
    /**
     * 仓库描述
     */
    private String whText;
    /**
     * 地图名称
     */
    private String mapName;

    /**
     * 地码
     */
    private String groundTypeCode;

    /**
     * 类型（1是删格，2是拓扑）
     */
    private String typeCode;

    /**
     * 地图的行
     */
    private String rowCount;

    /**
     * 地图列
     */
    private String colCount;

    /**
     * 地图内容
     */
    private String mapContent;
    /**
     * 工作台地码
     */
    private String wbCode;

    /**
     * X坐标
     */
    private String coox;

    /**
     * Y坐标
     */
    private String cooy;
    /**
     * 工作台简称
     */
    private String shortWbCode;

    /**
     * IP
     */
    private String ip;

    /**
     * 工作台类型
     */
    private String wbType;

    private String mapCode;

    /**
     * 货架编号
     */
    private String podCode;

    /**
     * 货架描述
     */
    private String podText;

    private List<SyncNotifyBinsDto> bins;

    /**
     * 容器编号
     */
    private String ctnrTypCode;
    /**
     * 容器类型
     */
    private String ctnrTypText;

    public String getCtnrTypCode() {
        return ctnrTypCode;
    }

    public void setCtnrTypCode(String ctnrTypCode) {
        this.ctnrTypCode = ctnrTypCode;
    }

    public String getCtnrTypText() {
        return ctnrTypText;
    }

    public void setCtnrTypText(String ctnrTypText) {
        this.ctnrTypText = ctnrTypText;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodText() {
        return podText;
    }

    public void setPodText(String podText) {
        this.podText = podText;
    }

    public List<SyncNotifyBinsDto> getBins() {
        return bins;
    }

    public void setBins(List<SyncNotifyBinsDto> bins) {
        this.bins = bins;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getStgTypCode() {
        return stgTypCode;
    }

    public void setStgTypCode(String stgTypCode) {
        this.stgTypCode = stgTypCode;
    }

    public String getStgTypText() {
        return stgTypText;
    }

    public void setStgTypText(String stgTypText) {
        this.stgTypText = stgTypText;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getPodTypCode() {
        return podTypCode;
    }

    public void setPodTypCode(String podTypCode) {
        this.podTypCode = podTypCode;
    }

    public String getPodTypText() {
        return podTypText;
    }

    public void setPodTypText(String podTypText) {
        this.podTypText = podTypText;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getPodWei() {
        return podWei;
    }

    public void setPodWei(String podWei) {
        this.podWei = podWei;
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getStgBinTypCode() {
        return stgBinTypCode;
    }

    public void setStgBinTypCode(String stgBinTypCode) {
        this.stgBinTypCode = stgBinTypCode;
    }

    public String getStgBinTypText() {
        return stgBinTypText;
    }

    public void setStgBinTypText(String stgBinTypText) {
        this.stgBinTypText = stgBinTypText;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getStgSecCode() {
        return stgSecCode;
    }

    public void setStgSecCode(String stgSecCode) {
        this.stgSecCode = stgSecCode;
    }

    public String getStgSecText() {
        return stgSecText;
    }

    public void setStgSecText(String stgSecText) {
        this.stgSecText = stgSecText;
    }

    public String getMaskNum() {
        return maskNum;
    }

    public void setMaskNum(String maskNum) {
        this.maskNum = maskNum;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    public String getWhText() {
        return whText;
    }

    public void setWhText(String whText) {
        this.whText = whText;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getGroundTypeCode() {
        return groundTypeCode;
    }

    public void setGroundTypeCode(String groundTypeCode) {
        this.groundTypeCode = groundTypeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public String getColCount() {
        return colCount;
    }

    public void setColCount(String colCount) {
        this.colCount = colCount;
    }

    public String getMapContent() {
        return mapContent;
    }

    public void setMapContent(String mapContent) {
        this.mapContent = mapContent;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getCoox() {
        return coox;
    }

    public void setCoox(String coox) {
        this.coox = coox;
    }

    public String getCooy() {
        return cooy;
    }

    public void setCooy(String cooy) {
        this.cooy = cooy;
    }

    public String getShortWbCode() {
        return shortWbCode;
    }

    public void setShortWbCode(String shortWbCode) {
        this.shortWbCode = shortWbCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getWbType() {
        return wbType;
    }

    public void setWbType(String wbType) {
        this.wbType = wbType;
    }
}
