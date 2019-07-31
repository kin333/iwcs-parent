package com.wisdom.iwcs.domain.task.dto;

import com.github.crab2died.annotation.ExcelField;
import com.github.crab2died.converter.WriteConvertible;
import com.wisdom.iwcs.domain.base.dto.MainTaskDTOChangeConverter.ChangeMainTaskTypeCodeConverter;
import com.wisdom.iwcs.domain.base.dto.MainTaskDTOChangeConverter.ChangeTaskStatusConverter;
import com.wisdom.iwcs.domain.base.dto.PodDetailCreatTimeDateConverter;
import com.wisdom.iwcs.domain.base.dto.SubTaskDTOChangeConverter.ChangeSendStatusConverter;
import com.wisdom.iwcs.domain.base.dto.SubTaskDTOChangeConverter.ChangeSubTaksTypConverter;
import com.wisdom.iwcs.domain.base.dto.SubTaskDTOChangeConverter.ChangeSubTaskBizPropConverter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_sub_task")
public class SubTaskDTO {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 子任务编号
     */
    @Column(name = "sub_task_num")
    @ExcelField(title = "子任务编号",order =1)
    private String subTaskNum;

    /**
     * 子任务类型
     */
    @Column(name = "sub_task_typ")
    @ExcelField(title = "子任务类型",order =2,writeConverter= ChangeSubTaksTypConverter.class)
    private String subTaskTyp;

    /**
     * 子任务执行顺序
     */
    @Column(name = "sub_task_seq")
    @ExcelField(title = "子任务执行顺序",order =3)
    private Integer subTaskSeq;

    /**
     * 主任务编号
     */
    @Column(name = "main_task_num")
    @ExcelField(title = "主任务编号",order =4)
    private String mainTaskNum;

    /**
     * 主任务执行顺序
     */
    @Column(name = "main_task_seq")
    @ExcelField(title = "主任务执行顺序",order =5)
    private Integer mainTaskSeq;

    /**
     * 任务类型（主任务）
     */
    @Column(name = "main_task_type")
    @ExcelField(title = "主任务类型",order =6,writeConverter= ChangeMainTaskTypeCodeConverter.class)
    private String mainTaskType;

    /**
     * 任务组/子任务
     */
    @Column(name = "group_flag")
    @ExcelField(title = "任务组/子任务",order =7)
    private String groupFlag;

    /**
     * 任务组编号
     */
    @Column(name = "task_group_code")
    @ExcelField(title = "任务组编号",order =12)
    private String taskGroupCode;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @ExcelField(title = "创建时间",order =17,writeConverter= PodDetailCreatTimeDateConverter.class)
    private Date createDate;

    /**
     * 任务消息体
     */
    @Column(name = "task_msg")
    private String taskMsg;

    /**
     * 下发状态
     */
    @Column(name = "send_status")
    @ExcelField(title = "下发状态",order =11,writeConverter= ChangeSendStatusConverter.class)
    private String sendStatus;

    /**
     * 备注
     */
    @ExcelField(title = "备注",order =18)
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
     * 执行顺序
     */
    private Integer sequence;

    /**
     * 取消标志
     */
    @Column(name = "cancel_flag")
    private Integer cancelFlag;

    /**
     * 取消id
     */
    @Column(name = "cancel_id")
    private Integer cancelId;

    /**
     * 取消类型
     */
    @Column(name = "cancel_type")
    private String cancelType;

    /**
     * 更新时间
     */
    @Column(name = "date_chg")
    private String dateChg;

    /**
     * 组id
     */
    @Column(name = "group_id")
    private String groupId;

    /**
     * 循环执行
     */
    @Column(name = "loop_exec")
    private String loopExec;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 任务状态
     */
    @Column(name = "task_status")
    @ExcelField(title = "任务状态",order =8,writeConverter= ChangeTaskStatusConverter.class)
    private String taskStatus;

    /**
     * 地图编码
     */
    @Column(name = "map_code")
    @ExcelField(title = "地图编码",order =13)
    private String mapCode;
    /**
     * 货架号
     */
    @Column(name = "pod_code")
    @ExcelField(title = "货架号",order =14)
    private String podCode;
    /**
     * 货架方向
     */
    @Column(name = "pod_dir")
    private String podDir;
    /**
     * 任务起始码
     */
    @Column(name = "start_bercode")
    @ExcelField(title = "起始地码",order =9)
    private String startBercode;

    @Column(name = "start_x")
    private double start_x;

    @Column(name = "start_y")
    private double start_y;

    /**
     * 任务终点码
     */
    @Column(name = "end_bercode")
    @ExcelField(title = "终点地码",order =10)
    private String endBercode;

    @Column(name = "end_x")
    private double end_x;

    @Column(name = "end_y")
    private double end_y;

    /**
     * 结束信息，记录
     */
    @Column(name = "end_remark")
    private String endRemark;

    /**
     * 执行任务id
     */
    @Column(name = "worker_task_code")
    private String workerTaskCode;

    /**
     * 库区编号
     */
    @Column(name = "area_code")
    @ExcelField(title = "库区编号",order =16)
    private String areaCode;

    /**
     * 子任务单业务属性
     */
    @Column(name = "sub_task_biz_prop")
    @ExcelField(title = "子任务单业务属性",order =15,writeConverter= ChangeSubTaskBizPropConverter.class)
    private String subTaskBizProp;

    /**
     * 执行机器人编号
     */
    @Column(name = "robot_code")
    private String robotCode;

    /**
     * 实际工作状态
     */
    @Column(name = "work_task_status")
    private String workTaskStatus;

    /**
     * 实际任务开始时间
     */
    @Column(name = "task_start_time")
    private Date taskStartTime;
    /**
     * 实际任务离开储位时间
     */
    @Column(name = "task_leave_time")
    private Date taskLeaveTime;
    /**
     * 实际任务结束时间
     */
    @Column(name = "task_end_time")
    private Date taskEndTime;

    /**
     * 任务下发时间
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 起始点点位别名(冗余字段)
     */
    @Column(name = "start_alias")
    private String startAlias;
    /**
     * 终点点位别名(冗余字段)
     */
    @Column(name = "end_alias")
    private String endAlias;

    public String getStartAlias() {
        return startAlias;
    }

    public void setStartAlias(String startAlias) {
        this.startAlias = startAlias;
    }

    public String getEndAlias() {
        return endAlias;
    }

    public void setEndAlias(String endAlias) {
        this.endAlias = endAlias;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskLeaveTime() {
        return taskLeaveTime;
    }

    public void setTaskLeaveTime(Date taskLeaveTime) {
        this.taskLeaveTime = taskLeaveTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getWorkTaskStatus() {
        return workTaskStatus;
    }

    public void setWorkTaskStatus(String workTaskStatus) {
        this.workTaskStatus = workTaskStatus;
    }


    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }

    public String getSubTaskBizProp() {
        return subTaskBizProp;
    }

    public void setSubTaskBizProp(String subTaskBizProp) {
        this.subTaskBizProp = subTaskBizProp;
    }

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
      return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
      this.id = id;
    }

    /**
     * 获取子任务编号
     *
     * @return sub_task_num - 子任务编号
     */
    public String getSubTaskNum() {
      return subTaskNum;
    }

    /**
     * 设置子任务编号
     *
     * @param subTaskNum 子任务编号
     */
    public void setSubTaskNum(String subTaskNum) {
      this.subTaskNum = subTaskNum == null ? null : subTaskNum.trim();
    }

    /**
     * 获取子任务类型
     *
     * @return sub_task_typ - 子任务类型
     */
    public String getSubTaskTyp() {
      return subTaskTyp;
    }

    /**
     * 设置子任务类型
     *
     * @param subTaskTyp 子任务类型
     */
    public void setSubTaskTyp(String subTaskTyp) {
      this.subTaskTyp = subTaskTyp == null ? null : subTaskTyp.trim();
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
     * 获取任务类型（主任务）
     *
     * @return main_task_type - 任务类型（主任务）
     */
    public String getMainTaskType() {
      return mainTaskType;
    }

    /**
     * 设置任务类型（主任务）
     *
     * @param mainTaskType 任务类型（主任务）
     */
    public void setMainTaskType(String mainTaskType) {
      this.mainTaskType = mainTaskType == null ? null : mainTaskType.trim();
    }

    /**
     * 获取任务组/子任务
     *
     * @return group_flag - 任务组/子任务
     */
    public String getGroupFlag() {
      return groupFlag;
    }

    /**
     * 设置任务组/子任务
     *
     * @param groupFlag 任务组/子任务
     */
    public void setGroupFlag(String groupFlag) {
      this.groupFlag = groupFlag == null ? null : groupFlag.trim();
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
     * 获取任务消息体
     *
     * @return task_msg - 任务消息体
     */
    public String getTaskMsg() {
      return taskMsg;
    }

    /**
     * 设置任务消息体
     *
     * @param taskMsg 任务消息体
     */
    public void setTaskMsg(String taskMsg) {
      this.taskMsg = taskMsg == null ? null : taskMsg.trim();
    }

    /**
     * 获取下发状态
     *
     * @return send_status - 下发状态
     */
    public String getSendStatus() {
      return sendStatus;
    }

    /**
     * 设置下发状态
     *
     * @param sendStatus 下发状态
     */
    public void setSendStatus(String sendStatus) {
      this.sendStatus = sendStatus == null ? null : sendStatus.trim();
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

    /**
     * 获取执行顺序
     *
     * @return sequence - 执行顺序
     */
    public Integer getSequence() {
      return sequence;
    }

    /**
     * 设置执行顺序
     *
     * @param sequence 执行顺序
     */
    public void setSequence(Integer sequence) {
      this.sequence = sequence;
    }

    /**
     * 获取取消标志
     *
     * @return cancel_flag - 取消标志
     */
    public Integer getCancelFlag() {
      return cancelFlag;
    }

    /**
     * 设置取消标志
     *
     * @param cancelFlag 取消标志
     */
    public void setCancelFlag(Integer cancelFlag) {
      this.cancelFlag = cancelFlag;
    }

    /**
     * 获取取消id
     *
     * @return cancel_id - 取消id
     */
    public Integer getCancelId() {
      return cancelId;
    }

    /**
     * 设置取消id
     *
     * @param cancelId 取消id
     */
    public void setCancelId(Integer cancelId) {
      this.cancelId = cancelId;
    }

    /**
     * 获取取消类型
     *
     * @return cancel_type - 取消类型
     */
    public String getCancelType() {
      return cancelType;
    }

    /**
     * 设置取消类型
     *
     * @param cancelType 取消类型
     */
    public void setCancelType(String cancelType) {
      this.cancelType = cancelType == null ? null : cancelType.trim();
    }

    /**
     * 获取更新时间
     *
     * @return date_chg - 更新时间
     */
    public String getDateChg() {
      return dateChg;
    }

    /**
     * 设置更新时间
     *
     * @param dateChg 更新时间
     */
    public void setDateChg(String dateChg) {
      this.dateChg = dateChg == null ? null : dateChg.trim();
    }

    /**
     * 获取组id
     *
     * @return group_id - 组id
     */
    public String getGroupId() {
      return groupId;
    }

    /**
     * 设置组id
     *
     * @param groupId 组id
     */
    public void setGroupId(String groupId) {
      this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 获取循环执行
     *
     * @return loop_exec - 循环执行
     */
    public String getLoopExec() {
      return loopExec;
    }

    /**
     * 设置循环执行
     *
     * @param loopExec 循环执行
     */
    public void setLoopExec(String loopExec) {
      this.loopExec = loopExec == null ? null : loopExec.trim();
    }

    /**
     * 获取优先级
     *
     * @return priority - 优先级
     */
    public Integer getPriority() {
      return priority;
    }

    /**
     * 设置优先级
     *
     * @param priority 优先级
     */
    public void setPriority(Integer priority) {
      this.priority = priority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodDir() {
        return podDir;
    }

    public void setPodDir(String podDir) {
        this.podDir = podDir;
    }

    public String getStartBercode() {
        return startBercode;
    }

    public void setStartBercode(String startBercode) {
        this.startBercode = startBercode;
    }

    public double getStart_x() {
        return start_x;
    }

    public void setStart_x(double start_x) {
        this.start_x = start_x;
    }

    public double getStart_y() {
        return start_y;
    }

    public void setStart_y(double start_y) {
        this.start_y = start_y;
    }

    public String getEndBercode() {
        return endBercode;
    }

    public void setEndBercode(String endBercode) {
        this.endBercode = endBercode;
    }

    public double getEnd_x() {
        return end_x;
    }

    public void setEnd_x(double end_x) {
        this.end_x = end_x;
    }

    public double getEnd_y() {
        return end_y;
    }

    public void setEnd_y(double end_y) {
        this.end_y = end_y;
    }

    public String getEndRemark() {
        return endRemark;
    }

    public void setEndRemark(String endRemark) {
        this.endRemark = endRemark;
    }

    public String getWorkerTaskCode() {
        return workerTaskCode;
    }

    public void setWorkerTaskCode(String workerTaskCode) {
        this.workerTaskCode = workerTaskCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
