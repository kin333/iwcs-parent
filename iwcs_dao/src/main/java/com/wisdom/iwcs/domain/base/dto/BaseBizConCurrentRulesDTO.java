package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_biz_concurrent_rules")
public class BaseBizConCurrentRulesDTO {
    @Id
    private Integer id;

    /**
     * 原业务类型编号
     */
    @Column(name = "src_biz_type")
    private String srcBizType;

    /**
     * 与原业务类型不可同时并发的业务类型
     */
    @Column(name = "unable_concurrent_biz")
    private String unableConcurrentBiz;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

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
     * 获取原业务类型编号
     *
     * @return src_biz_type - 原业务类型编号
     */
    public String getSrcBizType() {
        return srcBizType;
    }

    /**
     * 设置原业务类型编号
     *
     * @param srcBizType 原业务类型编号
     */
    public void setSrcBizType(String srcBizType) {
        this.srcBizType = srcBizType == null ? null : srcBizType.trim();
    }

    /**
     * 获取与原业务类型不可同时并发的业务类型
     *
     * @return unable_concurrent_biz - 与原业务类型不可同时并发的业务类型
     */
    public String getUnableConcurrentBiz() {
        return unableConcurrentBiz;
    }

    /**
     * 设置与原业务类型不可同时并发的业务类型
     *
     * @param unableConcurrentBiz 与原业务类型不可同时并发的业务类型
     */
    public void setUnableConcurrentBiz(String unableConcurrentBiz) {
        this.unableConcurrentBiz = unableConcurrentBiz == null ? null : unableConcurrentBiz.trim();
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
}