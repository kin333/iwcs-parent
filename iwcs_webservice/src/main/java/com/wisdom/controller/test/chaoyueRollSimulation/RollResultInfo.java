package com.wisdom.controller.test.chaoyueRollSimulation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RollResultInfo {

    /**
     * 任务号
     */
    private String taskCode;
    /**
     * 空料箱回收上箱点
     */
    private String srcWbCode;
    /**
     * 空料箱数量
     */
    private Integer emptyRecyleNum;
    /**
     * 回收点
     */
    private String emptyRecyleWb;
    /**
     * 当前点位
     */
    private String currentWb;
    /**
     * 任务状态
     */
    private String taskSta;
}
