package com.wisdom.iwcs.domain.inv.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description
 * @Author panqingzun
 * @Date 2019-03-20
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "inv_task_cond_detail")
public class InvTaskCondDetailDTO implements Serializable {

    private static final long serialVersionUID = 6645115519029615568L;

    /**
     * 盘点任务唯一编号
     */
    @Column(name = "inv_num")
    private String invNum;

    /**
     * 货主
     */
    private String cargoOwner;

    /**
     * 物料号
     */
    private String matCode;

    /**
     * 仓位号
     */
    private String bincode;
    /**
     * 特征值1
     */
    private String stkCharacter1;

    /**
     * 特征值2
     */
    private String stkCharacter2;

    /**
     * 特征值3
     */
    private String stkCharacter3;

    /**
     * 特征值4
     */
    private String stkCharacter4;

    /**
     * 特征值5
     */
    private String stkCharacter5;

    /**
     * 库区
     */
    private String stgAreaCode;

    /**
     * 批次号
     */
    private String batchNum;

    /**
     * 容器编号
     */
    private String containerCode;

    /**
     * 规格编号
     */
    private String specCode;

    /**
     * 业务单号，库存
     */
    private String stkOrderNo;

    /**
     * 子单编号
     */
    private String stkSubOrderNo;

    /**
     * 变化天数
     */
    private Integer days;

    public String getInvNum() {
        return invNum;
    }

    public void setInvNum(String invNum) {
        this.invNum = invNum;
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

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
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

    public String getStgAreaCode() {
        return stgAreaCode;
    }

    public void setStgAreaCode(String stgAreaCode) {
        this.stgAreaCode = stgAreaCode;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getStkOrderNo() {
        return stkOrderNo;
    }

    public void setStkOrderNo(String stkOrderNo) {
        this.stkOrderNo = stkOrderNo;
    }

    public String getStkSubOrderNo() {
        return stkSubOrderNo;
    }

    public void setStkSubOrderNo(String stkSubOrderNo) {
        this.stkSubOrderNo = stkSubOrderNo;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
