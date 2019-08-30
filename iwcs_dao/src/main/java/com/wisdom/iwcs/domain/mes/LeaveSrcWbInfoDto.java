package com.wisdom.iwcs.domain.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * AGV离开起点
 * @Author george
 * @Date 2019/8/30 10:30
 */
@Getter
@Setter
public class LeaveSrcWbInfoDto {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 搬运任务起点
     */
    private String srcWb;
    /**
     * AGV编号
     */
    private String agvCode;
    /**
     * 到达时间
     */
    private String leaveTime;
}
