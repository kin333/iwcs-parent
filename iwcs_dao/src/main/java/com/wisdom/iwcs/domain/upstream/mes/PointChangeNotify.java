package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 节点变更通知信息类
 * @author han
 */
@Getter
@Setter
public class PointChangeNotify {
    /**
     * 请求编号,可自己生成
     */
    private String reqcode;
    /**
     * 任务号
     */
    private String taskCode;
    /**
     * 当前点位
     */
    private String currentWb;
    /**
     * 小车编号
     */
    private String agvCode;
    /**
     * 任务状态：供货点接料 必填项
     * 供料点接货到位(2)、供料点接货离开(3)、接料点到位(4)、接料点离开(6)
     */
    private String taskSta;
}

