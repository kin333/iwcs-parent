package com.wisdom.controller.upstream.mes;


import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.ModifyPodStatus;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.StartSupllyAndRecyles;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.MODIFY_POD_STATUS;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.MODIFY_POD_STATUS_DESCS;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;


/**
 * 超越修改货架状态
 */
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/api/wisdom/notify")
public class ModifyPodStatusController {

    @Autowired
    TaskCreateService taskCreateService;
    @Autowired
    MesRequestService mesRequestService;


    /**
     * 超越修改货架状态
     */
    @PostMapping("/modifyPodStatus")
    @SystemInterfaceLog(methodCode = MODIFY_POD_STATUS, methodName = MODIFY_POD_STATUS_DESCS, methodThansfer = SRC_MES)
    public MesResult modifyPodStatus(@RequestBody MesBaseRequest<ModifyPodStatus> mesBaseRequest) {

        ModifyPodStatus data = mesBaseRequest.getData();
        mesRequestService.modifyPodStatus(data, mesBaseRequest.getReqcode());
        return new MesResult(mesBaseRequest.getReqcode());
    }
}
