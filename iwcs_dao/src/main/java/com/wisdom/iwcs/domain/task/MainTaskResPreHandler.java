package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "main_task_res_pre_handler")
public class MainTaskResPreHandler {
    @Id
    private Integer id;

    /**
     * 编码
     */
    @Column(name = "pre_handler_code")
    private String preHandlerCode;

    /**
     * 主任务编号
     */
    @Column(name = "main_task_type")
    private String mainTaskType;

    /**
     * 资源类型(货架:pod/点位:point)
     */
    @Column(name = "resource_type")
    private String resourceType;

    /**
     * 资源定位方法
     */
    @Column(name = "resource_locate_type")
    private String resourceLocateType;

    /**
     * 资源定位参数(json数组)
     */
    @Column(name = "resource_locate_params")
    private String resourceLocateParams;

    /**
     * 校验策略名
     */
    @Column(name = "validation_strategy")
    private String validationStrategy;

    /**
     * 操作策略名
     */
    @Column(name = "operation_strategy")
    private String operationStrategy;

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
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

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
     * 获取编码
     *
     * @return pre_handler_code - 编码
     */
    public String getPreHandlerCode() {
        return preHandlerCode;
    }

    /**
     * 设置编码
     *
     * @param preHandlerCode 编码
     */
    public void setPreHandlerCode(String preHandlerCode) {
        this.preHandlerCode = preHandlerCode == null ? null : preHandlerCode.trim();
    }

    /**
     * 获取主任务编号
     *
     * @return main_task_type - 主任务编号
     */
    public String getMainTaskType() {
        return mainTaskType;
    }

    /**
     * 设置主任务编号
     *
     * @param mainTaskType 主任务编号
     */
    public void setMainTaskType(String mainTaskType) {
        this.mainTaskType = mainTaskType == null ? null : mainTaskType.trim();
    }

    /**
     * 获取资源类型(货架:pod/点位:point)
     *
     * @return resource_type - 资源类型(货架:pod/点位:point)
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类型(货架:pod/点位:point)
     *
     * @param resourceType 资源类型(货架:pod/点位:point)
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    /**
     * 获取资源定位方法
     *
     * @return resource_locate_type - 资源定位方法
     */
    public String getResourceLocateType() {
        return resourceLocateType;
    }

    /**
     * 设置资源定位方法
     *
     * @param resourceLocateType 资源定位方法
     */
    public void setResourceLocateType(String resourceLocateType) {
        this.resourceLocateType = resourceLocateType == null ? null : resourceLocateType.trim();
    }

    /**
     * 获取资源定位参数(json)
     *
     * @return resource_locate_params - 资源定位参数(json)
     */
    public String getResourceLocateParams() {
        return resourceLocateParams;
    }

    /**
     * 设置资源定位参数(json)
     *
     * @param resourceLocateParams 资源定位参数(json)
     */
    public void setResourceLocateParams(String resourceLocateParams) {
        this.resourceLocateParams = resourceLocateParams == null ? null : resourceLocateParams.trim();
    }

    /**
     * 获取校验策略名
     *
     * @return validation_strategy - 校验策略名
     */
    public String getValidationStrategy() {
        return validationStrategy;
    }

    /**
     * 设置校验策略名
     *
     * @param validationStrategy 校验策略名
     */
    public void setValidationStrategy(String validationStrategy) {
        this.validationStrategy = validationStrategy == null ? null : validationStrategy.trim();
    }

    /**
     * 获取操作策略名
     *
     * @return operation_strategy - 操作策略名
     */
    public String getOperationStrategy() {
        return operationStrategy;
    }

    /**
     * 设置操作策略名
     *
     * @param operationStrategy 操作策略名
     */
    public void setOperationStrategy(String operationStrategy) {
        this.operationStrategy = operationStrategy == null ? null : operationStrategy.trim();
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
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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