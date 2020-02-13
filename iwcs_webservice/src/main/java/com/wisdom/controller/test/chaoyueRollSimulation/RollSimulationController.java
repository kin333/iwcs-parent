package com.wisdom.controller.test.chaoyueRollSimulation;


import com.wisdom.controller.test.TestMesBaseRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.task.ImitateTestMapper;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RollSimulationController {
    private Logger logger = LoggerFactory.getLogger(RollSimulationController.class);

    @Autowired
    ImitateTestMapper imitateTestMapper;
    @Autowired
    MesRequestService mesRequestService;

    /**
     * 点到点通知
     */
    @RequestMapping("/api/wisdom/agvHandlingTask/leaveSrcWb")
    public MesResult leaveSrcWb(@RequestBody TestMesBaseRequest<RollResultInfo> rollRequetInfo) {

        String reqCode = rollRequetInfo.getReqcode();
        return new MesResult();
    }

    @RequestMapping("/api/wisdom/agvHandlingTask/arriveDestWb")
    public MesResult arriveDestWb(@RequestBody TestMesBaseRequest<RollResultInfo> rollRequetInfo) {

        String podCode = rollRequetInfo.getData().getPodCode();
        return new MesResult();
    }

}
