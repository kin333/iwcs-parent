package com.wisdom.iwcs.domain.hikSync;

import lombok.Getter;
import lombok.Setter;

/**
 * 小车到达检验点接收参数
 * @author han
 */
@Getter
@Setter
public class HikReachCheckArea extends BaseHikRequest{
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
