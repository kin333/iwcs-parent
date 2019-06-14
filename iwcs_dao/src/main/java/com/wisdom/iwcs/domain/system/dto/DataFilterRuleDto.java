package com.wisdom.iwcs.domain.system.dto;

public class DataFilterRuleDto {

    private String ruleName;

    private String ruleColumn;

    private String ruleConditions;

    private String ruleValue;

    private Integer menuId;

    private Integer authorityId;

    private Boolean valid;

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public String toString() {
        return "DataFilterRuleDto{" +
                "ruleName='" + ruleName + '\'' +
                ", ruleColumn='" + ruleColumn + '\'' +
                ", ruleConditions='" + ruleConditions + '\'' +
                ", ruleValue='" + ruleValue + '\'' +
                ", menuId=" + menuId +
                ", authorityId=" + authorityId +
                '}';
    }
}