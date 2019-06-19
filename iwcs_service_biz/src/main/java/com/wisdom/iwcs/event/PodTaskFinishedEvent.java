package com.wisdom.iwcs.event;

/**
 * 货架任务结束事件
 *
 * @author ted
 * @create 2019-03-06 上午9:50
 **/
public class PodTaskFinishedEvent extends CusBaseCusEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PodTaskFinishedEvent(PodTaskFinishedEventInfos source) {
        super(source);
    }
}
