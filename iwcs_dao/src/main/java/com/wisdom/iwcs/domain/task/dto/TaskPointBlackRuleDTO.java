package com.wisdom.iwcs.domain.task.dto;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_task_point_black_rule")
public class TaskPointBlackRuleDTO {
    @Id
    private Long id;

    /**
     * 起始点操作区域（老化区、检验区）
     */
    @Column(name = "start_operate_area")
    private String startOperateArea;

    /**
     * 起始点业务类型（如产线缓存区，作业区等）
     */
    @Column(name = "start_biz_type")
    private String startBizType;

    /**
     * 起始点业务次级区域（如产线作业区工作点、人工点等）
     */
    @Column(name = "start_biz_second_area")
    private String startBizSecondArea;

    /**
     * 目标点操作区域（老化区、检验区）
     */
    @Column(name = "target_operate_area")
    private String targetOperateArea;

    /**
     * 目标点业务类型（如产线缓存区，作业区等）
     */
    @Column(name = "target_biz_type")
    private String targetBizType;

    /**
     * 目标点业务次级区域（如产线作业区工作点、人工点等）
     */
    @Column(name = "target_biz_second_area")
    private String targetBizSecondArea;

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
     * 获取起始点操作区域（老化区、检验区）
     *
     * @return start_operate_area - 起始点操作区域（老化区、检验区）
     */
    public String getStartOperateArea() {
      return startOperateArea;
    }

    /**
     * 设置起始点操作区域（老化区、检验区）
     *
     * @param startOperateArea 起始点操作区域（老化区、检验区）
     */
    public void setStartOperateArea(String startOperateArea) {
      this.startOperateArea = startOperateArea == null ? null : startOperateArea.trim();
    }

    /**
     * 获取起始点业务类型（如产线缓存区，作业区等）
     *
     * @return start_biz_type - 起始点业务类型（如产线缓存区，作业区等）
     */
    public String getStartBizType() {
      return startBizType;
    }

    /**
     * 设置起始点业务类型（如产线缓存区，作业区等）
     *
     * @param startBizType 起始点业务类型（如产线缓存区，作业区等）
     */
    public void setStartBizType(String startBizType) {
      this.startBizType = startBizType == null ? null : startBizType.trim();
    }

    /**
     * 获取起始点业务次级区域（如产线作业区工作点、人工点等）
     *
     * @return start_biz_second_area - 起始点业务次级区域（如产线作业区工作点、人工点等）
     */
    public String getStartBizSecondArea() {
      return startBizSecondArea;
    }

    /**
     * 设置起始点业务次级区域（如产线作业区工作点、人工点等）
     *
     * @param startBizSecondArea 起始点业务次级区域（如产线作业区工作点、人工点等）
     */
    public void setStartBizSecondArea(String startBizSecondArea) {
      this.startBizSecondArea = startBizSecondArea == null ? null : startBizSecondArea.trim();
    }

    /**
     * 获取目标点操作区域（老化区、检验区）
     *
     * @return target_operate_area - 目标点操作区域（老化区、检验区）
     */
    public String getTargetOperateArea() {
      return targetOperateArea;
    }

    /**
     * 设置目标点操作区域（老化区、检验区）
     *
     * @param targetOperateArea 目标点操作区域（老化区、检验区）
     */
    public void setTargetOperateArea(String targetOperateArea) {
      this.targetOperateArea = targetOperateArea == null ? null : targetOperateArea.trim();
    }

    /**
     * 获取目标点业务类型（如产线缓存区，作业区等）
     *
     * @return target_biz_type - 目标点业务类型（如产线缓存区，作业区等）
     */
    public String getTargetBizType() {
      return targetBizType;
    }

    /**
     * 设置目标点业务类型（如产线缓存区，作业区等）
     *
     * @param targetBizType 目标点业务类型（如产线缓存区，作业区等）
     */
    public void setTargetBizType(String targetBizType) {
      this.targetBizType = targetBizType == null ? null : targetBizType.trim();
    }

    /**
     * 获取目标点业务次级区域（如产线作业区工作点、人工点等）
     *
     * @return target_biz_second_area - 目标点业务次级区域（如产线作业区工作点、人工点等）
     */
    public String getTargetBizSecondArea() {
      return targetBizSecondArea;
    }

    /**
     * 设置目标点业务次级区域（如产线作业区工作点、人工点等）
     *
     * @param targetBizSecondArea 目标点业务次级区域（如产线作业区工作点、人工点等）
     */
    public void setTargetBizSecondArea(String targetBizSecondArea) {
      this.targetBizSecondArea = targetBizSecondArea == null ? null : targetBizSecondArea.trim();
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
}
