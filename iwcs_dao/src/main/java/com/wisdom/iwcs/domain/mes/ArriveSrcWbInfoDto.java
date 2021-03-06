package com.wisdom.iwcs.domain.mes;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * AGV到达起点
 * @Author george
 * @Date 2019/8/30 10:23
 */
@Getter
@Setter
public class ArriveSrcWbInfoDto {

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
    private Date arriveTime;
}
