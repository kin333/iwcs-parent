package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_task_rel_action")
public class TaskRelAction {
    @Id
    private Long id;

    /**
     * 动作编号
     */
    @Column(name = "action_code")
    private String actionCode;

    /**
     * 动作中文名
     */
    @Column(name = "action_name")
    private String actionName;

    /**
     * 创建节点(AGV到达起点,AGV离开,AGV到达终点)
     */
    @Column(name = "create_node")
    private String createNode;

    /**
     * 模板编号
     */
    @Column(name = "templ_code")
    private String templCode;

    /**
     * 动作类型(notify:通知类动作)
     */
    @Column(name = "action_type")
    private String actionType;

    /**
     * 动作上下文(消息通知消息体模板)
     */
    private String content;

    /**
     * 通知地址
     */
    private String url;

    /**
     * 通知第三方
     */
    private String app;

    /**
     * 第三方调用类型(调用方式rest/soap/tcp)
     */
    @Column(name = "third_invoke_type")
    private String thirdInvokeType;

    /**
     * 执行模型,是否重复发送,1必达,0非必达
     */
    @Column(name = "execute_mode")
    private String executeMode;

    /**
     * 前置动作
     */
    @Column(name = "pre_actions")
    private String preActions;

    /**
     * 备注
     */
    private String remark;

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
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;
    /**
     * 请求返回值处理器
     */
    @Column(name = "response_handler")
    private String responseHandler;

    public String getResponseHandler() {
        return responseHandler;
    }

    public void setResponseHandler(String responseHandler) {
        this.responseHandler = responseHandler;
    }

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
     * 获取动作编号
     *
     * @return action_code - 动作编号
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * 设置动作编号
     *
     * @param actionCode 动作编号
     */
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode == null ? null : actionCode.trim();
    }

    /**
     * 获取动作中文名
     *
     * @return action_name - 动作中文名
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * 设置动作中文名
     *
     * @param actionName 动作中文名
     */
    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    /**
     * 获取创建节点(AGV到达起点,AGV离开,AGV到达终点)
     *
     * @return create_node - 创建节点(AGV到达起点,AGV离开,AGV到达终点)
     */
    public String getCreateNode() {
        return createNode;
    }

    /**
     * 设置创建节点(AGV到达起点,AGV离开,AGV到达终点)
     *
     * @param createNode 创建节点(AGV到达起点,AGV离开,AGV到达终点)
     */
    public void setCreateNode(String createNode) {
        this.createNode = createNode == null ? null : createNode.trim();
    }

    /**
     * 获取模板编号
     *
     * @return templ_code - 模板编号
     */
    public String getTemplCode() {
        return templCode;
    }

    /**
     * 设置模板编号
     *
     * @param templCode 模板编号
     */
    public void setTemplCode(String templCode) {
        this.templCode = templCode == null ? null : templCode.trim();
    }

    /**
     * 获取动作类型(notify:通知类动作)
     *
     * @return action_type - 动作类型(notify:通知类动作)
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * 设置动作类型(notify:通知类动作)
     *
     * @param actionType 动作类型(notify:通知类动作)
     */
    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    /**
     * 获取动作上下文(消息通知消息体模板)
     *
     * @return content - 动作上下文(消息通知消息体模板)
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置动作上下文(消息通知消息体模板)
     *
     * @param content 动作上下文(消息通知消息体模板)
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取通知地址
     *
     * @return url - 通知地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置通知地址
     *
     * @param url 通知地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取通知第三方
     *
     * @return app - 通知第三方
     */
    public String getApp() {
        return app;
    }

    /**
     * 设置通知第三方
     *
     * @param app 通知第三方
     */
    public void setApp(String app) {
        this.app = app == null ? null : app.trim();
    }

    /**
     * 获取第三方调用类型(调用方式rest/soap/tcp)
     *
     * @return third_invoke_type - 第三方调用类型(调用方式rest/soap/tcp)
     */
    public String getThirdInvokeType() {
        return thirdInvokeType;
    }

    /**
     * 设置第三方调用类型(调用方式rest/soap/tcp)
     *
     * @param thirdInvokeType 第三方调用类型(调用方式rest/soap/tcp)
     */
    public void setThirdInvokeType(String thirdInvokeType) {
        this.thirdInvokeType = thirdInvokeType == null ? null : thirdInvokeType.trim();
    }

    /**
     * 获取执行模型,是否重复发送,1必达,0非必达
     *
     * @return execute_mode - 执行模型,是否重复发送,1必达,0非必达
     */
    public String getExecuteMode() {
        return executeMode;
    }

    /**
     * 设置执行模型,是否重复发送,1必达,0非必达
     *
     * @param executeMode 执行模型,是否重复发送,1必达,0非必达
     */
    public void setExecuteMode(String executeMode) {
        this.executeMode = executeMode == null ? null : executeMode.trim();
    }

    /**
     * 获取前置动作
     *
     * @return pre_actions - 前置动作
     */
    public String getPreActions() {
        return preActions;
    }

    /**
     * 设置前置动作
     *
     * @param preActions 前置动作
     */
    public void setPreActions(String preActions) {
        this.preActions = preActions == null ? null : preActions.trim();
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

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
}