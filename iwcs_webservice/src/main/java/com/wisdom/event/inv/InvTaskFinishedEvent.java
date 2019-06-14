package com.wisdom.event.inv;

import com.wisdom.event.CusBaseCusEvent;

/**
 * 盘点确认结束事件
 *
 * @Auther: panzun
 * @Date: 2019-3-21 09:29
 * @Description:
 */
public class InvTaskFinishedEvent extends CusBaseCusEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public InvTaskFinishedEvent(Object source) {
        super(source);
    }
}
