package com.wisdom.iwcs.domain.hikSync;

import lombok.Getter;
import lombok.Setter;

/**
 * 向Hik发送接电梯货架的请求参数
 */
@Setter
@Getter
public class HikFiniTask extends BaseHikRequest {
    /**
     * 提升机编号
     */
    private String liftId;
    /**
     * 门开时所在的楼层
     */
    private String instantLocation;
    /**
     * 申请资源时对应的唯一标识
     */
    private String taskDetailKey;
}
