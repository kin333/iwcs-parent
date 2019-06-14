package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_pod_bincode")
public class BasePodBincodeDTO {
    @Id
    private Integer id;

    /**
     * 仓位编号
     */
    private String bincode;

    /**
     * 货架编号
     */
    @Column(name = "pod_code")
    private String podCode;

    /**
     * 仓位类型code
     */
    @Column(name = "bin_type_code")
    private String binTypeCode;

    /**
     * 方向，冗余，方便查询
     */
    private Integer direction;

    /**
     * 层数，冗余，方便查询
     */
    private Integer layer;

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
     * 获取仓位编号
     *
     * @return bincode - 仓位编号
     */
    public String getBincode() {
        return bincode;
    }

    /**
     * 设置仓位编号
     *
     * @param bincode 仓位编号
     */
    public void setBincode(String bincode) {
        this.bincode = bincode == null ? null : bincode.trim();
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
     * 获取仓位类型code
     *
     * @return bin_type_code - 仓位类型code
     */
    public String getBinTypeCode() {
        return binTypeCode;
    }

    /**
     * 设置仓位类型code
     *
     * @param binTypeCode 仓位类型code
     */
    public void setBinTypeCode(String binTypeCode) {
        this.binTypeCode = binTypeCode == null ? null : binTypeCode.trim();
    }

    /**
     * 获取方向，冗余，方便查询
     *
     * @return direction - 方向，冗余，方便查询
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     * 设置方向，冗余，方便查询
     *
     * @param direction 方向，冗余，方便查询
     */
    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    /**
     * 获取层数，冗余，方便查询
     *
     * @return layer - 层数，冗余，方便查询
     */
    public Integer getLayer() {
        return layer;
    }

    /**
     * 设置层数，冗余，方便查询
     *
     * @param layer 层数，冗余，方便查询
     */
    public void setLayer(Integer layer) {
        this.layer = layer;
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