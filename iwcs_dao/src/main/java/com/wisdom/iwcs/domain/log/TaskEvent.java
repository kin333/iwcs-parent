package com.wisdom.iwcs.domain.log;

import lombok.Getter;
import lombok.Setter;

/**
 * 任务类事件信息类
 */
@Getter
@Setter
public class TaskEvent extends BaseQueueInfo {
    /**
     * 地图编码
     */
    private String mapCode;
    /**
     * 区域编码
     */
    private String berthType;
    /**
     * 任务子任务编号
     */
    private String subTaskNum;
    /**
     * 起始地码
     */
    private String startBerCode;
    /**
     * 终点地码
     */
    private String endBerCode;
    /**
     * 货架号
     */
    private String podCode;
}
