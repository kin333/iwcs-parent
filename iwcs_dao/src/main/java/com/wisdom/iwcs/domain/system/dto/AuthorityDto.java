package com.wisdom.iwcs.domain.system.dto;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * An authority (a security role) used by Spring Security.
 */

public class AuthorityDto implements Serializable {
    private Integer id;

    private String name;

    private String chineseName;

    private String url;

    private String description;

    private Integer parentId;

    private Byte authType;

    private Integer sortCode;

    private String operationcode;

    private String operationname;

    private Integer operationtype;

    private String ruleColumn;

    private String ruleConditions;

    private String ruleValue;

    public String getRuleColumn() {
        return ruleColumn;
    }

    public void setRuleColumn(String ruleColumn) {
        this.ruleColumn = ruleColumn;
    }

    public String getRuleConditions() {
        return ruleConditions;
    }

    public void setRuleConditions(String ruleConditions) {
        this.ruleConditions = ruleConditions;
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }

    public String getOperationcode() {
        return operationcode;
    }

    public void setOperationcode(String operationcode) {
        this.operationcode = operationcode;
    }

    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname;
    }

    public Integer getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(Integer operationtype) {
        this.operationtype = operationtype;
    }

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