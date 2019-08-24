package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 接料点通知供料及回收空箱信息类
 * @author han
 */
@Getter
@Setter
public class StartSupllyAndRecyle {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 供料点 必填项
     */
    private String supplyLoadWb;
    /**
     * 下料数量，选填项
     */
    private Integer supplyUnLoadNum;
    /**
     * 空框回收点，选填项
     */
    private String emptyRecyleWb;
    /**
     * 空框回收数量，选填项
     */
    private Integer emptyRecyleNum;
    /**
     * 任务状态：接料点供料 必填项
     * 供料点接货到位(2)、供料点接货离开(3)、接料点到位(4)、接料点离开(6)
     */
    private String taskSta;
}


