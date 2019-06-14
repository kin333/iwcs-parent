package com.wisdom.iwcs.domain.task.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "agv_task_detail_outstock_process")
public class AgvTaskDetailOutstockProcessDTO {
    @Id
    private Integer id;

    /**
     * 工作台任务编号：自动生成唯一任务编号
     */
    @Column(name = "task_no")
    private String taskNo;

    /**
     * 参与出库的bincode
     */
    @Column(name = "bin_code")
    private String binCode;

    /**
     * 货架号,冗余
     */
    @Column(name = "pod_code")
    private String podCode;

    /**
     * 库存唯一编号
     */
    @Column(name = "stk_code")
    private String stkCode;

    /**
     * 应出数量
     */
    @Column(name = "cal_outstock_qty")
    private BigDecimal calOutstockQty;

    /**
     * 实际出库数量
     */
    @Column(name = "actual_outstock_qty")
    private BigDecimal actualOutstockQty;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 存在指定SN呼叫时，该层bincode应拿SN
     */
    @Column(name = "cal_outstock_sns")
    private String calOutstockSns;

    /**
     * 存在指定SN呼叫时，缺失的SN信息
     */
    @Column(name = "actual_outstock_sns")
    private String actualOutstockSns;

    /**
     * 是否指定SN出库，0为否，1为是
     */
    @Column(name = "pre_sn_flag")
    private String preSnFlag;

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
     * 获取工作台任务编号：自动生成唯一任务编号
     *
     * @return task_no - 工作台任务编号：自动生成唯一任务编号
     */
    public String getTaskNo() {
        return taskNo;
    }

    /**
     * 设置工作台任务编号：自动生成唯一任务编号
     *
     * @param taskNo 工作台任务编号：自动生成唯一任务编号
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo == null ? null : taskNo.trim();
    }

    /**
     * 获取参与出库的bincode
     *
     * @return bin_code - 参与出库的bincode
     */
    public String getBinCode() {
        return binCode;
    }

    /**
     * 设置参与出库的bincode
     *
     * @param binCode 参与出库的bincode
     */
    public void setBinCode(String binCode) {
        this.binCode = binCode == null ? null : binCode.trim();
    }

    /**
     * 获取货架号,冗余
     *
     * @return pod_code - 货架号,冗余
     */
    public String getPodCode() {
        return podCode;
    }

    /**
     * 设置货架号,冗余
     *
     * @param podCode 货架号,冗余
     */
    public void setPodCode(String podCode) {
        this.podCode = podCode == null ? null : podCode.trim();
    }

    /**
     * 获取库存唯一编号
     *
     * @return stk_code - 库存唯一编号
     */
    public String getStkCode() {
        return stkCode;
    }

    /**
     * 设置库存唯一编号
     *
     * @param stkCode 库存唯一编号
     */
    public void setStkCode(String stkCode) {
        this.stkCode = stkCode == null ? null : stkCode.trim();
    }

    /**
     * 获取应出数量
     *
     * @return cal_outstock_qty - 应出数量
     */
    public BigDecimal getCalOutstockQty() {
        return calOutstockQty;
    }

    /**
     * 设置应出数量
     *
     * @param calOutstockQty 应出数量
     */
    public void setCalOutstockQty(BigDecimal calOutstockQty) {
        this.calOutstockQty = calOutstockQty;
    }

    /**
     * 获取实际出库数量
     *
     * @return actual_outstock_qty - 实际出库数量
     */
    public BigDecimal getActualOutstockQty() {
        return actualOutstockQty;
    }

    /**
     * 设置实际出库数量
     *
     * @param actualOutstockQty 实际出库数量
     */
    public void setActualOutstockQty(BigDecimal actualOutstockQty) {
        this.actualOutstockQty = actualOutstockQty;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return last_modified_time - 更新时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置更新时间
     *
     * @param lastModifiedTime 更新时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * 获取存在指定SN呼叫时，该层bincode应拿SN
     *
     * @return cal_outstock_sns - 存在指定SN呼叫时，该层bincode应拿SN
     */
    public String getCalOutstockSns() {
        return calOutstockSns;
    }

    /**
     * 设置存在指定SN呼叫时，该层bincode应拿SN
     *
     * @param calOutstockSns 存在指定SN呼叫时，该层bincode应拿SN
     */
    public void setCalOutstockSns(String calOutstockSns) {
        this.calOutstockSns = calOutstockSns == null ? null : calOutstockSns.trim();
    }

    /**
     * 获取存在指定SN呼叫时，缺失的SN信息
     *
     * @return actual_outstock_sns - 存在指定SN呼叫时，缺失的SN信息
     */
    public String getActualOutstockSns() {
        return actualOutstockSns;
    }

    /**
     * 设置存在指定SN呼叫时，缺失的SN信息
     *
     * @param actualOutstockSns 存在指定SN呼叫时，缺失的SN信息
     */
    public void setActualOutstockSns(String actualOutstockSns) {
        this.actualOutstockSns = actualOutstockSns == null ? null : actualOutstockSns.trim();
    }

    /**
     * 获取是否指定SN出库，0为否，1为是
     *
     * @return pre_sn_flag - 是否指定SN出库，0为否，1为是
     */
    public String getPreSnFlag() {
        return preSnFlag;
    }

    /**
     * 设置是否指定SN出库，0为否，1为是
     *
     * @param preSnFlag 是否指定SN出库，0为否，1为是
     */
    public void setPreSnFlag(String preSnFlag) {
        this.preSnFlag = preSnFlag == null ? null : preSnFlag.trim();
    }
}