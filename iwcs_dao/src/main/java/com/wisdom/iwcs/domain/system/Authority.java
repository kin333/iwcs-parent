package com.wisdom.iwcs.domain.system;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * An authority (a security role) used by Spring Security.
 */

public class Authority implements Serializable {
    private Integer id;

    private String name;

    private String chineseName;

    private String url;

    private String description;

    private Integer parentId;

    private Byte authType;

    private Integer sortCode;

    private Boolean checked = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getAuthType() {
        return authType;
    }

    public void setAuthType(Byte authType) {
        this.authType = authType;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
                .add("Hash", hashCode())
                .add("id", id).add("name", name).add("chineseName", chineseName)
                .add("url", url).add("description", description).add("parentId", parentId).add("authType", authType)
                .add("sortCode", sortCode)
                .omitNullValues().toString();
    }
}