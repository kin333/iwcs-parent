package com.wisdom.iwcs.domain.inv.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "inv_task")
public class InvTaskDTO {
    /**
     * 上游盘点任务唯一编号
     */
    @Column(name = "src_inv_no")
    private String srcInvNo;

    /**
     * 物料最近操作天数
     */
    @Column(name = "days")
    private Integer days;

    /**
     * 盘点状态，0未盘点，1已盘点
     */
    @Column(name = "inv_status")
    private Integer invStatus;

    /**
     * 盘点任务唯一编号
     */
    @Column(name = "sys_inv_num")
    private String sysInvNum;

    /**
     * 任务描述
     */
    @Column(name = "task_desc")
    private String taskDesc;

    /**
     * 上游用户id（发起盘点任务的操作人
     */
    @Column(name = "src_user_code")
    private Integer srcUserCode;

    /**
     * 盘点类型 0：盘点库存 1：盘点库存和发生变动+天数days
     */
    @Column(name = "inv_type")
    private Integer invType;

    /**
     * 是否直接会叫小车 1 直接呼叫
     */
    private Integer automatedCallType;

    /**
     * 工作台
     */
    private String wbCode;

    private List<InvTaskCondDetailDTO> data;

    public String getSrcInvNo() {
        return srcInvNo;
    }

    public void setSrcInvNo(String srcInvNo) {
        this.srcInvNo = srcInvNo;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getInvStatus() {
        return invStatus;
    }

    public void setInvStatus(Integer invStatus) {
        this.invStatus = invStatus;
    }

    public String getSysInvNum() {
        return sysInvNum;
    }

    public void setSysInvNum(String sysInvNum) {
        this.sysInvNum = sysInvNum;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Integer getSrcUserCode() {
        return srcUserCode;
    }

    public void setSrcUserCode(Integer srcUserCode) {
        this.srcUserCode = srcUserCode;
    }

    public Integer getInvType() {
        return invType;
    }

    public void setInvType(Integer invType) {
        this.invType = invType;
    }

    public Integer getAutomatedCallType() {
        return automatedCallType;
    }

    public void setAutomatedCallType(Integer automatedCallType) {
        this.automatedCallType = automatedCallType;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public List<InvTaskCondDetailDTO> getData() {
        return data;
    }

    public void setData(List<InvTaskCondDetailDTO> data) {
        this.data = data;
    }
}