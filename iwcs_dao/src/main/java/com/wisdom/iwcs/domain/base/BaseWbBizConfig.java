package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_wb_biz_config")
public class BaseWbBizConfig {
    @Id
    private Integer id;

    /**
     * 工作台编码
     */
    @Column(name = "wb_code")
    private String wbCode;

    /**
     * 业务类型：出库＼入库＼盘点
     */
    @Column(name = "biz_type")
    private String bizType;

    /**
     * 业务最大呼叫数
     */
    @Column(name = "biz_batch_max_num")
    private String bizBatchMaxNum;

    /**
     * 到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     */
    @Column(name = "agv_lift_status")
    private String agvLiftStatus;

    /**
     * 默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     */
    @Column(name = "agv_return_pod_type")
    private String agvReturnPodType;

    /**
     * 备注
     */
    private String remark;

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
     * 最后修改人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * AGV旋转属性，支持单个的设置行为
     */
    @Column(name = "agv_action_rotate_type")
    private String agvActionRotateType;

    /**
     * 呼叫默认数量
     */
    @Column(name = "biz_default_num")
    private Integer bizDefaultNum;

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
     * 获取工作台编码
     *
     * @return wb_code - 工作台编码
     */
    public String getWbCode() {
        return wbCode;
    }

    /**
     * 设置工作台编码
     *
     * @param wbCode 工作台编码
     */
    public void setWbCode(String wbCode) {
        this.wbCode = wbCode == null ? null : wbCode.trim();
    }

    /**
     * 获取业务类型：出库＼入库＼盘点
     *
     * @return biz_type - 业务类型：出库＼入库＼盘点
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * 设置业务类型：出库＼入库＼盘点
     *
     * @param bizType 业务类型：出库＼入库＼盘点
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    /**
     * 获取业务最大呼叫数
     *
     * @return biz_batch_max_num - 业务最大呼叫数
     */
    public String getBizBatchMaxNum() {
        return bizBatchMaxNum;
    }

    /**
     * 设置业务最大呼叫数
     *
     * @param bizBatchMaxNum 业务最大呼叫数
     */
    public void setBizBatchMaxNum(String bizBatchMaxNum) {
        this.bizBatchMaxNum = bizBatchMaxNum == null ? null : bizBatchMaxNum.trim();
    }

    /**
     * 获取到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     *
     * @return agv_lift_status - 到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     */
    public String getAgvLiftStatus() {
        return agvLiftStatus;
    }

    /**
     * 设置到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     *
     * @param agvLiftStatus 到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     */
    public void setAgvLiftStatus(String agvLiftStatus) {
        this.agvLiftStatus = agvLiftStatus == null ? null : agvLiftStatus.trim();
    }

    /**
     * 获取默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     *
     * @return agv_return_pod_type - 默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     */
    public String getAgvReturnPodType() {
        return agvReturnPodType;
    }

    /**
     * 设置默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     *
     * @param agvReturnPodType 默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     */
    public void setAgvReturnPodType(String agvReturnPodType) {
        this.agvReturnPodType = agvReturnPodType == null ? null : agvReturnPodType.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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
     * 获取最后修改人
     *
     * @return last_modified_by - 最后修改人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置最后修改人
     *
     * @param lastModifiedBy 最后修改人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * 获取最后修改时间
     *
     * @return last_modified_time - 最后修改时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastModifiedTime 最后修改时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getAgvActionRotateType() {
        return agvActionRotateType;
    }

    public void setAgvActionRotateType(String agvActionRotateType) {
        this.agvActionRotateType = agvActionRotateType;
    }

    public Integer getBizDefaultNum() {
        return bizDefaultNum;
    }

    public void setBizDefaultNum(Integer bizDefaultNum) {
        this.bizDefaultNum = bizDefaultNum;
    }
}