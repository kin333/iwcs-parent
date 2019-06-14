package com.wisdom.service.inv;

import com.wisdom.event.inv.InvTaskBincodeOccupationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @Auther: panzun
 * @Date: 2019-3-22 15:48
 * @Description:
 */
public
interface IinvBincodeOccupationService {

    @Async("InvBincodeOccupationExecutor")
    @EventListener
    void invBincodeOccupationEventListener(InvTaskBincodeOccupationEvent invTaskBincodeOccupationEvent);

    void supplementInvCallOut(String bincode);
}
