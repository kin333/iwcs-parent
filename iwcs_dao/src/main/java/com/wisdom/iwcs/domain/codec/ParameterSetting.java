package com.wisdom.iwcs.domain.codec;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "codec_parameter_setting")
public class ParameterSetting {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 分类属性
     */
    private String attribute;

    /**
     * integer 的主键
     */
    @Column(name = "integer_code")
    private Integer integerCode;

    /**
     * decimal 的主键
     */
    @Column(name = "decimal_code")
    private String decimalCode;

    /**
     * string 的主键
     */
    @Column(name = "string_code")
    private String stringCode;

    /**
     * 描述A
     */
    @Column(name = "describe_a")
    private String describeA;

    /**
     * 描述B
     */
    @Column(name = "describe_b")
    private String describeB;

    /**
     * 显示排序
     */
    @Column(name = "display_order")
    private Integer displayOrder;

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
     * 更新人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 更新时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 删除标识：1-未删除，0-已删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分类属性
     *
     * @return attribute - 分类属性
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * 设置分类属性
     *
     * @param attribute 分类属性
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute == null ? null : attribute.trim();
    }

    /**
     * 获取integer 的主键
     *
     * @return integer_code - integer 的主键
     */
    public Integer getIntegerCode() {
        return integerCode;
    }

    /**
     * 设置integer 的主键
     *
     * @param integerCode integer 的主键
     */
    public void setIntegerCode(Integer integerCode) {
        this.integerCode = integerCode;
    }

    /**
     * 获取decimal 的主键
     *
     * @return decimal_code - decimal 的主键
     */
    public String getDecimalCode() {
        return decimalCode;
    }

    /**
     * 设置decimal 的主键
     *
     * @param decimalCode decimal 的主键
     */
    public void setDecimalCode(String decimalCode) {
        this.decimalCode = decimalCode == null ? null : decimalCode.trim();
    }

    /**
     * 获取string 的主键
     *
     * @return string_code - string 的主键
     */
    public String getStringCode() {
        return stringCode;
    }

    /**
     * 设置string 的主键
     *
     * @param stringCode string 的主键
     */
    public void setStringCode(String stringCode) {
        this.stringCode = stringCode == null ? null : stringCode.trim();
    }

    /**
     * 获取描述A
     *
     * @return describe_a - 描述A
     */
    public String getDescribeA() {
        return describeA;
    }

    /**
     * 设置描述A
     *
     * @param describeA 描述A
     */
    public void setDescribeA(String describeA) {
        this.describeA = describeA == null ? null : describeA.trim();
    }

    /**
     * 获取描述B
     *
     * @return describe_b - 描述B
     */
    public String getDescribeB() {
        return describeB;
    }

    /**
     * 设置描述B
     *
     * @param describeB 描述B
     */
    public void setDescribeB(String describeB) {
        this.describeB = describeB == null ? null : describeB.trim();
    }

    /**
     * 获取显示排序
     *
     * @return display_order - 显示排序
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 设置显示排序
     *
     * @param displayOrder 显示排序
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
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
     * 获取更新人
     *
     * @return last_modified_by - 更新人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置更新人
     *
     * @param lastModifiedBy 更新人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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
     * 获取删除标识：1-未删除，0-已删除
     *
     * @return delete_flag - 删除标识：1-未删除，0-已删除
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标识：1-未删除，0-已删除
     *
     * @param deleteFlag 删除标识：1-未删除，0-已删除
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}