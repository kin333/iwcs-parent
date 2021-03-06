package com.wisdom.iwcs.domain.notice;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Notice {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 通知类型 1 系统通知 2 个人通知
     */
    private Integer type;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 过期时间
     */
    @Column(name = "expiration_date")
    private Date expirationDate;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 是否删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "user_id")
    private Integer userId;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public Date getCreatedTime() {
        return createdTime;
    }


    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public Date getExpirationDate() {
        return expirationDate;
    }


    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


    public Integer getCreatedBy() {
        return createdBy;
    }


    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }


    public Integer getDeleteFlag() {
        return deleteFlag;
    }


    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}