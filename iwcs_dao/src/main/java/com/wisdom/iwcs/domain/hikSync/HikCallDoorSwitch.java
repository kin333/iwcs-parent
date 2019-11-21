package com.wisdom.iwcs.domain.hikSync;

import lombok.Getter;
import lombok.Setter;

/**
 * 小车请求开门关门参数
 * @author George
 */
@Getter
@Setter
public class HikCallDoorSwitch extends BaseHikRequest{
    /**
     * 任务标识
     */
    private String type;
    /**
     * 自动门
     */
    private String deviceType;
    /**
     * 任务号，唯一标识
     */
    private String uuid;
    /**
     * 设备编号，ECS添加的设备编号
     */
    private String deviceIndex;
    /**
     * applyLock 申请：自动门开门
     * releaseDevice释放：自动门关门
     */
    private String actionTask;
    /**
     * 起始楼层
     */
    private String src;
    /**
     * 终止楼层
     */
    private String des;

}
