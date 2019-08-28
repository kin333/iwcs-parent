package com.wisdom.iwcs.domain.task.dto;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_task_rel")
public class TaskRelDTO {
    @Id
    private Long id;

    /**
     * 任务模板编号
     */
    @Column(name = "templ_code")
    private String templCode;

    /**
     * 主任务类型编号
     */
    @Column(name = "main_task_type_code")
    private String mainTaskTypeCode;

    /**
     * 主任务执行顺序
     */
    @Column(name = "main_task_seq")
    private Integer mainTaskSeq;

    /**
     * 子任务类型编号
     */
    @Column(name = "sub_task_type_code")
    private String subTaskTypeCode;

    /**
     * 任务组项目id
     */
    @Column(name = "task_group_item_id")
    private String taskGroupItemId;

    /**
     * 任务组编号
     */
    @Column(name = "task_group_code")
    private String taskGroupCode;

    /**
     * 子任务执行顺序
     */
    @Column(name = "sub_task_seq")
    private Integer subTaskSeq;

    /**
     * 任务组执行顺序
     */
    @Column(name = "group_seq")
    private Integer groupSeq;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 需要确认
     */
    @Column(name = "need_confirm")
    private String needConfirm;

    /**
     * 是否通知
     */
    @Column(name = "need_inform")
    private String needInform;

    /**
     * 是否需要触发
     */
    @Column(name = "need_trigger")
    private String needTrigger;

    /**
     * 通过什么字段触发任务：任务号、呼叫号、系统自动、设备号、条件触发
     */
    @Column(name = "trigger_type")
    private String triggerType;

    /**
     * 条件触发类型下必填，根据业务拓展
     */
    @Column(name = "condition_handler")
    private String conditionHandler;

    /**
     * sqop/rest/tcp
     */
    @Column(name = "third_invoke_type")
    private String thirdInvokeType;

    /**
     * 第三方(应用)类型
     */
    @Column(name = "third_type")
    private String thirdType;

    /**
     * 应用编号
     */
    @Column(name = "app_code")
    private String appCode;

    /**
     * 第3方路径
     */
    @Column(name = "third_url")
    private String thirdUrl;

    /**
     * 任务开始3方通知方法
     */
    @Column(name = "third_start_method")
    private String thirdStartMethod;

    /**
     * 任务结束3方通知方法
     */
    @Column(name = "third_end_method")
    private String thirdEndMethod;

    /**
     * 如：固定点、线路、呼叫路径、关联点、主叫号、策略配置、电梯等待位、关联区域
     */
    @Column(name = "point_access")
    private String pointAccess;

    /**
     * 流出子任务
     */
    private String outflow;

    /**
     * 任务动作类型 MOVE:移动任务;STAY:非移动任务
     */
    @Column(name = "action_type")
    private String actionType;

    /**
     * 起点获取方式
     */
    @Column(name = "start_point_access")
    private String startPointAccess;

    /**
     * 起点获取方式的value值
     */
    @Column(name = "start_point_access_value")
    private String startPointAccessValue;

    /**
     * 载具获取方式
     */
    @Column(name = "pod_access")
    private String podAccess;

    /**
     * 载具获取方式的value值
     */
    @Column(name = "pod_access_value")
    private String podAccessValue;

    /**
     * 终点获取方式
     */
    @Column(name = "end_point_access")
    private String endPointAccess;

    /**
     * 终点获取方式的value值
     */
    @Column(name = "end_point_access_value")
    private String endPointAccessValue;

    /**
     * 三方执行者获取方式
     */
    @Column(name = "robot_access")
    private String robotAccess;

    /**
     * 三方执行者获取方式的value值
     */
    @Column(name = "robot_access_value")
    private String robotAccessValue;

    /**
     * 三方执行id获取方式
     */
    @Column(name = "worker_task_code_access")
    private String workerTaskCodeAccess;

    /**
     * 三方执行id获取方式的value值
     */
    @Column(name = "worker_task_code_access_value")
    private String workerTaskCodeAccessValue;

    public String getOutflow() {
        return outflow;
    }

    public void setOutflow(String outflow) {
        this.outflow = outflow;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getStartPointAccess() {
        return startPointAccess;
    }

    public void setStartPointAccess(String startPointAccess) {
        this.startPointAccess = startPointAccess;
    }

    public String getStartPointAccessValue() {
        return startPointAccessValue;
    }

    public void setStartPointAccessValue(String startPointAccessValue) {
        this.startPointAccessValue = startPointAccessValue;
    }

    public String getPodAccess() {
        return podAccess;
    }

    public void setPodAccess(String podAccess) {
        this.podAccess = podAccess;
    }

    public String getPodAccessValue() {
        return podAccessValue;
    }

    public void setPodAccessValue(String podAccessValue) {
        this.podAccessValue = podAccessValue;
    }

    public String getEndPointAccess() {
        return endPointAccess;
    }

    public void setEndPointAccess(String endPointAccess) {
        this.endPointAccess = endPointAccess;
    }

    public String getEndPointAccessValue() {
        return endPointAccessValue;
    }

    public void setEndPointAccessValue(String endPointAccessValue) {
        this.endPointAccessValue = endPointAccessValue;
    }

    public String getRobotAccess() {
        return robotAccess;
    }

    public void setRobotAccess(String robotAccess) {
        this.robotAccess = robotAccess;
    }

    public String getRobotAccessValue() {
        return robotAccessValue;
    }

    public void setRobotAccessValue(String robotAccessValue) {
        this.robotAccessValue = robotAccessValue;
    }

    public String getWorkerTaskCodeAccess() {
        return workerTaskCodeAccess;
    }

    public void setWorkerTaskCodeAccess(String workerTaskCodeAccess) {
        this.workerTaskCodeAccess = workerTaskCodeAccess;
    }

    public String getWorkerTaskCodeAccessValue() {
        return workerTaskCodeAccessValue;
    }

    public void setWorkerTaskCodeAccessValue(String workerTaskCodeAccessValue) {
        this.workerTaskCodeAccessValue = workerTaskCodeAccessValue;
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
     * 获取任务模板编号
     *
     * @return templ_code - 任务模板编号
     */
    public String getTemplCode() {
      return templCode;
    }

    /**
     * 设置任务模板编号
     *
     * @param templCode 任务模板编号
     */
    public void setTemplCode(String templCode) {
      this.templCode = templCode == null ? null : templCode.trim();
    }

    /**
     * 获取主任务类型编号
     *
     * @return main_task_type_code - 主任务类型编号
     */
    public String getMainTaskTypeCode() {
      return mainTaskTypeCode;
    }

    /**
     * 设置主任务类型编号
     *
     * @param mainTaskTypeCode 主任务类型编号
     */
    public void setMainTaskTypeCode(String mainTaskTypeCode) {
      this.mainTaskTypeCode = mainTaskTypeCode == null ? null : mainTaskTypeCode.trim();
    }

    /**
     * 获取主任务执行顺序
     *
     * @return main_task_seq - 主任务执行顺序
     */
    public Integer getMainTaskSeq() {
      return mainTaskSeq;
    }

    /**
     * 设置主任务执行顺序
     *
     * @param mainTaskSeq 主任务执行顺序
     */
    public void setMainTaskSeq(Integer mainTaskSeq) {
      this.mainTaskSeq = mainTaskSeq;
    }

    /**
     * 获取子任务类型编号
     *
     * @return sub_task_type_code - 子任务类型编号
     */
    public String getSubTaskTypeCode() {
      return subTaskTypeCode;
    }

    /**
     * 设置子任务类型编号
     *
     * @param subTaskTypeCode 子任务类型编号
     */
    public void setSubTaskTypeCode(String subTaskTypeCode) {
      this.subTaskTypeCode = subTaskTypeCode == null ? null : subTaskTypeCode.trim();
    }

    /**
     * 获取任务组项目id
     *
     * @return task_group_item_id - 任务组项目id
     */
    public String getTaskGroupItemId() {
      return taskGroupItemId;
    }

    /**
     * 设置任务组项目id
     *
     * @param taskGroupItemId 任务组项目id
     */
    public void setTaskGroupItemId(String taskGroupItemId) {
      this.taskGroupItemId = taskGroupItemId == null ? null : taskGroupItemId.trim();
    }

    /**
     * 获取任务组编号
     *
     * @return task_group_code - 任务组编号
     */
    public String getTaskGroupCode() {
      return taskGroupCode;
    }

    /**
     * 设置任务组编号
     *
     * @param taskGroupCode 任务组编号
     */
    public void setTaskGroupCode(String taskGroupCode) {
      this.taskGroupCode = taskGroupCode == null ? null : taskGroupCode.trim();
    }

    /**
     * 获取子任务执行顺序
     *
     * @return sub_task_seq - 子任务执行顺序
     */
    public Integer getSubTaskSeq() {
      return subTaskSeq;
    }

    /**
     * 设置子任务执行顺序
     *
     * @param subTaskSeq 子任务执行顺序
     */
    public void setSubTaskSeq(Integer subTaskSeq) {
      this.subTaskSeq = subTaskSeq;
    }

    /**
     * 获取任务组执行顺序
     *
     * @return group_seq - 任务组执行顺序
     */
    public Integer getGroupSeq() {
      return groupSeq;
    }

    /**
     * 设置任务组执行顺序
     *
     * @param groupSeq 任务组执行顺序
     */
    public void setGroupSeq(Integer groupSeq) {
      this.groupSeq = groupSeq;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
      return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
      this.createDate = createDate;
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
     * 获取需要确认
     *
     * @return need_confirm - 需要确认
     */
    public String getNeedConfirm() {
      return needConfirm;
    }

    /**
     * 设置需要确认
     *
     * @param needConfirm 需要确认
     */
    public void setNeedConfirm(String needConfirm) {
      this.needConfirm = needConfirm == null ? null : needConfirm.trim();
    }

    /**
     * 获取是否通知
     *
     * @return need_inform - 是否通知
     */
    public String getNeedInform() {
      return needInform;
    }

    /**
     * 设置是否通知
     *
     * @param needInform 是否通知
     */
    public void setNeedInform(String needInform) {
      this.needInform = needInform == null ? null : needInform.trim();
    }

    /**
     * 获取是否需要触发
     *
     * @return need_trigger - 是否需要触发
     */
    public String getNeedTrigger() {
      return needTrigger;
    }

    /**
     * 设置是否需要触发
     *
     * @param needTrigger 是否需要触发
     */
    public void setNeedTrigger(String needTrigger) {
      this.needTrigger = needTrigger == null ? null : needTrigger.trim();
    }

    /**
     * 获取通过什么字段触发任务：任务号、呼叫号、系统自动、设备号、条件触发
     *
     * @return trigger_type - 通过什么字段触发任务：任务号、呼叫号、系统自动、设备号、条件触发
     */
    public String getTriggerType() {
      return triggerType;
    }

    /**
     * 设置通过什么字段触发任务：任务号、呼叫号、系统自动、设备号、条件触发
     *
     * @param triggerType 通过什么字段触发任务：任务号、呼叫号、系统自动、设备号、条件触发
     */
    public void setTriggerType(String triggerType) {
      this.triggerType = triggerType == null ? null : triggerType.trim();
    }

    /**
     * 获取条件触发类型下必填，根据业务拓展
     *
     * @return condition_handler - 条件触发类型下必填，根据业务拓展
     */
    public String getConditionHandler() {
      return conditionHandler;
    }

    /**
     * 设置条件触发类型下必填，根据业务拓展
     *
     * @param conditionHandler 条件触发类型下必填，根据业务拓展
     */
    public void setConditionHandler(String conditionHandler) {
      this.conditionHandler = conditionHandler == null ? null : conditionHandler.trim();
    }

    /**
     * 获取sqop/rest/tcp
     *
     * @return third_invoke_type - sqop/rest/tcp
     */
    public String getThirdInvokeType() {
      return thirdInvokeType;
    }

    /**
     * 设置sqop/rest/tcp
     *
     * @param thirdInvokeType sqop/rest/tcp
     */
    public void setThirdInvokeType(String thirdInvokeType) {
      this.thirdInvokeType = thirdInvokeType == null ? null : thirdInvokeType.trim();
    }

    /**
     * 获取第三方(应用)类型
     *
     * @return third_type - 第三方(应用)类型
     */
    public String getThirdType() {
      return thirdType;
    }

    /**
     * 设置第三方(应用)类型
     *
     * @param thirdType 第三方(应用)类型
     */
    public void setThirdType(String thirdType) {
      this.thirdType = thirdType == null ? null : thirdType.trim();
    }

    /**
     * 获取应用编号
     *
     * @return app_code - 应用编号
     */
    public String getAppCode() {
      return appCode;
    }

    /**
     * 设置应用编号
     *
     * @param appCode 应用编号
     */
    public void setAppCode(String appCode) {
      this.appCode = appCode == null ? null : appCode.trim();
    }

    /**
     * 获取第3方路径
     *
     * @return third_url - 第3方路径
     */
    public String getThirdUrl() {
      return thirdUrl;
    }

    /**
     * 设置第3方路径
     *
     * @param thirdUrl 第3方路径
     */
    public void setThirdUrl(String thirdUrl) {
      this.thirdUrl = thirdUrl == null ? null : thirdUrl.trim();
    }

    /**
     * 获取任务开始3方通知方法
     *
     * @return third_start_method - 任务开始3方通知方法
     */
    public String getThirdStartMethod() {
      return thirdStartMethod;
    }

    /**
     * 设置任务开始3方通知方法
     *
     * @param thirdStartMethod 任务开始3方通知方法
     */
    public void setThirdStartMethod(String thirdStartMethod) {
      this.thirdStartMethod = thirdStartMethod == null ? null : thirdStartMethod.trim();
    }

    /**
     * 获取任务结束3方通知方法
     *
     * @return third_end_method - 任务结束3方通知方法
     */
    public String getThirdEndMethod() {
      return thirdEndMethod;
    }

    /**
     * 设置任务结束3方通知方法
     *
     * @param thirdEndMethod 任务结束3方通知方法
     */
    public void setThirdEndMethod(String thirdEndMethod) {
      this.thirdEndMethod = thirdEndMethod == null ? null : thirdEndMethod.trim();
    }

    /**
     * 获取如：固定点、线路、呼叫路径、关联点、主叫号、策略配置、电梯等待位、关联区域
     *
     * @return point_access - 如：固定点、线路、呼叫路径、关联点、主叫号、策略配置、电梯等待位、关联区域
     */
    public String getPointAccess() {
      return pointAccess;
    }

    /**
     * 设置如：固定点、线路、呼叫路径、关联点、主叫号、策略配置、电梯等待位、关联区域
     *
     * @param pointAccess 如：固定点、线路、呼叫路径、关联点、主叫号、策略配置、电梯等待位、关联区域
     */
    public void setPointAccess(String pointAccess) {
      this.pointAccess = pointAccess == null ? null : pointAccess.trim();
    }
}
