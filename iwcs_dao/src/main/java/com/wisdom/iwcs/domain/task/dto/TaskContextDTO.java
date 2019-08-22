package com.wisdom.iwcs.domain.task.dto;

import java.util.Date;
import javax.persistence.*;

@Table(name = "task_context")
public class TaskContextDTO {
    @Id
    private Long id;

    /**
     * 主任务编号
     */
    @Column(name = "main_task_num")
    private String mainTaskNum;

    /**
     * 上下文json
     */
    private String context;

    /**
     * 版本号
     */
    private Integer version;

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
     * 获取主任务编号
     *
     * @return main_task_num - 主任务编号
     */
    public String getMainTaskNum() {
        return mainTaskNum;
    }

    /**
     * 设置主任务编号
     *
     * @param mainTaskNum 主任务编号
     */
    public void setMainTaskNum(String mainTaskNum) {
        this.mainTaskNum = mainTaskNum == null ? null : mainTaskNum.trim();
    }

    /**
     * 获取上下文json
     *
     * @return context - 上下文json
     */
    public String getContext() {
        return context;
    }

    /**
     * 设置上下文json
     *
     * @param context 上下文json
     */
    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
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