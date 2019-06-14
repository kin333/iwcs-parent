package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_pod_type_bincode_detail")
public class BasePodTypeBincodeDetailDTO {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

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
     * 层数，表示第几层
     */
    private Integer layer;

    /**
     * 方向，表示哪一面
     */
    private Integer direction;

    /**
     * 仓位类型编码
     */
    @Column(name = "bin_type_code")
    private String binTypeCode;

    /**
     * 该层该面有多少个仓位
     */
    @Column(name = "bincode_num")
    private String bincodeNum;

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
     * 获取层数，表示第几层
     *
     * @return layer - 层数，表示第几层
     */
    public Integer getLayer() {
        return layer;
    }

    /**
     * 设置层数，表示第几层
     *
     * @param layer 层数，表示第几层
     */
    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    /**
     * 获取方向，表示哪一面
     *
     * @return direction - 方向，表示哪一面
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     * 设置方向，表示哪一面
     *
     * @param direction 方向，表示哪一面
     */
    public void setDirection(Integer direction) {
        this.direction = direction;
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
     * 获取该层该面有多少个仓位
     *
     * @return bincode_num - 该层该面有多少个仓位
     */
    public String getBincodeNum() {
        return bincodeNum;
    }

    /**
     * 设置该层该面有多少个仓位
     *
     * @param bincodeNum 该层该面有多少个仓位
     */
    public void setBincodeNum(String bincodeNum) {
        this.bincodeNum = bincodeNum == null ? null : bincodeNum.trim();
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