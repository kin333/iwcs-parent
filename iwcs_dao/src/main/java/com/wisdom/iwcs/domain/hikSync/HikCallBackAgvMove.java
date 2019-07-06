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
     * 地点地码(!!!)
     */
    private String wbCode;
    /**
     *
     */
    private String mapDataCode;


}

