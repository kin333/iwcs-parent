package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "base_ctnr_type")
public class BaseCtnrType {
    @Id
    private Long id;

    /**
     * 容器类型编号
     */
    @Column(name = "ctnr_typ_code")
    private String ctnrTypCode;

    /**
     * 容器类型名称
     */
    @Column(name = "ctnr_typ_text")
    private String ctnrTypText;

    /**
     * 容器深度
     */
    private String length;

    /**
     * 容器宽度
     */
    private String width;

    /**
     * 容器高度
     */
    private String height;

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
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取容器类型编号
     *
     * @return ctnr_typ_code - 容器类型编号
     */
    public String getCtnrTypCode() {
        return ctnrTypCode;
    }

    /**
     * 设置容器类型编号
     *
     * @param ctnrTypCode 容器类型编号
     */
    public void setCtnrTypCode(String ctnrTypCode) {
        this.ctnrTypCode = ctnrTypCode == null ? null : ctnrTypCode.trim();
    }

    /**
     * 获取容器类型名称
     *
     * @return ctnr_typ_text - 容器类型名称
     */
    public String getCtnrTypText() {
        return ctnrTypText;
    }

    /**
     * 设置容器类型名称
     *
     * @param ctnrTypText 容器类型名称
     */
    public void setCtnrTypText(String ctnrTypText) {
        this.ctnrTypText = ctnrTypText == null ? null : ctnrTypText.trim();
    }

    /**
     * 获取容器深度
     *
     * @return length - 容器深度
     */
    public String getLength() {
        return length;
    }

    /**
     * 设置容器深度
     *
     * @param length 容器深度
     */
    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
    }

    /**
     * 获取容器宽度
     *
     * @return width - 容器宽度
     */
    public String getWidth() {
        return width;
    }

    /**
     * 设置容器宽度
     *
     * @param width 容器宽度
     */
    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    /**
     * 获取容器高度
     *
     * @return height - 容器高度
     */
    public String getHeight() {
        return height;
    }

    /**
     * 设置容器高度
     *
     * @param height 容器高度
     */
    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
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