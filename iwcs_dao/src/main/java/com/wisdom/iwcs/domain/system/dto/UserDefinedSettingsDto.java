package com.wisdom.iwcs.domain.system.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "s_user_defined_settings")
public class UserDefinedSettingsDto {
    @Id
    private Integer id;

    /**

     */
    @Column(name = "settings_class")
    private String settingsClass;

    /**

     */
    @Column(name = "settings_name")
    private String settingsName;

    /**

     */
    @Column(name = "settings_content")
    private String settingsContent;

    /**

     */
    @Column(name = "settings_index")
    private Integer settingsIndex;

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
     *

     */
    public String getSettingsClass() {
        return settingsClass;
    }

    /**
     *

     */
    public void setSettingsClass(String settingsClass) {
        this.settingsClass = settingsClass == null ? null : settingsClass.trim();
    }

    /**
     *

     */
    public String getSettingsName() {
        return settingsName;
    }

    /**
     *

     */
    public void setSettingsName(String settingsName) {
        this.settingsName = settingsName == null ? null : settingsName.trim();
    }

    /**
     *

     */
    public String getSettingsContent() {
        return settingsContent;
    }

    /**
     *

     */
    public void setSettingsContent(String settingsContent) {
        this.settingsContent = settingsContent == null ? null : settingsContent.trim();
    }

    /**
     *

     */
    public Integer getSettingsIndex() {
        return settingsIndex;
    }

    /**
     *

     */
    public void setSettingsIndex(Integer settingsIndex) {
        this.settingsIndex = settingsIndex;
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