package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_pod_type")
public class BasePodTypeDTO {
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
     * 货架类型编码
     */
    @Column(name = "pod_type_code")
    private String podTypeCode;

    /**
     * 货架类型名称
     */
    @Column(name = "pod_type_name")
    private String podTypeName;

    /**
     * 货架类型共几层
     */
    @Column(name = "total_layer")
    private Integer totalLayer;

    /**
     * 货架重量
     */
    private Long weight;

    /**
     * 长
     */
    private Long length;

    /**
     * 宽
     */
    private Long width;

    /**
     * 高
     */
    private Long height;

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
     * 默认回库策略
     */
    @Column(name = "default_return_strategy")
    private String defaultReturnStrategy;

    public String getDefaultReturnStrategy() {
        return defaultReturnStrategy;
    }

    public void setDefaultReturnStrategy(String defaultReturnStrategy) {
        this.defaultReturnStrategy = defaultReturnStrategy;
    }

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
     * 获取货架类型名称
     *
     * @return pod_type_name - 货架类型名称
     */
    public String getPodTypeName() {
        return podTypeName;
    }

    /**
     * 设置货架类型名称
     *
     * @param podTypeName 货架类型名称
     */
    public void setPodTypeName(String podTypeName) {
        this.podTypeName = podTypeName == null ? null : podTypeName.trim();
    }

    /**
     * 获取货架类型共几层
     *
     * @return total_layer - 货架类型共几层
     */
    public Integer getTotalLayer() {
        return totalLayer;
    }

    /**
     * 设置货架类型共几层
     *
     * @param totalLayer 货架类型共几层
     */
    public void setTotalLayer(Integer totalLayer) {
        this.totalLayer = totalLayer;
    }

    /**
     * 获取货架重量
     *
     * @return weight - 货架重量
     */
    public Long getWeight() {
        return weight;
    }

    /**
     * 设置货架重量
     *
     * @param weight 货架重量
     */
    public void setWeight(Long weight) {
        this.weight = weight;
    }

    /**
     * 获取长
     *
     * @return length - 长
     */
    public Long getLength() {
        return length;
    }

    /**
     * 设置长
     *
     * @param length 长
     */
    public void setLength(Long length) {
        this.length = length;
    }

    /**
     * 获取宽
     *
     * @return width - 宽
     */
    public Long getWidth() {
        return width;
    }

    /**
     * 设置宽
     *
     * @param width 宽
     */
    public void setWidth(Long width) {
        this.width = width;
    }

    /**
     * 获取高
     *
     * @return height - 高
     */
    public Long getHeight() {
        return height;
    }

    /**
     * 设置高
     *
     * @param height 高
     */
    public void setHeight(Long height) {
        this.height = height;
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