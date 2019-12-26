package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "resource_handler_strategy")
public class ResourceHandlerStrategy {
    @Id
    private Integer id;

    /**
     * 编码
     */
    @Column(name = "strategy_code")
    private String strategyCode;

    /**
     * 策略名称
     */
    @Column(name = "strategy_name")
    private String strategyName;

    /**
     * 策略类型:校验策略validation,操作策略operation
     */
    @Column(name = "strategy_type")
    private String strategyType;

    /**
     * 资源类型(货架:pod/点位:point)
     */
    @Column(name = "resource_type")
    private String resourceType;

    /**
     * 策略处理器
     */
    @Column(name = "strategy_handler")
    private String strategyHandler;

    /**
     * 策略参数(json格式)
     */
    @Column(name = "strategy_params")
    private String strategyParams;

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
     * @return strategy_code - 编码
     */
    public String getStrategyCode() {
        return strategyCode;
    }

    /**
     * 设置编码
     *
     * @param strategyCode 编码
     */
    public void setStrategyCode(String strategyCode) {
        this.strategyCode = strategyCode == null ? null : strategyCode.trim();
    }

    /**
     * 获取策略名称
     *
     * @return strategy_name - 策略名称
     */
    public String getStrategyName() {
        return strategyName;
    }

    /**
     * 设置策略名称
     *
     * @param strategyName 策略名称
     */
    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName == null ? null : strategyName.trim();
    }

    /**
     * 获取策略类型:校验策略validation,操作策略operation
     *
     * @return strategy_type - 策略类型:校验策略validation,操作策略operation
     */
    public String getStrategyType() {
        return strategyType;
    }

    /**
     * 设置策略类型:校验策略validation,操作策略operation
     *
     * @param strategyType 策略类型:校验策略validation,操作策略operation
     */
    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType == null ? null : strategyType.trim();
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
     * 获取策略处理器
     *
     * @return strategy_handler - 策略处理器
     */
    public String getStrategyHandler() {
        return strategyHandler;
    }

    /**
     * 设置策略处理器
     *
     * @param strategyHandler 策略处理器
     */
    public void setStrategyHandler(String strategyHandler) {
        this.strategyHandler = strategyHandler == null ? null : strategyHandler.trim();
    }

    /**
     * 获取策略参数(json格式)
     *
     * @return strategy_params - 策略参数(json格式)
     */
    public String getStrategyParams() {
        return strategyParams;
    }

    /**
     * 设置策略参数(json格式)
     *
     * @param strategyParams 策略参数(json格式)
     */
    public void setStrategyParams(String strategyParams) {
        this.strategyParams = strategyParams == null ? null : strategyParams.trim();
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