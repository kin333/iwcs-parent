package com.wisdom.iwcs.domain.upstream.mes;


import lombok.Getter;
import lombok.Setter;

/**
 * MES通知可从等待点进入终点（围栏已开/围栏已关）消息接收类
 */
@Setter
@Getter
public class ConWaitToDestWbRequest {

    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 关闭或打开围栏  1开围栏成功,2关围栏成功
     */
    private String doorStatus;

}
