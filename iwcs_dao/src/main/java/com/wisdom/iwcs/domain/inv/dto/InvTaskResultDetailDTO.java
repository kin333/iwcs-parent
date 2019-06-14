package com.wisdom.iwcs.domain.inv.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
@Entity
@Table(name = "inv_task_result_detail")
public class InvTaskResultDetailDTO {
    /**
     * 盘点盘点任务唯一标识
     */
    @Column(name = "src_inv_no")
    private String srcInvNo;

    /**
     * 业务单号，库存
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 子单编号
     */
    @Column(name = "sub_order_no")
    private String subOrderNo;

    /**
     * 货主
     */
    @Column(name = "cargo_owner")
    private String cargoOwner;

    /**
     * 物料号
     */
    @Column(name = "mat_code")
    private String matCode;

    /**
     * 特征值1
     */
    @Column(name = "stk_character1")
    private String stkCharacter1;

    /**
     * 特征值2
     */
    @Column(name = "stk_character2")
    private String stkCharacter2;

    /**
     * 特征值3
     */
    @Column(name = "stk_character3")
    private String stkCharacter3;

    /**
     * 特征值4
     */
    @Column(name = "stk_character4")
    private String stkCharacter4;

    /**
     * 特征值5
     */
    @Column(name = "stk_character5")
    private String stkCharacter5;

    /**
     * 是否按SN盘点 0-NO 1-YES
     */
    @Column(name = "per_sn")
    private Integer perSn;

    /**
     * 任务描述
     */
    @Column(name = "task_desc")
    private String taskDesc;

    /**
     * 盘点状态，0未盘点，1已盘点
     */
    @Column(name = "inv_status")
    private Integer invStatus;

    /**
     * 货架编号
     */
    @Column(name = "podcode")
    private String podcode;

    /**
     * 仓位编号
     */
    @Column(name = "bincode")
    private String bincode;

    /**
     * 库存唯一编号
     */
    @Column(name = "stk_code")
    private String stkCode;

    /**
     * 系统数量
     */
    @Column(name = "sys_qty")
    private BigDecimal sysQty;

    /**
     * 盘点数量
     */
    @Column(name = "inv_qty")
    private BigDecimal invQty;

    /**
     * 盘点上报时间
     */
    @Column(name = "inv_result_report_time")
    private Date invResultReportTime;

    private String wbCode;

    /**
     * 1为原地不动  0为返回原点
     * 直接 给初始值
     */
    private Integer agvNextAction = 0;

    public String getSrcInvNo() {
        return srcInvNo;
    }

    public void setSrcInvNo(String srcInvNo) {
        this.srcInvNo = srcInvNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public String getCargoOwner() {
        return cargoOwner;
    }

    public void setCargoOwner(String cargoOwner) {
        this.cargoOwner = cargoOwner;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getStkCharacter1() {
        return stkCharacter1;
    }

    public void setStkCharacter1(String stkCharacter1) {
        this.stkCharacter1 = stkCharacter1;
    }

    public String getStkCharacter2() {
        return stkCharacter2;
    }

    public void setStkCharacter2(String stkCharacter2) {
        this.stkCharacter2 = stkCharacter2;
    }

    public String getStkCharacter3() {
        return stkCharacter3;
    }

    public void setStkCharacter3(String stkCharacter3) {
        this.stkCharacter3 = stkCharacter3;
    }

    public String getStkCharacter4() {
        return stkCharacter4;
    }

    public void setStkCharacter4(String stkCharacter4) {
        this.stkCharacter4 = stkCharacter4;
    }

    public String getStkCharacter5() {
        return stkCharacter5;
    }

    public void setStkCharacter5(String stkCharacter5) {
        this.stkCharacter5 = stkCharacter5;
    }

    public Integer getPerSn() {
        return perSn;
    }

    public void setPerSn(Integer perSn) {
        this.perSn = perSn;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Integer getInvStatus() {
        return invStatus;
    }

    public void setInvStatus(Integer invStatus) {
        this.invStatus = invStatus;
    }

    public String getPodcode() {
        return podcode;
    }

    public void setPodcode(String podcode) {
        this.podcode = podcode;
    }

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public String getStkCode() {
        return stkCode;
    }

    public void setStkCode(String stkCode) {
        this.stkCode = stkCode;
    }

    public BigDecimal getSysQty() {
        return sysQty;
    }

    public void setSysQty(BigDecimal sysQty) {
        this.sysQty = sysQty;
    }

    public BigDecimal getInvQty() {
        return invQty;
    }

    public void setInvQty(BigDecimal invQty) {
        this.invQty = invQty;
    }

    public Date getInvResultReportTime() {
        return invResultReportTime;
    }

    public void setInvResultReportTime(Date invResultReportTime) {
        this.invResultReportTime = invResultReportTime;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public Integer getAgvNextAction() {
        return agvNextAction;
    }

    public void setAgvNextAction(Integer agvNextAction) {
        this.agvNextAction = agvNextAction;
    }
}