package com.wisdom.iwcs.service.inv;

import com.wisdom.iwcs.event.inv.InvTaskFinishedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @Auther: panzun
 * @Date: 2019-3-21 09:37
 * @Description:
 */
public
interface IinvSupplementCallOutService {

    @Async("InvFinishedExecutor")
    @EventListener
    void invFinishedEventListener(InvTaskFinishedEvent invTaskFinishedEvent);

    void supplementInvTaskCallOut(String wbCode, String srcInvNo);
}
