package com.wisdom.iwcs.domain.system;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "s_table_columns_settings")
public class TableColumnsSettings {
    /**
     * id
     */
    @Id
    private Integer id;

    /**

     */
    @Column(name = "table_name")
    private String tableName;

    /**

     */
    @Column(name = "columns_settings")
    private String columnsSettings;

    /**

     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**

     */
    @Column(name = "created_time")
    private Date createdTime;

    /**

     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**

     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**

     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *

     */
    public String getTableName() {
        return tableName;
    }

    /**
     *

     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     *

     */
    public String getColumnsSettings() {
        return columnsSettings;
    }

    /**
     *

     */
    public void setColumnsSettings(String columnsSettings) {
        this.columnsSettings = columnsSettings == null ? null : columnsSettings.trim();
    }

    /**
     *

     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     *

     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *

     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     *

     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     *

     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     *

     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     *

     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     *

     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     *

     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     *

     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}