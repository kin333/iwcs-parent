package com.wisdom.iwcs.domain.task.dto;

import javax.persistence.*;

public class AddressDTO {
    @Id
    private Long id;

    /**
     * 地址编码
     */
    private String code;

    /**
     * ip和端口信息
     */
    private String address;

    /**
     * 中文名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态: 0:启用,1:禁用
     */
    private String status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取地址编码
     *
     * @return code - 地址编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置地址编码
     *
     * @param code 地址编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取ip和端口信息
     *
     * @return address - ip和端口信息
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置ip和端口信息
     *
     * @param address ip和端口信息
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取中文名
     *
     * @return name - 中文名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置中文名
     *
     * @param name 中文名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 获取状态: 0:启用,1:禁用
     *
     * @return status - 状态: 0:启用,1:禁用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态: 0:启用,1:禁用
     *
     * @param status 状态: 0:启用,1:禁用
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}