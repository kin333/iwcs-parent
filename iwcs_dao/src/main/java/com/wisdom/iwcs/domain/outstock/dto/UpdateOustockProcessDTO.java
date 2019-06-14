package com.wisdom.iwcs.domain.outstock.dto;

import java.math.BigDecimal;

/**
 * 更新出库进度用
 *
 * @Author: cecilia.yang
 * @Date: 2019/3/1 14:42
 */
public class UpdateOustockProcessDTO {
    private String taskNo;

    private String binCode;

    private String stkCode;

    private BigDecimal outStorageNum;

    private String actualOutSns;

    @Override
    public String toString() {
        return "UpdateOustockProcessDTO{" +
                "taskNo='" + taskNo + '\'' +
                ", binCode='" + binCode + '\'' +
                ", stkCode='" + stkCode + '\'' +
                ", outStorageNum=" + outStorageNum +
                ", actualOutSns='" + actualOutSns + '\'' +
                '}';
    }

    public String getActualOutSns() {
        return actualOutSns;
    }

    public void setActualOutSns(String actualOutSns) {
        this.actualOutSns = actualOutSns;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getBinCode() {
        return binCode;
    }

    public void setBinCode(String binCode) {
        this.binCode = binCode;
    }

    public String getStkCode() {
        return stkCode;
    }

    public void setStkCode(String stkCode) {
        this.stkCode = stkCode;
    }

    public BigDecimal getOutStorageNum() {
        return outStorageNum;
    }

    public void setOutStorageNum(BigDecimal outStorageNum) {
        this.outStorageNum = outStorageNum;
    }
}
