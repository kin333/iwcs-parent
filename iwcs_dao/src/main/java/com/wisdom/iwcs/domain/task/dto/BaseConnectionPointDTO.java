package com.wisdom.iwcs.domain.task.dto;

import java.util.Date;
import javax.persistence.*;

@Table(name = "base_connection_point")
public class BaseConnectionPointDTO {
    @Id
    @Column(name = "connection_point_id")
    private Integer connectionPointId;

    /**
     * 关联点编号
     */
    @Column(name = "connection_point_code")
    private String connectionPointCode;

    /**
     * 点位地码
     */
    @Column(name = "ber_code")
    private String berCode;

    /**
     * 点位编号
     */
    @Column(name = "point_alias")
    private String pointAlias;

    /**
     * 点位类型：电梯检验点1，电梯交接点2，线体工作点3
     */
    @Column(name = "connection_point_type")
    private String connectionPointType;

    /**
     * 关联设备编号
     */
    @Column(name = "device_code")
    private String deviceCode;

    /**
     * 地图编号
     */
    @Column(name = "map_code")
    private String mapCode;

    /**
     * 库区编号
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 预留字段1
     */
    @Column(name = "connection_prop1")
    private String connectionProp1;

    /**
     * 预留字段2
     */
    @Column(name = "connection_prop2")
    private String connectionProp2;

    /**
     * 预留字段3
     */
    @Column(name = "connection_prop3")
    private String connectionProp3;

    /**
     * 预留字段4
     */
    @Column(name = "connection_prop4")
    private String connectionProp4;

    /**
     * 预留字段5
     */
    @Column(name = "connection_prop5")
    private String connectionProp5;

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
     * @return connection_point_id
     */
    public Integer getConnectionPointId() {
        return connectionPointId;
    }

    /**
     * @param connectionPointId
     */
    public void setConnectionPointId(Integer connectionPointId) {
        this.connectionPointId = connectionPointId;
    }

    /**
     * 获取关联点编号
     *
     * @return connection_point_code - 关联点编号
     */
    public String getConnectionPointCode() {
        return connectionPointCode;
    }

    /**
     * 设置关联点编号
     *
     * @param connectionPointCode 关联点编号
     */
    public void setConnectionPointCode(String connectionPointCode) {
        this.connectionPointCode = connectionPointCode == null ? null : connectionPointCode.trim();
    }

    /**
     * 获取点位地码
     *
     * @return ber_code - 点位地码
     */
    public String getBerCode() {
        return berCode;
    }

    /**
     * 设置点位地码
     *
     * @param berCode 点位地码
     */
    public void setBerCode(String berCode) {
        this.berCode = berCode == null ? null : berCode.trim();
    }

    /**
     * 获取点位编号
     *
     * @return point_alias - 点位编号
     */
    public String getPointAlias() {
        return pointAlias;
    }

    /**
     * 设置点位编号
     *
     * @param pointAlias 点位编号
     */
    public void setPointAlias(String pointAlias) {
        this.pointAlias = pointAlias == null ? null : pointAlias.trim();
    }

    /**
     * 获取点位类型：电梯检验点1，电梯交接点2，线体工作点3
     *
     * @return connection_point_type - 点位类型：电梯检验点1，电梯交接点2，线体工作点3
     */
    public String getConnectionPointType() {
        return connectionPointType;
    }

    /**
     * 设置点位类型：电梯检验点1，电梯交接点2，线体工作点3
     *
     * @param connectionPointType 点位类型：电梯检验点1，电梯交接点2，线体工作点3
     */
    public void setConnectionPointType(String connectionPointType) {
        this.connectionPointType = connectionPointType == null ? null : connectionPointType.trim();
    }

    /**
     * 获取关联设备编号
     *
     * @return device_code - 关联设备编号
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * 设置关联设备编号
     *
     * @param deviceCode 关联设备编号
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
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
     * 获取库区编号
     *
     * @return area_code - 库区编号
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置库区编号
     *
     * @param areaCode 库区编号
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取预留字段1
     *
     * @return connection_prop1 - 预留字段1
     */
    public String getConnectionProp1() {
        return connectionProp1;
    }

    /**
     * 设置预留字段1
     *
     * @param connectionProp1 预留字段1
     */
    public void setConnectionProp1(String connectionProp1) {
        this.connectionProp1 = connectionProp1 == null ? null : connectionProp1.trim();
    }

    /**
     * 获取预留字段2
     *
     * @return connection_prop2 - 预留字段2
     */
    public String getConnectionProp2() {
        return connectionProp2;
    }

    /**
     * 设置预留字段2
     *
     * @param connectionProp2 预留字段2
     */
    public void setConnectionProp2(String connectionProp2) {
        this.connectionProp2 = connectionProp2 == null ? null : connectionProp2.trim();
    }

    /**
     * 获取预留字段3
     *
     * @return connection_prop3 - 预留字段3
     */
    public String getConnectionProp3() {
        return connectionProp3;
    }

    /**
     * 设置预留字段3
     *
     * @param connectionProp3 预留字段3
     */
    public void setConnectionProp3(String connectionProp3) {
        this.connectionProp3 = connectionProp3 == null ? null : connectionProp3.trim();
    }

    /**
     * 获取预留字段4
     *
     * @return connection_prop4 - 预留字段4
     */
    public String getConnectionProp4() {
        return connectionProp4;
    }

    /**
     * 设置预留字段4
     *
     * @param connectionProp4 预留字段4
     */
    public void setConnectionProp4(String connectionProp4) {
        this.connectionProp4 = connectionProp4 == null ? null : connectionProp4.trim();
    }

    /**
     * 获取预留字段5
     *
     * @return connection_prop5 - 预留字段5
     */
    public String getConnectionProp5() {
        return connectionProp5;
    }

    /**
     * 设置预留字段5
     *
     * @param connectionProp5 预留字段5
     */
    public void setConnectionProp5(String connectionProp5) {
        this.connectionProp5 = connectionProp5 == null ? null : connectionProp5.trim();
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