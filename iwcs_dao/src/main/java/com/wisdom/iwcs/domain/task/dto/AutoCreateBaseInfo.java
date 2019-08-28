package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.iwcs.domain.task.TaskRel;
import lombok.Getter;
import lombok.Setter;

/**
 * 自动生成子任务时,获取数据策略的通用交互类
 */
@Getter
@Setter
public class AutoCreateBaseInfo {
    /**
     * 主任务号
     */
    private String mainTaskNum;
    /**
     * 策略参数
     */
    private String value;
    /**
     *
     */
    private TaskRel taskRel;

    public AutoCreateBaseInfo() {
    }

    public AutoCreateBaseInfo(String mainTaskNum, String value) {
        this.mainTaskNum = mainTaskNum;
        this.value = value;
    }

    public AutoCreateBaseInfo(String mainTaskNum, String value, TaskRel taskRel) {
        this.mainTaskNum = mainTaskNum;
        this.value = value;
        this.taskRel = taskRel;
    }
}
