package com.wisdom.controller.hikcallback;

import com.wisdom.iwcs.domain.hikSync.HikCallBackAgvMove;
import com.wisdom.iwcs.domain.hikSync.HikSyncResponse;
import com.wisdom.iwcs.service.hikCallback.iwcsHikCallback.HikCallbackIwcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * iwcs 的海康回调接口
 * @author han
 */
@RestController
@RequestMapping("/hikCallback_iwcs")
public class HikCallbackIwcsController {

    @Autowired
    HikCallbackIwcsService hikCallbackIwcsService;

    /**
     * 小车移动的回调接口
     * @return
     */
    @PostMapping("/taskNotify")
    public HikSyncResponse taskNotify(@RequestBody HikCallBackAgvMove hikCallBackAgvMove) {
        return hikCallbackIwcsService.taskNotify(hikCallBackAgvMove);
    }
}
