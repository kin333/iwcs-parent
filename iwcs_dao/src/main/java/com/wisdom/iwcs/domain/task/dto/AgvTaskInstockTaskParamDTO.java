package com.wisdom.iwcs.domain.task.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "agv_task_instock_task_param")
public class AgvTaskInstockTaskParamDTO {
    @Id
    private Integer id;

    /**
     * 工作台任务编号：自动生成唯一任务编号
     */
    @Column(name = "task_no")
    private String taskNo;

    /**
     * 入库呼叫策略code
     */
    @Column(name = "stra_code")
    private String straCode;

    /**
     * 策略参数
     */
    @Column(name = "stra_param")
    private String straParam;

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
     * 获取工作台任务编号：自动生成唯一任务编号
     *
     * @return task_no - 工作台任务编号：自动生成唯一任务编号
     */
    public String getTaskNo() {
        return taskNo;
    }

    /**
     * 设置工作台任务编号：自动生成唯一任务编号
     *
     * @param taskNo 工作台任务编号：自动生成唯一任务编号
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo == null ? null : taskNo.trim();
    }

    /**
     * 获取入库呼叫策略code
     *
     * @return stra_code - 入库呼叫策略code
     */
    public String getStraCode() {
        return straCode;
    }

    /**
     * 设置入库呼叫策略code
     *
     * @param straCode 入库呼叫策略code
     */
    public void setStraCode(String straCode) {
        this.straCode = straCode == null ? null : straCode.trim();
    }

    /**
     * 获取策略参数
     *
     * @return stra_param - 策略参数
     */
    public String getStraParam() {
        return straParam;
    }

    /**
     * 设置策略参数
     *
     * @param straParam 策略参数
     */
    public void setStraParam(String straParam) {
        this.straParam = straParam == null ? null : straParam.trim();
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