package com.wisdom.iwcs.domain.instock.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "instock_call_stra_param")
public class InstockCallStraParamDTO {
    @Id
    private Integer id;

    /**
     * 参数
     */
    private String param;

    /**
     * 参数名字
     */
    @Column(name = "param_name")
    private String paramName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 激活状态
     */
    private Byte status;

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
     * 获取参数
     *
     * @return param - 参数
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置参数
     *
     * @param param 参数
     */
    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    /**
     * 获取参数名字
     *
     * @return param_name - 参数名字
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置参数名字
     *
     * @param paramName 参数名字
     */
    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
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
     * 获取激活状态
     *
     * @return status - 激活状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置激活状态
     *
     * @param status 激活状态
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}