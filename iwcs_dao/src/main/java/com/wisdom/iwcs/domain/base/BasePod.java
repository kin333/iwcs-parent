package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_pod")
public class BasePod {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 仓库编码
     */
    @Column(name = "wh_code")
    private String whCode;

    /**
     * 货架编号
     */
    @Column(name = "pod_code")
    private String podCode;

    /**
     * 货架名称
     */
    @Column(name = "pod_name")
    private String podName;

    /**
     * 货架类型编码
     */
    @Column(name = "pod_type_code")
    private String podTypeCode;

    /**
     * bincode数量
     */
    @Column(name = "bin_cnt")
    private Integer binCnt;

    /**
     * 有效标记，0有效，1无效
     */
    @Column(name = "valid_flag")
    private Integer validFlag;

    /**
     * 删除标记，0未删除，1删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

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
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取仓库编码
     *
     * @return wh_code - 仓库编码
     */
    public String getWhCode() {
        return whCode;
    }

    /**
     * 设置仓库编码
     *
     * @param whCode 仓库编码
     */
    public void setWhCode(String whCode) {
        this.whCode = whCode == null ? null : whCode.trim();
    }

    /**
     * 获取货架编号
     *
     * @return pod_code - 货架编号
     */
    public String getPodCode() {
        return podCode;
    }

    /**
     * 设置货架编号
     *
     * @param podCode 货架编号
     */
    public void setPodCode(String podCode) {
        this.podCode = podCode == null ? null : podCode.trim();
    }

    /**
     * 获取货架名称
     *
     * @return pod_name - 货架名称
     */
    public String getPodName() {
        return podName;
    }

    /**
     * 设置货架名称
     *
     * @param podName 货架名称
     */
    public void setPodName(String podName) {
        this.podName = podName == null ? null : podName.trim();
    }

    /**
     * 获取货架类型编码
     *
     * @return pod_type_code - 货架类型编码
     */
    public String getPodTypeCode() {
        return podTypeCode;
    }

    /**
     * 设置货架类型编码
     *
     * @param podTypeCode 货架类型编码
     */
    public void setPodTypeCode(String podTypeCode) {
        this.podTypeCode = podTypeCode == null ? null : podTypeCode.trim();
    }

    /**
     * 获取bincode数量
     *
     * @return bin_cnt - bincode数量
     */
    public Integer getBinCnt() {
        return binCnt;
    }

    /**
     * 设置bincode数量
     *
     * @param binCnt bincode数量
     */
    public void setBinCnt(Integer binCnt) {
        this.binCnt = binCnt;
    }

    /**
     * 获取有效标记，0有效，1无效
     *
     * @return valid_flag - 有效标记，0有效，1无效
     */
    public Integer getValidFlag() {
        return validFlag;
    }

    /**
     * 设置有效标记，0有效，1无效
     *
     * @param validFlag 有效标记，0有效，1无效
     */
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    /**
     * 获取删除标记，0未删除，1删除
     *
     * @return delete_flag - 删除标记，0未删除，1删除
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标记，0未删除，1删除
     *
     * @param deleteFlag 删除标记，0未删除，1删除
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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
}