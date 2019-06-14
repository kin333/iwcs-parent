package com.wisdom.iwcs.domain.quartz.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "quartz_job_log")
public class QuartzJobLogDTO {
    @Id
    @Column(name = "log_id")
    private Integer logId;

    /**
     * 任务id
     */
    @Column(name = "job_id")
    private Integer jobId;

    /**
     * Bean名称
     */
    @Column(name = "bean_name")
    private String beanName;

    /**
     * 方法名
     */
    @Column(name = "method_name")
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 任务状态，0：暂停，1：正常
     */
    private String status;

    /**
     * 失败信息
     */
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * @return log_id
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * 获取任务id
     *
     * @return job_id - 任务id
     */
    public Integer getJobId() {
        return jobId;
    }

    /**
     * 设置任务id
     *
     * @param jobId 任务id
     */
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    /**
     * 获取Bean名称
     *
     * @return bean_name - Bean名称
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 设置Bean名称
     *
     * @param beanName Bean名称
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    /**
     * 获取方法名
     *
     * @return method_name - 方法名
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置方法名
     *
     * @param methodName 方法名
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * 获取参数
     *
     * @return params - 参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置参数
     *
     * @param params 参数
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * 获取任务状态，0：暂停，1：正常
     *
     * @return status - 任务状态，0：暂停，1：正常
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置任务状态，0：暂停，1：正常
     *
     * @param status 任务状态，0：暂停，1：正常
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取失败信息
     *
     * @return error - 失败信息
     */
    public String getError() {
        return error;
    }

    /**
     * 设置失败信息
     *
     * @param error 失败信息
     */
    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    /**
     * 获取耗时(单位：毫秒)
     *
     * @return times - 耗时(单位：毫秒)
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * 设置耗时(单位：毫秒)
     *
     * @param times 耗时(单位：毫秒)
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}