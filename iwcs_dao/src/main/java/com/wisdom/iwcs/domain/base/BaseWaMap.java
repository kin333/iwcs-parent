package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_wa_map")
public class BaseWaMap {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 地图编号
     */
    @Column(name = "map_code")
    private String mapCode;

    /**
     * 库区代码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 地图属性，如楼层等
     */
    @Column(name = "map_prop1")
    private String mapProp1;

    /**
     * 地图属性，预留
     */
    @Column(name = "map_prop2")
    private String mapProp2;

    /**
     * 地图属性，预留
     */
    @Column(name = "map_prop3")
    private String mapProp3;

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
     * 获取地图编号
     *
     * @return map_code - 地图编号
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * 设置地图编号
     *
     * @param mapCode 地图编号
     */
    public void setMapCode(String mapCode) {
        this.mapCode = mapCode == null ? null : mapCode.trim();
    }

    /**
     * 获取库区代码
     *
     * @return area_code - 库区代码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置库区代码
     *
     * @param areaCode 库区代码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取地图属性，如楼层等
     *
     * @return map_prop1 - 地图属性，如楼层等
     */
    public String getMapProp1() {
        return mapProp1;
    }

    /**
     * 设置地图属性，如楼层等
     *
     * @param mapProp1 地图属性，如楼层等
     */
    public void setMapProp1(String mapProp1) {
        this.mapProp1 = mapProp1 == null ? null : mapProp1.trim();
    }

    /**
     * 获取地图属性，预留
     *
     * @return map_prop2 - 地图属性，预留
     */
    public String getMapProp2() {
        return mapProp2;
    }

    /**
     * 设置地图属性，预留
     *
     * @param mapProp2 地图属性，预留
     */
    public void setMapProp2(String mapProp2) {
        this.mapProp2 = mapProp2 == null ? null : mapProp2.trim();
    }

    /**
     * 获取地图属性，预留
     *
     * @return map_prop3 - 地图属性，预留
     */
    public String getMapProp3() {
        return mapProp3;
    }

    /**
     * 设置地图属性，预留
     *
     * @param mapProp3 地图属性，预留
     */
    public void setMapProp3(String mapProp3) {
        this.mapProp3 = mapProp3 == null ? null : mapProp3.trim();
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
}