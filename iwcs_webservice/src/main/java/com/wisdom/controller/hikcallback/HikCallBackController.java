package com.wisdom.controller.hikcallback;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.domain.hikSync.*;
import com.wisdom.iwcs.service.hikCallback.IHikCallBackGetPodReturnAreaService;
import com.wisdom.iwcs.service.hikCallback.IHikCallBackNotifyPodArrService;
import com.wisdom.iwcs.service.hikCallback.IHikCallBackSyncService;
import com.wisdom.iwcs.service.hikCallback.IHikCallBackTaskNotifyService;
import com.wisdom.iwcs.service.hikCallback.iwcsHikCallback.HikCallbackIwcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_HIK;

/**
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/hikCallback")
public class HikCallBackController {

    @Autowired
    private IHikCallBackSyncService IHikCallBackSyncService;
    @Autowired
    private IHikCallBackTaskNotifyService IHikCallBackTaskNotifyService;
    @Autowired
    private IHikCallBackGetPodReturnAreaService IHikCallBackGetPodReturnAreaService;
    @Autowired
    private IHikCallBackNotifyPodArrService IHikCallBackNotifyPodArrService;
    @Autowired
    HikCallbackIwcsService hikCallbackIwcsService;


    /**
     * 同步海康基础数据
     *
     * @param syncNotifyRequestDto
     * @return
     */
    @PostMapping(value = "/syncNotify")
    @SystemInterfaceLog(methodCode = SYNC_NOTIFY_CODE, methodName = SYNC_NOTIFY_NAME, methodThansfer = SRC_HIK)
    public HikSyncResponse receiveSyncBaseInfoNotify(@RequestBody SyncNotifyRequestDto syncNotifyRequestDto) {

        return IHikCallBackSyncService.receiveSyncBaseInfoNotify(syncNotifyRequestDto);
    }

    /**
     * 指定条件下通知客户端
     *
     * @param notifyClientRequestDTO
     * @return
     */
    @PostMapping(value = "/notifyClient")
    @SystemInterfaceLog(methodCode = NOTIFY_CLIENT_CODE, methodName = NOTIFY_CLIENT_NAME, methodThansfer = SRC_HIK)
    public HikSyncResponse receiveTaskNotify(@RequestBody NotifyClientRequestDTO notifyClientRequestDTO) {
        return IHikCallBackTaskNotifyService.receiveTaskNotify(notifyClientRequestDTO);
    }

    /**
     * 海康获取货架回库策略
     *
     * @param podReturnAreaRequestDTO
     * @return
     */
    @PostMapping(value = "/podReturnArea")
    @SystemInterfaceLog(methodCode = POD_RETURN_STRA_CODE, methodName = POD_RETURN_STRA_NAME, methodThansfer = SRC_HIK)
    public HikReturnPodStraResponse getPodReturnArea(@RequestBody PodReturnAreaRequestDTO podReturnAreaRequestDTO) {
        return IHikCallBackGetPodReturnAreaService.returnPodReturnArea(podReturnAreaRequestDTO);
    }

    /**
     * 通知货架返程回储位
     *
     * @param notifyPodArrRequestDTO
     * @return
     */
    @PostMapping(value = "/notifyPodArr")
    @SystemInterfaceLog(methodCode = NOTIFY_POD_ARR_CODE, methodName = NOTIFY_POD_ARR_NAME, methodThansfer = SRC_HIK)
    public HikSyncResponse receivePodArriveStorageNotify(@RequestBody NotifyPodArrRequestDTO notifyPodArrRequestDTO) {
        return IHikCallBackNotifyPodArrService.receivePodArriveStorageNotify(notifyPodArrRequestDTO);
    }

    /**
     * 小车移动的回调接口
     * @return
     */
    @PostMapping("/iwcs/taskNotify")
    @SystemInterfaceLog(methodCode = TASK_NOTIFY, methodName = TASK_NOTIFY_DESC, methodThansfer = SRC_HIK)
    public HikSyncResponse taskNotify(@RequestBody HikCallBackAgvMove hikCallBackAgvMove) {
        return hikCallbackIwcsService.taskNotify(hikCallBackAgvMove);
    }

    /**
     * 小车到达检验点回调
     * @return
     */
    @PostMapping("/applyResource")
    @SystemInterfaceLog(methodCode = APPLY_RESOURCE, methodName = APPLY_RESOURCE_DESC, methodThansfer = SRC_HIK)
    public HikSyncResponse applyResource(@RequestBody HikReachCheckArea hikReachCheckArea) {
        return hikCallbackIwcsService.applyResource(hikReachCheckArea);
    }

    /**
     * 小车送货架进电梯后,agv出电梯回调接口
     * @param hikReachCheckArea
     * @return
     */
    @PostMapping("/excuteTask")
    @SystemInterfaceLog(methodCode = EXCUTE_TASK, methodName = EXCUTE_TASK_DESC, methodThansfer = SRC_HIK)
    public HikSyncResponse excuteTask(@RequestBody HikReachCheckArea hikReachCheckArea) {
        return hikCallbackIwcsService.excuteTask(hikReachCheckArea);
    }
    /**
     * agv接货架出电梯时,小车出电梯回调
     * @return
     */
    @PostMapping("/releaseResource")
    @SystemInterfaceLog(methodCode = RELEASE_RESOURCE, methodName = RELEASE_RESOURCE_DESC, methodThansfer = SRC_HIK)
    public HikSyncResponse releaseResource(@RequestBody HikReachCheckArea hikReachCheckArea) {
        return hikCallbackIwcsService.releaseResource(hikReachCheckArea);
    }

}
