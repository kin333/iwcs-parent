package com.wisdom.iwcs.service.outstock;

import com.wisdom.iwcs.event.PodTaskFinishedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:18
 */
public interface IOutStockSupplementCallOutService {
    @Async("PodTaskFinishedExecutor")
    @EventListener
    void podTaskFinishedEventListener(PodTaskFinishedEvent podTaskFinishedEvent);

    void supplementCallOut(String podCode);
}
