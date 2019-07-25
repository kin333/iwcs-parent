package com.wisdom.iwcs.domain.linebody;

import javax.persistence.*;

@Table(name = "line_body")
public class LineBody {
    @Id
    private Integer id;

    /**
     * 线体编号
     */
    @Column(name = "line_code")
    private String lineCode;

    /**
     * 通信编号（三方通信协议使用）
     */
    @Column(name = "msg_code")
    private String msgCode;

    /**
     * 名称
     */
    @Column(name = "line_name")
    private String lineName;

    /**
     * 通信ip
     */
    @Column(name = "line_ip")
    private String lineIp;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 地图编号
     */
    @Column(name = "map_code")
    private String mapCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取线体编号
     *
     * @return line_code - 线体编号
     */
    public String getLineCode() {
        return lineCode;
    }

    /**
     * 设置线体编号
     *
     * @param lineCode 线体编号
     */
    public void setLineCode(String lineCode) {
        this.lineCode = lineCode == null ? null : lineCode.trim();
    }

    /**
     * 获取通信编号（三方通信协议使用）
     *
     * @return msg_code - 通信编号（三方通信协议使用）
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * 设置通信编号（三方通信协议使用）
     *
     * @param msgCode 通信编号（三方通信协议使用）
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    /**
     * 获取名称
     *
     * @return line_name - 名称
     */
    public String getLineName() {
        return lineName;
    }

    /**
     * 设置名称
     *
     * @param lineName 名称
     */
    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    /**
     * 获取通信ip
     *
     * @return line_ip - 通信ip
     */
    public String getLineIp() {
        return lineIp;
    }

    /**
     * 设置通信ip
     *
     * @param lineIp 通信ip
     */
    public void setLineIp(String lineIp) {
        this.lineIp = lineIp == null ? null : lineIp.trim();
    }

    /**
     * 获取楼层
     *
     * @return floor - 楼层
     */
    public Integer getFloor() {
        return floor;
    }

    /**
     * 设置楼层
     *
     * @param floor 楼层
     */
    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    /**
     * 获取地图编号
     *
     * @return map_code - 地图编号
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * 设置地图编号
     *
     * @param mapCode 地图编号
     */
    public void setMapCode(String mapCode) {
        this.mapCode = mapCode == null ? null : mapCode.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}