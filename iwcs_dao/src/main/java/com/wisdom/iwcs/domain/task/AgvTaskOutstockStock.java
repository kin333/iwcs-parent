package com.wisdom.iwcs.domain.task;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "agv_task_outstock_stock")
public class AgvTaskOutstockStock {
    @Id
    private Integer id;

    /**
     * 工作台任务编号：自动生成唯一任务编号
     */
    @Column(name = "task_no")
    private String taskNo;

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
     * 库区编号
     */
    @Column(name = "stg_area_code")
    private String stgAreaCode;

    /**
     * 批次号
     */
    @Column(name = "batch_num")
    private String batchNum;

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
     * 总需数量
     */
    @Column(name = "total_need_qty")
    private BigDecimal totalNeedQty;

    /**
     * 已呼叫数量
     */
    @Column(name = "already_call_qty")
    private BigDecimal alreadyCallQty;

    /**
     * 缺失数量
     */
    @Column(name = "missing_qty")
    private BigDecimal missingQty;

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
     * 存在指定SN呼叫时，已呼叫的SN信息
     */
    @Column(name = "already_call_sns")
    private String alreadyCallSns;

    /**
     * 存在指定SN呼叫时，缺失的SN信息
     */
    @Column(name = "missing_sns")
    private String missingSns;

    /**
     * 是否指定SN出库，0为否，1为是
     */
    @Column(name = "pre_sn_flag")
    private String preSnFlag;

    /**
     * 规格编号
     */
    @Column(name = "spec_code")
    private String specCode;

    /**
     * 容器编号
     */
    @Column(name = "container_code")
    private String containerCode;

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
     * 获取货主
     *
     * @return cargo_owner - 货主
     */
    public String getCargoOwner() {
        return cargoOwner;
    }

    /**
     * 设置货主
     *
     * @param cargoOwner 货主
     */
    public void setCargoOwner(String cargoOwner) {
        this.cargoOwner = cargoOwner == null ? null : cargoOwner.trim();
    }

    /**
     * 获取物料号
     *
     * @return mat_code - 物料号
     */
    public String getMatCode() {
        return matCode;
    }

    /**
     * 设置物料号
     *
     * @param matCode 物料号
     */
    public void setMatCode(String matCode) {
        this.matCode = matCode == null ? null : matCode.trim();
    }

    /**
     * 获取库区编号
     *
     * @return stg_area_code - 库区编号
     */
    public String getStgAreaCode() {
        return stgAreaCode;
    }

    /**
     * 设置库区编号
     *
     * @param stgAreaCode 库区编号
     */
    public void setStgAreaCode(String stgAreaCode) {
        this.stgAreaCode = stgAreaCode == null ? null : stgAreaCode.trim();
    }

    /**
     * 获取批次号
     *
     * @return batch_num - 批次号
     */
    public String getBatchNum() {
        return batchNum;
    }

    /**
     * 设置批次号
     *
     * @param batchNum 批次号
     */
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    /**
     * 获取特征值1
     *
     * @return stk_character1 - 特征值1
     */
    public String getStkCharacter1() {
        return stkCharacter1;
    }

    /**
     * 设置特征值1
     *
     * @param stkCharacter1 特征值1
     */
    public void setStkCharacter1(String stkCharacter1) {
        this.stkCharacter1 = stkCharacter1 == null ? null : stkCharacter1.trim();
    }

    /**
     * 获取特征值2
     *
     * @return stk_character2 - 特征值2
     */
    public String getStkCharacter2() {
        return stkCharacter2;
    }

    /**
     * 设置特征值2
     *
     * @param stkCharacter2 特征值2
     */
    public void setStkCharacter2(String stkCharacter2) {
        this.stkCharacter2 = stkCharacter2 == null ? null : stkCharacter2.trim();
    }

    /**
     * 获取特征值3
     *
     * @return stk_character3 - 特征值3
     */
    public String getStkCharacter3() {
        return stkCharacter3;
    }

    /**
     * 设置特征值3
     *
     * @param stkCharacter3 特征值3
     */
    public void setStkCharacter3(String stkCharacter3) {
        this.stkCharacter3 = stkCharacter3 == null ? null : stkCharacter3.trim();
    }

    /**
     * 获取特征值4
     *
     * @return stk_character4 - 特征值4
     */
    public String getStkCharacter4() {
        return stkCharacter4;
    }

    /**
     * 设置特征值4
     *
     * @param stkCharacter4 特征值4
     */
    public void setStkCharacter4(String stkCharacter4) {
        this.stkCharacter4 = stkCharacter4 == null ? null : stkCharacter4.trim();
    }

    /**
     * 获取特征值5
     *
     * @return stk_character5 - 特征值5
     */
    public String getStkCharacter5() {
        return stkCharacter5;
    }

    /**
     * 设置特征值5
     *
     * @param stkCharacter5 特征值5
     */
    public void setStkCharacter5(String stkCharacter5) {
        this.stkCharacter5 = stkCharacter5 == null ? null : stkCharacter5.trim();
    }

    /**
     * 获取总需数量
     *
     * @return total_need_qty - 总需数量
     */
    public BigDecimal getTotalNeedQty() {
        return totalNeedQty;
    }

    /**
     * 设置总需数量
     *
     * @param totalNeedQty 总需数量
     */
    public void setTotalNeedQty(BigDecimal totalNeedQty) {
        this.totalNeedQty = totalNeedQty;
    }

    /**
     * 获取已呼叫数量
     *
     * @return already_call_qty - 已呼叫数量
     */
    public BigDecimal getAlreadyCallQty() {
        return alreadyCallQty;
    }

    /**
     * 设置已呼叫数量
     *
     * @param alreadyCallQty 已呼叫数量
     */
    public void setAlreadyCallQty(BigDecimal alreadyCallQty) {
        this.alreadyCallQty = alreadyCallQty;
    }

    /**
     * 获取缺失数量
     *
     * @return missing_qty - 缺失数量
     */
    public BigDecimal getMissingQty() {
        return missingQty;
    }

    /**
     * 设置缺失数量
     *
     * @param missingQty 缺失数量
     */
    public void setMissingQty(BigDecimal missingQty) {
        this.missingQty = missingQty;
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
     * 获取存在指定SN呼叫时，已呼叫的SN信息
     *
     * @return already_call_sns - 存在指定SN呼叫时，已呼叫的SN信息
     */
    public String getAlreadyCallSns() {
        return alreadyCallSns;
    }

    /**
     * 设置存在指定SN呼叫时，已呼叫的SN信息
     *
     * @param alreadyCallSns 存在指定SN呼叫时，已呼叫的SN信息
     */
    public void setAlreadyCallSns(String alreadyCallSns) {
        this.alreadyCallSns = alreadyCallSns == null ? null : alreadyCallSns.trim();
    }

    /**
     * 获取存在指定SN呼叫时，缺失的SN信息
     *
     * @return missing_sns - 存在指定SN呼叫时，缺失的SN信息
     */
    public String getMissingSns() {
        return missingSns;
    }

    /**
     * 设置存在指定SN呼叫时，缺失的SN信息
     *
     * @param missingSns 存在指定SN呼叫时，缺失的SN信息
     */
    public void setMissingSns(String missingSns) {
        this.missingSns = missingSns == null ? null : missingSns.trim();
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

    /**
     * 获取规格编号
     *
     * @return spec_code - 规格编号
     */
    public String getSpecCode() {
        return specCode;
    }

    /**
     * 设置规格编号
     *
     * @param specCode 规格编号
     */
    public void setSpecCode(String specCode) {
        this.specCode = specCode == null ? null : specCode.trim();
    }

    /**
     * 获取容器编号
     *
     * @return container_code - 容器编号
     */
    public String getContainerCode() {
        return containerCode;
    }

    /**
     * 设置容器编号
     *
     * @param containerCode 容器编号
     */
    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode == null ? null : containerCode.trim();
    }
}