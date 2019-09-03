package com.wisdom.iwcs.domain.mes;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * AGV到达终点
 * @Author george
 * @Date 2019/8/30 10:35 
 */
@Getter
@Setter
public class ArriveDestWbInfoDto {

    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 搬运任务起点
     */
    private String destWb;
    /**
     * AGV编号
     */
    private String agvCode;
    /**
     * 到达时间
     */
    private Date arriveTime;
}
