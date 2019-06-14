package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "base_wb_group")
public class BaseWbGroupDTO {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 分组编号
     */
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 分组名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 分组类型（互斥、备货）
     */
    @Column(name = "group_type_code")
    private String groupTypeCode;

    /**
     * 备注
     */
    private String remark;

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

    private String wbGroupDetailProp1;

    private String wbGroupDetailProp2;

    private String wbGroupDetailProp3;

    private String wbGroupDetailProp4;

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Integer getId() {
        return id;
    }

    public List<BaseWbGroupDetailDTO> baseWbGroupDetailDTOList;

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分组编号
     *
     * @return group_code - 分组编号
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置分组编号
     *
     * @param groupCode 分组编号
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    /**
     * 获取分组名称
     *
     * @return group_name - 分组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置分组名称
     *
     * @param groupName 分组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * 获取分组类型（互斥、备货）
     *
     * @return group_type_code - 分组类型（互斥、备货）
     */
    public String getGroupTypeCode() {
        return groupTypeCode;
    }

    /**
     * 设置分组类型（互斥、备货）
     *
     * @param groupTypeCode 分组类型（互斥、备货）
     */
    public void setGroupTypeCode(String groupTypeCode) {
        this.groupTypeCode = groupTypeCode == null ? null : groupTypeCode.trim();
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

    public List<BaseWbGroupDetailDTO> getBaseWbGroupDetailDTOList() {
        return baseWbGroupDetailDTOList;
    }

    public void setBaseWbGroupDetailDTOList(List<BaseWbGroupDetailDTO> baseWbGroupDetailDTOList) {
        this.baseWbGroupDetailDTOList = baseWbGroupDetailDTOList;
    }

    public String getWbGroupDetailProp1() {
        return wbGroupDetailProp1;
    }

    public void setWbGroupDetailProp1(String wbGroupDetailProp1) {
        this.wbGroupDetailProp1 = wbGroupDetailProp1;
    }

    public String getWbGroupDetailProp2() {
        return wbGroupDetailProp2;
    }

    public void setWbGroupDetailProp2(String wbGroupDetailProp2) {
        this.wbGroupDetailProp2 = wbGroupDetailProp2;
    }

    public String getWbGroupDetailProp3() {
        return wbGroupDetailProp3;
    }

    public void setWbGroupDetailProp3(String wbGroupDetailProp3) {
        this.wbGroupDetailProp3 = wbGroupDetailProp3;
    }

    public String getWbGroupDetailProp4() {
        return wbGroupDetailProp4;
    }

    public void setWbGroupDetailProp4(String wbGroupDetailProp4) {
        this.wbGroupDetailProp4 = wbGroupDetailProp4;
    }
}