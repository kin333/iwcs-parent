package com.wisdom.iwcs.domain.mes;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Agv到达等待点
 * @Author george
 * @Date 2019/8/27 11:10
 */
@Getter
@Setter
public class ArriveDestWbWaitPortInfoDTO {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 等待点
     */
    private String waitPort;
    /**
     * AGV编号
     */
    private String agvCode;
    /**
     * 到达时间
     */
    private Date arriveTime;
}
