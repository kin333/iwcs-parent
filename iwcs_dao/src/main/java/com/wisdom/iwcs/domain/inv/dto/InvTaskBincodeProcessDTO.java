package com.wisdom.iwcs.domain.inv.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@ToString
@Entity
@Table(name = "inv_task_bincode_process")
public class InvTaskBincodeProcessDTO {
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
     * 盘点货架是否到达工作台，0是，1否
     */
    @Column(name = "if_out")
    private Integer ifOut;

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

    public Integer getIfOut() {
        return ifOut;
    }

    public void setIfOut(Integer ifOut) {
        this.ifOut = ifOut;
    }
}