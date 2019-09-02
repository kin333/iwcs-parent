package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "base_msg_send")
public class BaseMsgSend {
    @Id
    private Integer id;

    /**
     * 请求编号
     */
    @Column(name = "req_code")
    private String reqCode;

    /**
     * 更新时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 方法名
     */
    private String method;

    /**
     * 消息来源ip
     */
    @Column(name = "msg_from")
    private String msgFrom;

    /**
     * 消息类型
     */
    @Column(name = "msg_type")
    private String msgType;

    /**
     * 当前任务单号
     */
    @Column(name = "rcpt_status")
    private String rcptStatus;

    /**
     * 请求消息
     */
    @Column(name = "req_msg")
    private String reqMsg;

    /**
     * 请求类型
     */
    @Column(name = "req_type")
    private String reqType;

    /**
     * 发送状态
     */
    @Column(name = "send_status")
    private String sendStatus;

    /**
     * 任务编号
     */
    @Column(name = "task_code")
    private String taskCode;

    /**
     * 第三方引用类型
     */
    @Column(name = "third_invoke_type")
    private String thirdInvokeType;

    /**
     * 发送消息地址
     */
    private String url;

    /**
     * 发送消息内容
     */
    @Column(name = "send_msg")
    private String sendMsg;

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
     * 获取更新时间
     *
     * @return last_modified_time - 更新时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置更新时间
     *
     * @param lastModifiedTime 更新时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
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
     * 获取方法名
     *
     * @return method - 方法名
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置方法名
     *
     * @param method 方法名
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 获取消息来源ip
     *
     * @return msg_from - 消息来源ip
     */
    public String getMsgFrom() {
        return msgFrom;
    }

    /**
     * 设置消息来源ip
     *
     * @param msgFrom 消息来源ip
     */
    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom == null ? null : msgFrom.trim();
    }

    /**
     * 获取消息类型
     *
     * @return msg_type - 消息类型
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 设置消息类型
     *
     * @param msgType 消息类型
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    /**
     * 获取当前任务单号
     *
     * @return rcpt_status - 当前任务单号
     */
    public String getRcptStatus() {
        return rcptStatus;
    }

    /**
     * 设置当前任务单号
     *
     * @param rcptStatus 当前任务单号
     */
    public void setRcptStatus(String rcptStatus) {
        this.rcptStatus = rcptStatus == null ? null : rcptStatus.trim();
    }

    /**
     * 获取请求消息
     *
     * @return req_msg - 请求消息
     */
    public String getReqMsg() {
        return reqMsg;
    }

    /**
     * 设置请求消息
     *
     * @param reqMsg 请求消息
     */
    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg == null ? null : reqMsg.trim();
    }

    /**
     * 获取请求类型
     *
     * @return req_type - 请求类型
     */
    public String getReqType() {
        return reqType;
    }

    /**
     * 设置请求类型
     *
     * @param reqType 请求类型
     */
    public void setReqType(String reqType) {
        this.reqType = reqType == null ? null : reqType.trim();
    }

    /**
     * 获取发送状态
     *
     * @return send_status - 发送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置发送状态
     *
     * @param sendStatus 发送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 获取任务编号
     *
     * @return task_code - 任务编号
     */
    public String getTaskCode() {
        return taskCode;
    }

    /**
     * 设置任务编号
     *
     * @param taskCode 任务编号
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    /**
     * 获取第三方引用类型
     *
     * @return third_invoke_type - 第三方引用类型
     */
    public String getThirdInvokeType() {
        return thirdInvokeType;
    }

    /**
     * 设置第三方引用类型
     *
     * @param thirdInvokeType 第三方引用类型
     */
    public void setThirdInvokeType(String thirdInvokeType) {
        this.thirdInvokeType = thirdInvokeType == null ? null : thirdInvokeType.trim();
    }

    /**
     * 获取发送消息地址
     *
     * @return url - 发送消息地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置发送消息地址
     *
     * @param url 发送消息地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取发送消息内容
     *
     * @return send_msg - 发送消息内容
     */
    public String getSendMsg() {
        return sendMsg;
    }

    /**
     * 设置发送消息内容
     *
     * @param sendMsg 发送消息内容
     */
    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg == null ? null : sendMsg.trim();
    }
}