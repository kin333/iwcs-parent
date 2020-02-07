package com.wisdom.iwcs.domain.task;

import lombok.Getter;
import lombok.Setter;

/**
 * 创建叉车任务请求类
 */
@Getter
@Setter
public class ForkliftTaskRequest {
    /**
     * 起点编号
     */
    private String startPoint;
    /**
     * 终点编号
     */
    private String endPoint;
    /**
     * 起点仓位
     */
    private String startBinCode;
    /**
     * 终点仓位
     */
    private String endBinCode;
}
