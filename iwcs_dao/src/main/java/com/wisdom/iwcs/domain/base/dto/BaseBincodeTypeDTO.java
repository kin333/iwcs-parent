package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "base_bincode_type")
public class BaseBincodeTypeDTO {
    @Id
    private Integer id;

    /**
     * 仓库编码
     */
    @Column(name = "wh_code")
    private String whCode;

    /**
     * 仓位类型编码
     */
    @Column(name = "bin_type_code")
    private String binTypeCode;

    /**
     * 仓位类型名称
     */
    @Column(name = "bin_type_name")
    private String binTypeName;

    /**
     * 深
     */
    private BigDecimal depth;

    /**
     * 宽
     */
    private BigDecimal width;

    /**
     * 高
     */
    private BigDecimal height;

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
     * 获取仓位类型编码
     *
     * @return bin_type_code - 仓位类型编码
     */
    public String getBinTypeCode() {
        return binTypeCode;
    }

    /**
     * 设置仓位类型编码
     *
     * @param binTypeCode 仓位类型编码
     */
    public void setBinTypeCode(String binTypeCode) {
        this.binTypeCode = binTypeCode == null ? null : binTypeCode.trim();
    }

    /**
     * 获取仓位类型名称
     *
     * @return bin_type_name - 仓位类型名称
     */
    public String getBinTypeName() {
        return binTypeName;
    }

    /**
     * 设置仓位类型名称
     *
     * @param binTypeName 仓位类型名称
     */
    public void setBinTypeName(String binTypeName) {
        this.binTypeName = binTypeName == null ? null : binTypeName.trim();
    }

    /**
     * 获取深
     *
     * @return depth - 深
     */
    public BigDecimal getDepth() {
        return depth;
    }

    /**
     * 设置深
     *
     * @param depth 深
     */
    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    /**
     * 获取宽
     *
     * @return width - 宽
     */
    public BigDecimal getWidth() {
        return width;
    }

    /**
     * 设置宽
     *
     * @param width 宽
     */
    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    /**
     * 获取高
     *
     * @return height - 高
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * 设置高
     *
     * @param height 高
     */
    public void setHeight(BigDecimal height) {
        this.height = height;
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