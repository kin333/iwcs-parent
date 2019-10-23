package com.wisdom.controller.test.chaoyueRollSimulation;


import com.wisdom.controller.test.MesRequestInfo;
import com.wisdom.controller.test.TestMesBaseRequest;
import com.wisdom.iwcs.domain.task.Imitatetest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.SupllyUnload;
import com.wisdom.iwcs.mapper.task.ImitateTestMapper;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wisdom.iwcs.common.utils.TaskConstants.notifyAgvLeaveStatus.LEAVE_DOWN_GOOD;
import static com.wisdom.iwcs.common.utils.TaskConstants.notifyAgvLeaveStatus.LEAVE_GET_GOOD;

@RestController
@RequestMapping("/api/wisdom/autoProductionLine/supplyAndRecyle")
public class RollSimulationController {
    @Autowired
    ImitateTestMapper imitateTestMapper;
    @Autowired
    MesRequestService mesRequestService;

    private String TASK_CODE = "TEST";

    /**
     * 模拟SCADA接收供料结果（供料点上报已接收供料信息）
     */
    @PostMapping("/supllyUnload/SupllyAndRecyleInfo")
    public MesResult receiveUpNum(@RequestBody TestMesBaseRequest<MesRequestInfo> mesBaseRequest){
        MesRequestInfo mesRequestInfo = mesBaseRequest.getData();
        String taskCode = mesRequestInfo.getTaskCode();
        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(taskCode);

        //获取请求参数
        SupllyUnload supllyUnload = new SupllyUnload();
        supllyUnload.setTaskCode(taskCode);
        supllyUnload.setCurrentWb(imitatetest.getOutskupoint());
        supllyUnload.setTaskSta(LEAVE_DOWN_GOOD);

        //通知agv可离开
        mesRequestService.supllyUnload(supllyUnload,TASK_CODE);

        return new MesResult();
    }

    /**
     * 模拟 SCADA 上报已下料数量及已接收空框数量
     */
    @PostMapping("/supllyAndRecyleResult")
    public MesResult receiveDownNum(@RequestBody TestMesBaseRequest<MesRequestInfo> mesBaseRequest){
        MesRequestInfo mesRequestInfo = mesBaseRequest.getData();
        String taskCode = mesRequestInfo.getTaskCode();
        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(taskCode);

        //获取请求参数
        SupllyUnload supllyUnload = new SupllyUnload();
        supllyUnload.setTaskCode(taskCode);
        supllyUnload.setCurrentWb(imitatetest.getInskupoint1());
        supllyUnload.setTaskSta(LEAVE_GET_GOOD);

        //通知agv可离开
        mesRequestService.supllyUnload(supllyUnload,TASK_CODE);

        return new MesResult();
    }

}
