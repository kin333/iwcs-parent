package com.wisdom.iwcs.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义基础事件
 *
 * @author ted
 * @create 2019-03-06 上午9:46
 **/
public abstract class CusBaseCusEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CusBaseCusEvent(Object source) {
        super(source);
    }
}
