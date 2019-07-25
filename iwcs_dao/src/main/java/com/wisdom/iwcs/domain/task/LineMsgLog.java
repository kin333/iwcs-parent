package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "line_msg_log")
public class LineMsgLog {
    @Id
    private Integer id;

    /**
     * 通信类型：收receive/发send
     */
    @Column(name = "msg_type")
    private String msgType;

    /**
     * 发送/接收时间
     */
    @Column(name = "msg_time")
    private Date msgTime;

    /**
     * 请求编号
     */
    @Column(name = "req_code")
    private String reqCode;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 消息内容
     */
    @Column(name = "msg_body")
    private String msgBody;

    /**
     * 发送地址
     */
    @Column(name = "send_addr")
    private String sendAddr;

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
     * 获取通信类型：收receive/发send
     *
     * @return msg_type - 通信类型：收receive/发send
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 设置通信类型：收receive/发send
     *
     * @param msgType 通信类型：收receive/发send
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    /**
     * 获取发送/接收时间
     *
     * @return msg_time - 发送/接收时间
     */
    public Date getMsgTime() {
        return msgTime;
    }

    /**
     * 设置发送/接收时间
     *
     * @param msgTime 发送/接收时间
     */
    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    /**
     * 获取请求编号
     *
     * @return req_code - 请求编号
     */
    public String getReqCode() {
        return reqCode;
    }

    /**
     * 设置请求编号
     *
     * @param reqCode 请求编号
     */
    public void setReqCode(String reqCode) {
        this.reqCode = reqCode == null ? null : reqCode.trim();
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
     * 获取消息内容
     *
     * @return msg_body - 消息内容
     */
    public String getMsgBody() {
        return msgBody;
    }

    /**
     * 设置消息内容
     *
     * @param msgBody 消息内容
     */
    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody == null ? null : msgBody.trim();
    }

    /**
     * 获取发送地址
     *
     * @return send_addr - 发送地址
     */
    public String getSendAddr() {
        return sendAddr;
    }

    /**
     * 设置发送地址
     *
     * @param sendAddr 发送地址
     */
    public void setSendAddr(String sendAddr) {
        this.sendAddr = sendAddr == null ? null : sendAddr.trim();
    }
}