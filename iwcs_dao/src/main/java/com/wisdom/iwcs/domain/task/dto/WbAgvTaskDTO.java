package com.wisdom.iwcs.domain.task.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "wb_agv_task")
public class WbAgvTaskDTO {
    @Id
    private Integer id;

    /**
     * 工作台任务编号：自动生成唯一任务编号，
     */
    @Column(name = "task_no")
    private String taskNo;

    /**
     * 工作台编号
     */
    @Column(name = "wb_code")
    private String wbCode;

    /**
     * 任务类型，0出库，1入库，2盘点
     */
    @Column(name = "task_type")
    private String taskType;

    /**
     * 任务状态，0正在执行，9已完成
     */
    @Column(name = "task_status")
    private String taskStatus;

    /**
     * 任务结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * AGV旋转属性，到达工作台后AGV的动作
     */
    @Column(name = "agv_action_rotate_type")
    private String agvActionRotateType;

    /**
     * 到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     */
    @Column(name = "agv_lift_status")
    private String agvLiftStatus;

    /**
     * 默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     */
    @Column(name = "agv_return_pod_type")
    private String agvReturnPodType;

    /**
     * 业务参数1
     */
    @Column(name = "biz_item1")
    private String bizItem1;

    /**
     * 业务参数2
     */
    @Column(name = "biz_item2")
    private String bizItem2;

    /**
     * 业务参数3
     */
    @Column(name = "biz_item3")
    private String bizItem3;

    /**
     * 呼叫客户端类型，0手持，1PC端，2上游系统
     */
    @Column(name = "src_client_type")
    private String srcClientType;

    /**
     * 呼叫编号,上游系统指定
     */
    @Column(name = "src_req_code")
    private String srcReqCode;

    /**
     * 呼叫客户端编号，手持设备编号等
     */
    @Column(name = "src_client_code")
    private String srcClientCode;

    /**
     * 呼叫用户编号，登陆用户等
     */
    @Column(name = "src_user_code")
    private String srcUserCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务参数4
     */
    @Column(name = "biz_item4")
    private String bizItem4;

    /**
     * 业务参数5
     */
    @Column(name = "biz_item5")
    private String bizItem5;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 最后修改人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 上游未按照订单号呼叫时，IWCS自动生成虚拟单据，虚拟单据号与任务绑定，后续操作时追踪单号用
     */
    @Column(name = "biz_order_code")
    private String bizOrderCode;

    /**
     * 入库确认后是否循环补充，0：否，1：循环
     */
    @Column(name = "cycletp")
    private String cycletp;

    public String getBizOrderCode() {
        return bizOrderCode;
    }

    public void setBizOrderCode(String bizOrderCode) {
        this.bizOrderCode = bizOrderCode;
    }

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
     * 获取工作台任务编号：自动生成唯一任务编号，
     *
     * @return task_no - 工作台任务编号：自动生成唯一任务编号，
     */
    public String getTaskNo() {
        return taskNo;
    }

    /**
     * 设置工作台任务编号：自动生成唯一任务编号，
     *
     * @param taskNo 工作台任务编号：自动生成唯一任务编号，
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo == null ? null : taskNo.trim();
    }

    /**
     * 获取工作台编号
     *
     * @return wb_code - 工作台编号
     */
    public String getWbCode() {
        return wbCode;
    }

    /**
     * 设置工作台编号
     *
     * @param wbCode 工作台编号
     */
    public void setWbCode(String wbCode) {
        this.wbCode = wbCode == null ? null : wbCode.trim();
    }

    /**
     * 获取任务类型，0出库，1入库，2盘点
     *
     * @return task_type - 任务类型，0出库，1入库，2盘点
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * 设置任务类型，0出库，1入库，2盘点
     *
     * @param taskType 任务类型，0出库，1入库，2盘点
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    /**
     * 获取任务状态，0正在执行，9已完成
     *
     * @return task_status - 任务状态，0正在执行，9已完成
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务状态，0正在执行，9已完成
     *
     * @param taskStatus 任务状态，0正在执行，9已完成
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    /**
     * 获取任务结束时间
     *
     * @return end_time - 任务结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置任务结束时间
     *
     * @param endTime 任务结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取AGV旋转属性，到达工作台后AGV的动作
     *
     * @return agv_action_rotate_type - AGV旋转属性，到达工作台后AGV的动作
     */
    public String getAgvActionRotateType() {
        return agvActionRotateType;
    }

    /**
     * 设置AGV旋转属性，到达工作台后AGV的动作
     *
     * @param agvActionRotateType AGV旋转属性，到达工作台后AGV的动作
     */
    public void setAgvActionRotateType(String agvActionRotateType) {
        this.agvActionRotateType = agvActionRotateType == null ? null : agvActionRotateType.trim();
    }

    /**
     * 获取到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     *
     * @return agv_lift_status - 到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     */
    public String getAgvLiftStatus() {
        return agvLiftStatus;
    }

    /**
     * 设置到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     *
     * @param agvLiftStatus 到达目的地后是否举升着 举升-0 放下-1 放下释放小车-2
     */
    public void setAgvLiftStatus(String agvLiftStatus) {
        this.agvLiftStatus = agvLiftStatus == null ? null : agvLiftStatus.trim();
    }

    /**
     * 获取默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     *
     * @return agv_return_pod_type - 默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     */
    public String getAgvReturnPodType() {
        return agvReturnPodType;
    }

    /**
     * 设置默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     *
     * @param agvReturnPodType 默认为空：货架回库　任务类型：1－初始化入库　2－循环入库回库　3－理货业务回库　4－自动化工作台回库（货架放下）
     */
    public void setAgvReturnPodType(String agvReturnPodType) {
        this.agvReturnPodType = agvReturnPodType == null ? null : agvReturnPodType.trim();
    }

    /**
     * 获取业务参数1
     *
     * @return biz_item1 - 业务参数1
     */
    public String getBizItem1() {
        return bizItem1;
    }

    /**
     * 设置业务参数1
     *
     * @param bizItem1 业务参数1
     */
    public void setBizItem1(String bizItem1) {
        this.bizItem1 = bizItem1 == null ? null : bizItem1.trim();
    }

    /**
     * 获取业务参数2
     *
     * @return biz_item2 - 业务参数2
     */
    public String getBizItem2() {
        return bizItem2;
    }

    /**
     * 设置业务参数2
     *
     * @param bizItem2 业务参数2
     */
    public void setBizItem2(String bizItem2) {
        this.bizItem2 = bizItem2 == null ? null : bizItem2.trim();
    }

    /**
     * 获取业务参数3
     *
     * @return biz_item3 - 业务参数3
     */
    public String getBizItem3() {
        return bizItem3;
    }

    /**
     * 设置业务参数3
     *
     * @param bizItem3 业务参数3
     */
    public void setBizItem3(String bizItem3) {
        this.bizItem3 = bizItem3 == null ? null : bizItem3.trim();
    }

    /**
     * 获取呼叫客户端类型，0手持，1PC端，2上游系统
     *
     * @return src_client_type - 呼叫客户端类型，0手持，1PC端，2上游系统
     */
    public String getSrcClientType() {
        return srcClientType;
    }

    /**
     * 设置呼叫客户端类型，0手持，1PC端，2上游系统
     *
     * @param srcClientType 呼叫客户端类型，0手持，1PC端，2上游系统
     */
    public void setSrcClientType(String srcClientType) {
        this.srcClientType = srcClientType == null ? null : srcClientType.trim();
    }

    /**
     * 获取呼叫编号,上游系统指定
     *
     * @return src_req_code - 呼叫编号,上游系统指定
     */
    public String getSrcReqCode() {
        return srcReqCode;
    }

    /**
     * 设置呼叫编号,上游系统指定
     *
     * @param srcReqCode 呼叫编号,上游系统指定
     */
    public void setSrcReqCode(String srcReqCode) {
        this.srcReqCode = srcReqCode == null ? null : srcReqCode.trim();
    }

    /**
     * 获取呼叫客户端编号，手持设备编号等
     *
     * @return src_client_code - 呼叫客户端编号，手持设备编号等
     */
    public String getSrcClientCode() {
        return srcClientCode;
    }

    /**
     * 设置呼叫客户端编号，手持设备编号等
     *
     * @param srcClientCode 呼叫客户端编号，手持设备编号等
     */
    public void setSrcClientCode(String srcClientCode) {
        this.srcClientCode = srcClientCode == null ? null : srcClientCode.trim();
    }

    /**
     * 获取呼叫用户编号，登陆用户等
     *
     * @return src_user_code - 呼叫用户编号，登陆用户等
     */
    public String getSrcUserCode() {
        return srcUserCode;
    }

    /**
     * 设置呼叫用户编号，登陆用户等
     *
     * @param srcUserCode 呼叫用户编号，登陆用户等
     */
    public void setSrcUserCode(String srcUserCode) {
        this.srcUserCode = srcUserCode == null ? null : srcUserCode.trim();
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
     * 获取业务参数4
     *
     * @return biz_item4 - 业务参数4
     */
    public String getBizItem4() {
        return bizItem4;
    }

    /**
     * 设置业务参数4
     *
     * @param bizItem4 业务参数4
     */
    public void setBizItem4(String bizItem4) {
        this.bizItem4 = bizItem4 == null ? null : bizItem4.trim();
    }

    /**
     * 获取业务参数5
     *
     * @return biz_item5 - 业务参数5
     */
    public String getBizItem5() {
        return bizItem5;
    }

    /**
     * 设置业务参数5
     *
     * @param bizItem5 业务参数5
     */
    public void setBizItem5(String bizItem5) {
        this.bizItem5 = bizItem5 == null ? null : bizItem5.trim();
    }


    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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
     * 获取最后修改人
     *
     * @return last_modified_by - 最后修改人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置最后修改人
     *
     * @param lastModifiedBy 最后修改人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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

    public String getCycletp() {
        return cycletp;
    }

    public void setCycletp(String cycletp) {
        this.cycletp = cycletp;
    }
}