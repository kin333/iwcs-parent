package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_sub_task_conditions")
public class SubTaskCondition {
    @Id
    private Long id;

    /**
     * 子任务编号
     */
    @Column(name = "sub_task_num")
    private String subTaskNum;

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
     *条件状态：0-不符合,1已符合
     */
    @Column(name = "condition_met_status")
    private String conditionMetStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 前置条件:pre，后置条件:afte
     */
    @Column(name = "conditon_triger")
    private String conditonTriger;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubTaskNum() {
        return subTaskNum;
    }

    public void setSubTaskNum(String subTaskNum) {
        this.subTaskNum = subTaskNum == null ? null : subTaskNum.trim();
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

    public String getConditionMetStatus() {
        return conditionMetStatus;
    }

    public void setConditionMetStatus(String conditionMetStatus) {
        this.conditionMetStatus = conditionMetStatus == null ? null : conditionMetStatus.trim();
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
