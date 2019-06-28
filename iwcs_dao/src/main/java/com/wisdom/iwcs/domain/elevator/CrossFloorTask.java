package com.wisdom.iwcs.domain.elevator;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ele_cross_floor_task")
public class CrossFloorTask {

    @Id
    private Integer id;

    private String podcode;

    private String pid;

    @Column(name = "source_wb_task_code")
    private String sourceWbTaskCode;

    @Column(name = "pod_type")
    private String podType;

    @Column(name = "elevator_work_type")
    private String elevatorWorkType;

    /**
     * 货架所在楼层
     */
    @Column(name = "pod_floor")
    private String podFloor;

    /**
     * 源楼层
     */
    @Column(name = "source_floor")
    private String sourceFloor;

    /**
     * 目标楼层
     */
    @Column(name = "dest_floor")
    private String destFloor;

    private Integer priority;

    @Column(name = "elevator_code")
    private String elevatorCode;

    @Column(name = "source_connection_point")
    private String sourceConnectionPoint;

    @Column(name = "suggest_dest_connection_point")
    private String suggestDestConnectionPoint;

    @Column(name = "dest_connection_point")
    private String destConnectionPoint;

    @Column(name = "source_wb_code")
    private String sourceWbCode;

    @Column(name = "dest_area_type")
    private String destAreaType;

    @Column(name = "dest_storage")
    private String destStorage;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "cross_taskref")
    private byte[] crossTaskref;

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
     * @return podcode
     */
    public String getPodcode() {
        return podcode;
    }

    /**
     * @param podcode
     */
    public void setPodcode(String podcode) {
        this.podcode = podcode == null ? null : podcode.trim();
    }

    /**
     * @return pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * @return source_wb_task_code
     */
    public String getSourceWbTaskCode() {
        return sourceWbTaskCode;
    }

    /**
     * @param sourceWbTaskCode
     */
    public void setSourceWbTaskCode(String sourceWbTaskCode) {
        this.sourceWbTaskCode = sourceWbTaskCode == null ? null : sourceWbTaskCode.trim();
    }

    /**
     * @return pod_type
     */
    public String getPodType() {
        return podType;
    }

    /**
     * @param podType
     */
    public void setPodType(String podType) {
        this.podType = podType == null ? null : podType.trim();
    }

    /**
     * @return elevator_work_type
     */
    public String getElevatorWorkType() {
        return elevatorWorkType;
    }

    /**
     * @param elevatorWorkType
     */
    public void setElevatorWorkType(String elevatorWorkType) {
        this.elevatorWorkType = elevatorWorkType == null ? null : elevatorWorkType.trim();
    }

    /**
     * 货架所在楼层
     *
     * @return pod_floor - 货架所在楼层
     */
    public String getPodFloor() {
        return podFloor;
    }

    /**
     * 货架所在楼层
     *
     * @param podFloor 货架所在楼层
     */
    public void setPodFloor(String podFloor) {
        this.podFloor = podFloor == null ? null : podFloor.trim();
    }

    /**
     * 源楼层
     *
     * @return source_floor - 源楼层
     */
    public String getSourceFloor() {
        return sourceFloor;
    }

    /**
     * 源楼层
     *
     * @param sourceFloor 源楼层
     */
    public void setSourceFloor(String sourceFloor) {
        this.sourceFloor = sourceFloor == null ? null : sourceFloor.trim();
    }

    /**
     * 目标楼层
     *
     * @return dest_floor - 目标楼层
     */
    public String getDestFloor() {
        return destFloor;
    }

    /**
     * 目标楼层
     *
     * @param destFloor 目标楼层
     */
    public void setDestFloor(String destFloor) {
        this.destFloor = destFloor == null ? null : destFloor.trim();
    }

    /**
     * @return priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return elevator_code
     */
    public String getElevatorCode() {
        return elevatorCode;
    }

    /**
     * @param elevatorCode
     */
    public void setElevatorCode(String elevatorCode) {
        this.elevatorCode = elevatorCode == null ? null : elevatorCode.trim();
    }

    /**
     * @return source_connection_point
     */
    public String getSourceConnectionPoint() {
        return sourceConnectionPoint;
    }

    /**
     * @param sourceConnectionPoint
     */
    public void setSourceConnectionPoint(String sourceConnectionPoint) {
        this.sourceConnectionPoint = sourceConnectionPoint == null ? null : sourceConnectionPoint.trim();
    }

    /**
     * @return suggest_dest_connection_point
     */
    public String getSuggestDestConnectionPoint() {
        return suggestDestConnectionPoint;
    }

    /**
     * @param suggestDestConnectionPoint
     */
    public void setSuggestDestConnectionPoint(String suggestDestConnectionPoint) {
        this.suggestDestConnectionPoint = suggestDestConnectionPoint == null ? null : suggestDestConnectionPoint.trim();
    }

    /**
     * @return dest_connection_point
     */
    public String getDestConnectionPoint() {
        return destConnectionPoint;
    }

    /**
     * @param destConnectionPoint
     */
    public void setDestConnectionPoint(String destConnectionPoint) {
        this.destConnectionPoint = destConnectionPoint == null ? null : destConnectionPoint.trim();
    }

    /**
     * @return source_wb_code
     */
    public String getSourceWbCode() {
        return sourceWbCode;
    }

    /**
     * @param sourceWbCode
     */
    public void setSourceWbCode(String sourceWbCode) {
        this.sourceWbCode = sourceWbCode == null ? null : sourceWbCode.trim();
    }

    /**
     * @return dest_area_type
     */
    public String getDestAreaType() {
        return destAreaType;
    }

    /**
     * @param destAreaType
     */
    public void setDestAreaType(String destAreaType) {
        this.destAreaType = destAreaType == null ? null : destAreaType.trim();
    }

    /**
     * @return dest_storage
     */
    public String getDestStorage() {
        return destStorage;
    }

    /**
     * @param destStorage
     */
    public void setDestStorage(String destStorage) {
        this.destStorage = destStorage == null ? null : destStorage.trim();
    }

    /**
     * @return created_time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return last_modified_time
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * @param lastModifiedTime
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * @return delete_flag
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @return cross_taskref
     */
    public byte[] getCrossTaskref() {
        return crossTaskref;
    }

    /**
     * @param crossTaskref
     */
    public void setCrossTaskref(byte[] crossTaskref) {
        this.crossTaskref = crossTaskref;
    }
}
