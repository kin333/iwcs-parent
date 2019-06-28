package com.wisdom.iwcs.domain.elevator;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ele_cross_floor_task_status_log")
public class CrossFloorTaskStatusLog {
    @Id
    private Integer id;

    @Column(name = "cross_taskref")
    private String crossTaskref;

    private String podcode;

    private String pid;

    @Column(name = "created_time")
    private Date createdTime;

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
     * @return cross_taskref
     */
    public String getCrossTaskref() {
        return crossTaskref;
    }

    /**
     * @param crossTaskref
     */
    public void setCrossTaskref(String crossTaskref) {
        this.crossTaskref = crossTaskref == null ? null : crossTaskref.trim();
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
}
