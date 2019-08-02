package com.wisdom.iwcs.domain.task.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 健康检查结果返回类
 * @author han
 */
@Getter
@Setter
@ToString
public class HealthCheckResult {
    /**
     * 具体的告警信息
     */
    private String warnContent;
    /**
     * 告警源
     */
    private String warnSource;
    /**
     * 告警时间
     */
    private Date warnTime;
    /**
     * 检查结果 false代表异常
     */
    private Boolean checkResult;
    /**
     * 检查结果码
     */
    private Boolean checkCode;
    /**
     * 告警设备编号
     */
    private String robotCode;
    /**
     * 告警开始时间
     */
    private String beginTime;
    /**
     * 产生告警时 AGV 执行的任务号
     */
    private String taskCode;
    /**
     * 产生告警的 X 坐标
     */
    private String alarm_x;
    /**
     * 产生告警的 Y 坐标
     */
    private String alarm_y;

}
