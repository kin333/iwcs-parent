package com.wisdom.iwcs.domain.inv;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author panqingzun
 * @Date 2019-03-20
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "inv_task_bincode_process")
public class InvTaskBincodeProcess implements Serializable {

    private static final long serialVersionUID = 5869795557273517502L;

    @Id
    private Integer id;

    /**
     * 上游盘点号
     */
    @Column(name = "src_inv_no")
    private String srcInvNo;

    /**
     * 任务bincode
     */
    @Column(name = "task_bincode")
    private String taskBincode;

    /**
     * 货架编号
     */
    @Column(name = "podcode")
    private String podcode;

    /**
     * 盘点状态，0未盘点，1已盘点
     */
    @Column(name = "inv_status")
    private Integer invStatus;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 盘点上报结果时间
     */
    @Column(name = "inv_reporting_result_time")
    private Date invReportingResultTime;

    /**
     * 盘点货架是否到达工作台，0是，1否
     */
    @Column(name = "if_out")
    private Integer ifOut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrcInvNo() {
        return srcInvNo;
    }

    public void setSrcInvNo(String srcInvNo) {
        this.srcInvNo = srcInvNo;
    }

    public String getTaskBincode() {
        return taskBincode;
    }

    public void setTaskBincode(String taskBincode) {
        this.taskBincode = taskBincode;
    }

    public String getPodcode() {
        return podcode;
    }

    public void setPodcode(String podcode) {
        this.podcode = podcode;
    }

    public Integer getInvStatus() {
        return invStatus;
    }

    public void setInvStatus(Integer invStatus) {
        this.invStatus = invStatus;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getInvReportingResultTime() {
        return invReportingResultTime;
    }

    public void setInvReportingResultTime(Date invReportingResultTime) {
        this.invReportingResultTime = invReportingResultTime;
    }

    public Integer getIfOut() {
        return ifOut;
    }

    public void setIfOut(Integer ifOut) {
        this.ifOut = ifOut;
    }
}
