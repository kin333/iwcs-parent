package com.wisdom.iwcs.domain.notice;

/**
 * Created by centling on 2018/5/29.
 */
public class NoticeRequestData {

    /**
     * 业务编码
     */
    private String code;

    /**
     * 发送者Id
     */
    private String userId;
    /**
     * 接收消息用户ID
     */
    private String receiveUserId;
    /**
     * 接收消息用户所在公司ID
     */
    private String receivecompanyId;
    /**
     * 类型 1 系统消息 2 用户操作消息
     */
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReceivecompanyId() {
        return receivecompanyId;
    }

    public void setReceivecompanyId(String receivecompanyId) {
        this.receivecompanyId = receivecompanyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
