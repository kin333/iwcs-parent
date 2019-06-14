package com.wisdom.iwcs.domain.notice.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "notice_user")
public class NoticeUserDto {
    /**
     * 主键
     */
    @Id
    private Integer id;


    /**
     * 通知Id
     */
    @Column(name = "notice_id")
    private Integer noticeId;

    /**
     * 发送者Id
     */
    @Column(name = "send_id")
    private Integer sendId;

    /**
     * 接收者id
     */
    @Column(name = "receive_id")
    private Integer receiveId;

    /**
     * 状态 0 未读 1 已读
     */
    private Integer status;

    /**
     * 是否拉入回收站
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 阅读时间
     */
    @Column(name = "read_time")
    private Date readTime;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getSendId() {
        return sendId;
    }


    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }


    public Integer getReceiveId() {
        return receiveId;
    }


    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getDeleteFlag() {
        return deleteFlag;
    }


    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }


    public Date getCreatedTime() {
        return createdTime;
    }


    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public Date getReadTime() {
        return readTime;
    }


    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }
}