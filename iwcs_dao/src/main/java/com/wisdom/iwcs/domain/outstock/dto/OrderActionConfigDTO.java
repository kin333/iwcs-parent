package com.wisdom.iwcs.domain.outstock.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "outstock_order_action_config")
public class OrderActionConfigDTO {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 配置号
     */
    @Column(name = "config_code")
    private String configCode;

    /**
     * 配置类型：派车单等
     */
    @Column(name = "config_type")
    private String configType;

    /**
     * 配置参数
     */
    @Column(name = "config_prop1")
    private String configProp1;

    /**
     * 配置参数
     */
    @Column(name = "config_prop2")
    private String configProp2;

    /**
     * 配置参数
     */
    @Column(name = "config_prop3")
    private String configProp3;

    /**
     * 配置参数
     */
    @Column(name = "config_prop4")
    private String configProp4;

    /**
     * 配置参数
     */
    @Column(name = "config_prop5")
    private String configProp5;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取配置号
     *
     * @return config_code - 配置号
     */
    public String getConfigCode() {
        return configCode;
    }

    /**
     * 设置配置号
     *
     * @param configCode 配置号
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    /**
     * 获取配置类型：派车单等
     *
     * @return config_type - 配置类型：派车单等
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * 设置配置类型：派车单等
     *
     * @param configType 配置类型：派车单等
     */
    public void setConfigType(String configType) {
        this.configType = configType == null ? null : configType.trim();
    }

    /**
     * 获取配置参数
     *
     * @return config_prop1 - 配置参数
     */
    public String getConfigProp1() {
        return configProp1;
    }

    /**
     * 设置配置参数
     *
     * @param configProp1 配置参数
     */
    public void setConfigProp1(String configProp1) {
        this.configProp1 = configProp1 == null ? null : configProp1.trim();
    }

    /**
     * 获取配置参数
     *
     * @return config_prop2 - 配置参数
     */
    public String getConfigProp2() {
        return configProp2;
    }

    /**
     * 设置配置参数
     *
     * @param configProp2 配置参数
     */
    public void setConfigProp2(String configProp2) {
        this.configProp2 = configProp2 == null ? null : configProp2.trim();
    }

    /**
     * 获取配置参数
     *
     * @return config_prop3 - 配置参数
     */
    public String getConfigProp3() {
        return configProp3;
    }

    /**
     * 设置配置参数
     *
     * @param configProp3 配置参数
     */
    public void setConfigProp3(String configProp3) {
        this.configProp3 = configProp3 == null ? null : configProp3.trim();
    }

    /**
     * 获取配置参数
     *
     * @return config_prop4 - 配置参数
     */
    public String getConfigProp4() {
        return configProp4;
    }

    /**
     * 设置配置参数
     *
     * @param configProp4 配置参数
     */
    public void setConfigProp4(String configProp4) {
        this.configProp4 = configProp4 == null ? null : configProp4.trim();
    }

    /**
     * 获取配置参数
     *
     * @return config_prop5 - 配置参数
     */
    public String getConfigProp5() {
        return configProp5;
    }

    /**
     * 设置配置参数
     *
     * @param configProp5 配置参数
     */
    public void setConfigProp5(String configProp5) {
        this.configProp5 = configProp5 == null ? null : configProp5.trim();
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