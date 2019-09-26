package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * Mes通知小车离开信息类
 * @author han
 */
@Getter
@Setter
public class NotifyAgvLeave {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 当前点 必填项
     */
    private String currentWb;
    /**
     * 1 供料点(上料点) 2接料点1（下料点1）3接料点2（下料点2）4空料箱回收上箱点（接料点）, 5 空箱点
     */
    private String flag;
}


