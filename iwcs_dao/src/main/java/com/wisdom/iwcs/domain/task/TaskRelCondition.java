package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_task_rel_condition")
public class TaskRelCondition {
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
     * 子任务编号
     */
    @Column(name = "sub_task_type_code")
    private String subTaskTypeCode;

    /**
     * 条件处理器
     */
    @Column(name = "conditon_handler")
    private String conditonHandler;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 如：固定点、线路、呼叫路径、关联点、主叫号、策略配置、电梯等待位、关联区域
     */
    @Column(name = "point_access")
    private String pointAccess;

    /**
     * 更新时间
     */
    @Column(name = "date_chg")
    private String dateChg;

    /**
     * 备注
     */
    private String remark;

    /**
     * 前置条件:pre，后置条件:after
     */
    @Column(name = "conditon_triger")
    private String conditonTriger;

    /**
     * 订阅事件
     */
    @Column(name = "subscribe_event")
    private String subscribeEvent;

    /**
     * 执行顺序
     */
    @Column(name = "sub_task_seq")
    private Integer subTaskSeq;

    public Integer getSubTaskSeq() {
        return subTaskSeq;
    }

    public void setSubTaskSeq(Integer subTaskSeq) {
        this.subTaskSeq = subTaskSeq;
    }

    public String getSubscribeEvent() {
        return subscribeEvent;
    }

    public void setSubscribeEvent(String subscribeEvent) {
        this.subscribeEvent = subscribeEvent;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplCode() {
        return templCode;
    }

    public void setTemplCode(String templCode) {
        this.templCode = templCode == null ? null : templCode.trim();
    }

    public String getMainTaskTypeCode() {
        return mainTaskTypeCode;
    }

    public void setMainTaskTypeCode(String mainTaskTypeCode) {
        this.mainTaskTypeCode = mainTaskTypeCode == null ? null : mainTaskTypeCode.trim();
    }

    public String getSubTaskTypeCode() {
        return subTaskTypeCode;
    }

    public void setSubTaskTypeCode(String subTaskTypeCode) {
        this.subTaskTypeCode = subTaskTypeCode == null ? null : subTaskTypeCode.trim();
    }

    public String getConditonHandler() {
        return conditonHandler;
    }

    public void setConditonHandler(String conditonHandler) {
        this.conditonHandler = conditonHandler == null ? null : conditonHandler.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPointAccess() {
        return pointAccess;
    }

    public void setPointAccess(String pointAccess) {
        this.pointAccess = pointAccess == null ? null : pointAccess.trim();
    }

    public String getDateChg() {
        return dateChg;
    }

    public void setDateChg(String dateChg) {
        this.dateChg = dateChg == null ? null : dateChg.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getConditonTriger() {
        return conditonTriger;
    }

    public void setConditonTriger(String conditonTriger) {
        this.conditonTriger = conditonTriger == null ? null : conditonTriger.trim();
    }
}
