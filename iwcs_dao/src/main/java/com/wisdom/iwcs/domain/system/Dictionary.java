package com.wisdom.iwcs.domain.system;

import java.io.Serializable;

public class Dictionary implements Serializable {

    private Integer id;//主键
    private String dictType;//类别
    private String dictTypeName;//类别说明
    private String dictName;//名称
    private String dictValue;//值
    private Integer sortCode;//排序
    private Byte status;//激活状态
    private Integer createdBy;//创建人
    private Long createdTime;//创建时间
    private Integer lastModifiedBy;//更新人
    private Long lastModifiedTime;//更新时间
    private Integer deleteFlag;//删除标记 0为删除 1为正常

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName == null ? null : dictTypeName.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public enum DictType {
        ROLE_RIGHTT_LEVEL("ROLE_RIGHTT_LEVEL"),//角色权级
        DEPARTMENT_POWER_LEVEL("DEPARTMENT_POWER_LEVEL"),//部门权级
        ACCOUNT_STATUS("ACCOUNT_STATUS"),//账号状态
        CITY_MANAGEMENT("CITY_MANAGEMENT"),//城市管理
        PRODUCT_PROJECT("PRODUCT_PROJECT"),//产品项目
        COMMODITY_ATTRIBUTE("COMMODITY_ATTRIBUTE"),//商品属性
        COMMODITY_MATERIAL_SUPPLY_NODE("COMMODITY_MATERIAL_SUPPLY_NODE"),//商品材料供货节点
        COMMODITY_MATERIAL_STOCKING_NODE("COMMODITY_MATERIAL_STOCKING_NODE"),//商品材料备货节点
        COMMODITY_MATERIAL_RENEWAL_SOURCE("COMMODITY_MATERIAL_RENEWAL_SOURCE"),//商品材料更新来源
        COMMODITY_MATERIAL_UNIT("COMMODITY_MATERIAL_UNIT"),//商品材料单位
        COMMODITY_MATERIAL_WEIGHT_UNIT("COMMODITY_MATERIAL_WEIGHT_UNIT"),//商品材料重量单位
        MATERIAL_PROPERTIES("MATERIAL_PROPERTIES"),//材料属性
        WAREHOUSE_TYPE("WAREHOUSE_TYPE"),//仓库类型
        FUNCTION_CATEGORY_TYPE("FUNCTION_CATEGORY_TYPE"),//功能区类目类型
        FUNCTION_AREAS_DEFINITION("FUNCTION_AREAS_DEFINITION"),//功能区定义
        PLAN_TEMPLATE_TYPE("PLAN_TEMPLATE_TYPE"),//计划模板类型
        WORK_MANAGEMENT("WORK_MANAGEMENT"),//工种管理
        MINIMUM_UNIT_UNIT("MINIMUM_UNIT_UNIT"),//最小单元单位
        PLANNING_TEMPLATE_TYPE("PLANNING_TEMPLATE_TYPE"),
        PROJECT_TYPE("PROJECT_TYPE");//项目类型

        private String value;

        DictType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

}