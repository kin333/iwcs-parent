package com.wisdom.iwcs.domain.elevator;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ele_connection_point")
public class ConnectionPoint {

    @Id
    @Column(name = "coonnection_point_id")
    private Integer coonnectionPointId;

    @Column(name = "connection_point_code")
    private String connectionPointCode;

    @Column(name = "elevator_code")
    private Integer elevatorCode;

    @Column(name = "wb_code")
    private String wbCode;

    private String remark;

    private String status;

    @Column(name = "status_remark")
    private String statusRemark;

    @Column(name = "status_update_time")
    private Date statusUpdateTime;

    private String bercode;

    @Column(name = "map_code")
    private String mapCode;

    @Column(name = "wait_bercode")
    private String waitBercode;

    @Column(name = "connection_point_type")
    private String connectionPointType;

    /**
     * @return coonnection_point_id
     */
    public Integer getCoonnectionPointId() {
        return coonnectionPointId;
    }

    /**
     * @param coonnectionPointId
     */
    public void setCoonnectionPointId(Integer coonnectionPointId) {
        this.coonnectionPointId = coonnectionPointId;
    }

    /**
     * @return connection_point_code
     */
    public String getConnectionPointCode() {
        return connectionPointCode;
    }

    /**
     * @param connectionPointCode
     */
    public void setConnectionPointCode(String connectionPointCode) {
        this.connectionPointCode = connectionPointCode == null ? null : connectionPointCode.trim();
    }

    /**
     * @return elevator_code
     */
    public Integer getElevatorCode() {
        return elevatorCode;
    }

    /**
     * @param elevatorCode
     */
    public void setElevatorCode(Integer elevatorCode) {
        this.elevatorCode = elevatorCode;
    }

    /**
     * @return wb_code
     */
    public String getWbCode() {
        return wbCode;
    }

    /**
     * @param wbCode
     */
    public void setWbCode(String wbCode) {
        this.wbCode = wbCode == null ? null : wbCode.trim();
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return status_remark
     */
    public String getStatusRemark() {
        return statusRemark;
    }

    /**
     * @param statusRemark
     */
    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark == null ? null : statusRemark.trim();
    }

    /**
     * @return status_update_time
     */
    public Date getStatusUpdateTime() {
        return statusUpdateTime;
    }

    /**
     * @param statusUpdateTime
     */
    public void setStatusUpdateTime(Date statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
    }

    /**
     * @return bercode
     */
    public String getBercode() {
        return bercode;
    }

    /**
     * @param bercode
     */
    public void setBercode(String bercode) {
        this.bercode = bercode == null ? null : bercode.trim();
    }

    /**
     * @return map_code
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * @param mapCode
     */
    public void setMapCode(String mapCode) {
        this.mapCode = mapCode == null ? null : mapCode.trim();
    }

    /**
     * @return wait_bercode
     */
    public String getWaitBercode() {
        return waitBercode;
    }

    /**
     * @param waitBercode
     */
    public void setWaitBercode(String waitBercode) {
        this.waitBercode = waitBercode == null ? null : waitBercode.trim();
    }

    /**
     * @return connection_point_type
     */
    public String getConnectionPointType() {
        return connectionPointType;
    }

    /**
     * @param connectionPointType
     */
    public void setConnectionPointType(String connectionPointType) {
        this.connectionPointType = connectionPointType == null ? null : connectionPointType.trim();
    }
}
