package com.wisdom.iwcs.domain.base.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class MainTaskTypeAndAreaCode {
    /**
     * 主任务类型编号
     */
    @Column(name = "main_task_type_code")
    private String mainTaskTypeCode;

    /**
     * 主任务类型名称
     */
    @Column(name = "main_task_type_name")
    private String mainTaskTypeName;

    /**
     * 执行类
     */
    @Column(name = "exec_bean")
    private String execBean;

    /**
     * 任务优先级
     */
    private Integer priority;

    /**
     * 循环执行
     */
    @Column(name = "loop_exec")
    private String loopExec;

    /**
     * 循环间隔时间
     */
    @Column(name = "interval_time")
    private Integer intervalTime;

    /**
     * 目标物是否多任务
     */
    @Column(name = "multi_target_task")
    private String multiTargetTask;

    /**
     * 关联附任务模板
     */
    @Column(name = "rel_vice_task_typ")
    private String relViceTaskTyp;


    /**
     * 是否是系统默认模板
     */
    @Column(name = "is_default")
    private Integer isDefault;

    /**
     * 分解任务个数
     */
    @Column(name = "decomp_num")
    private Integer decompNum;

    private String areaCode;

}
