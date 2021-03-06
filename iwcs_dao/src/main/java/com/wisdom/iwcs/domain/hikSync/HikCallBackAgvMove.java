package com.wisdom.iwcs.domain.hikSync;

import lombok.Getter;
import lombok.Setter;

/**
 * 海康回调接口信息类
 * @author han
 *
 */
@Getter
@Setter
public class HikCallBackAgvMove {
    /**
     * 呼叫编码
     */
    private String callCode;

    /**
     * X坐标
     */
    private String cooX;
    /**
     * Y坐标
     */
    private String cooY;
    /**
     *
     */
    private String currentCallCode;
    /**
     *
     */
    private String currentPositionCode;
    /**
     *
     */
    private String data;
    /**
     * 地图类型
     */
    private String mapCode;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 货架号
     */
    private String podCode;
    /**
     *
     */
    private String podDir;
    /**
     * 请求编码
     */
    private String reqCode;
    /**
     * 请求时间
     */
    private String reqTime;
    /**
     * 执行任务机器编号
     */
    private String robotCode;
    /**
     * 任务编号
     */
    private String taskCode;
    /**
     * 地点地码(起点)
     */
    private String wbCode;
    /**
     * 地点地码(终点)
     */
    private String mapDataCode;

    /**
     * 提升机编号
     */
    private String liftId;
    /**
     * 门开时所在的楼层
     */
    private String srcFloor;
    /**
     * AGV 目标楼层，默认第一层为 1
     */
    private String destFloor;
    /**
     * 申请资源时对应的唯一标识
     */
    private String taskDetailKey;


}

