package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.soap.Text;
import java.util.Date;

@Table(name = "base_map")
public class BaseMapDTO {
    @Id
    private Integer id;

    /**
     * 地图编号
     */
    @Column(name = "map_code")
    private String mapCode;

    /**
     * 地图名称
     */
    @Column(name = "map_name")
    private String mapName;

    /**
     * 行数
     */
    @Column(name = "row_count")
    private Integer rowCount;

    /**
     * 列数
     */
    @Column(name = "col_count")
    private Integer colCount;

    /**
     * 每格宽度，毫米，默认1000
     */
    private Integer width;

    /**
     * 每格高度，毫米，默认1000
     */
    private Integer height;

    /**
     * 地图类型，1为栅格地图，2为拓扑地图
     */
    @Column(name = "map_type")
    private String mapType;

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
     * 地码类型
     */
    @Column(name = "ground_type_code")
    private String groundTypeCode;

    /**
     * 地图数据
     */
    @Column(name = "content")
    private String content;
    @Column(name = "version")
    private String version;
    @Column(name = "pod_data")
    private String podData;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPodData() {
        return podData;
    }

    public void setPodData(String podData) {
        this.podData = podData;
    }

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
     * 获取地图名称
     *
     * @return map_name - 地图名称
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * 设置地图名称
     *
     * @param mapName 地图名称
     */
    public void setMapName(String mapName) {
        this.mapName = mapName == null ? null : mapName.trim();
    }

    /**
     * 获取行数
     *
     * @return row_count - 行数
     */
    public Integer getRowCount() {
        return rowCount;
    }

    /**
     * 设置行数
     *
     * @param rowCount 行数
     */
    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * 获取列数
     *
     * @return col_count - 列数
     */
    public Integer getColCount() {
        return colCount;
    }

    /**
     * 设置列数
     *
     * @param colCount 列数
     */
    public void setColCount(Integer colCount) {
        this.colCount = colCount;
    }

    /**
     * 获取每格宽度，毫米，默认1000
     *
     * @return width - 每格宽度，毫米，默认1000
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 设置每格宽度，毫米，默认1000
     *
     * @param width 每格宽度，毫米，默认1000
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 获取每格高度，毫米，默认1000
     *
     * @return height - 每格高度，毫米，默认1000
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 设置每格高度，毫米，默认1000
     *
     * @param height 每格高度，毫米，默认1000
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 获取地图类型，1为栅格地图，2为拓扑地图
     *
     * @return map_type - 地图类型，1为栅格地图，2为拓扑地图
     */
    public String getMapType() {
        return mapType;
    }

    /**
     * 设置地图类型，1为栅格地图，2为拓扑地图
     *
     * @param mapType 地图类型，1为栅格地图，2为拓扑地图
     */
    public void setMapType(String mapType) {
        this.mapType = mapType == null ? null : mapType.trim();
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

    public String getGroundTypeCode() {
        return groundTypeCode;
    }

    public void setGroundTypeCode(String groundTypeCode) {
        this.groundTypeCode = groundTypeCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}