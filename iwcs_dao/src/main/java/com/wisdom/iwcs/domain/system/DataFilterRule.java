package com.wisdom.iwcs.domain.system;

import com.google.common.base.MoreObjects;

import java.util.Date;

public class DataFilterRule {
    private Integer id;

    private String ruleName;

    private String ruleColumn;

    private String ruleConditions;

    private String ruleValue;

    private Date createDate;

    private String createBy;

    private String createName;

    private Date updateDate;

    private String updateBy;

    private String updateName;

    private Integer menuId;

    private Integer authorityId;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
                .add("Hash", hashCode())
                .add("id", id).add("ruleName", ruleName).add("ruleColumn", ruleColumn)
                .add("ruleConditions", ruleConditions).add("ruleValue", ruleValue).add("createDate", createDate).add("createBy", createBy)
                .add("createName", createName).add("updateDate", updateDate).add("updateBy", updateBy).add("updateName", updateName)
                .add("menuId", menuId)
                .omitNullValues().toString();
    }
}