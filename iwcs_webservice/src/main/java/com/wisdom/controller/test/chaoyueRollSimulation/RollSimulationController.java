package com.wisdom.controller.test.chaoyueRollSimulation;


import com.wisdom.controller.test.MesRequestInfo;
import com.wisdom.controller.test.TestMesBaseRequest;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.Imitatetest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.StartSupllyAndRecyles;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.SupllyUnload;
import com.wisdom.iwcs.mapper.task.ImitateTestMapper;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wisdom.iwcs.common.utils.TaskConstants.notifyAgvLeaveStatus.LEAVE_DOWN_GOOD;
import static com.wisdom.iwcs.common.utils.TaskConstants.notifyAgvLeaveStatus.LEAVE_GET_GOOD;

@RestController
public class RollSimulationController {
    private Logger logger = LoggerFactory.getLogger(RollSimulationController.class);

    @Autowired
    ImitateTestMapper imitateTestMapper;
    @Autowired
    MesRequestService mesRequestService;

    private String TASK_CODE = "TEST";

    /**
     * 节点变更
     */
    @RequestMapping("/api/wisdom/autoProductionLine/supplyAndRecyle/agvProcessNotify")
    public MesResult agvProcessNotify(@RequestBody TestMesBaseRequest<RollResultInfo> rollRequetInfo) {

        String reqCode = rollRequetInfo.getReqcode();
        RollResultInfo data = rollRequetInfo.getData();

        Preconditions.checkMesBusinessError(StringUtils.isEmpty(data.getTaskSta()), "任务状态必填", reqCode);

        switch (data.getTaskSta())  {
            case "1":
                //接料点到达
                return arriveSend(data.getTaskCode(), data.getCurrentWb(), reqCode);
            case "2":
                //接料点离开
                return leaveSend();
            case "3":
                //供料点到达
                return arriveReceive(data.getTaskCode(), data.getCurrentWb(), reqCode);
            case "4":
                //供料点离开
                return leaveSend();
            case "5":
                //空上箱点到达
                return arriveRecyle(data.getTaskCode(), data.getCurrentWb(), reqCode);
            case "6":
                //空上箱点离开
                return leaveSend();
            case "7":
                //回收点到达
                return arriveRecyleBox(data.getTaskCode(), data.getCurrentWb(), reqCode);
            case "8":
                //回收点离开
                return leaveSend();
            default:
                logger.error("mes URL参数异常");
        }
        return new MesResult();
    }

    public MesResult arriveSend(String taskCode, String currentWb, String reqCode) {

        logger.info("任务{}开始请求AGV节点变更", taskCode);

        StartSupllyAndRecyles startSupllyAndRecyles = new StartSupllyAndRecyles();


        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(taskCode);

        startSupllyAndRecyles.setCurrentWb(currentWb);
        startSupllyAndRecyles.setNodeType("1");
        startSupllyAndRecyles.setTaskCode(taskCode);
        // 是否开始滚动
        mesRequestService.startSupllyAndRecyles(startSupllyAndRecyles, reqCode);

        return new MesResult();
    }
    // 所有离开节点通知
    public MesResult leaveSend() {
        return new MesResult();
    }
    // 供料点下料或接受空料箱
    public MesResult arriveReceive(String taskCode, String currentWb, String reqCode) {
        logger.info("任务{}开始请求AGV节点变更", taskCode);

        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(taskCode);

        StartSupllyAndRecyles startSupllyAndRecyles = new StartSupllyAndRecyles();

        if (StringUtils.isNotEmpty(imitatetest.getRecyclingpoint())) {
            startSupllyAndRecyles.setRecyleWb(imitatetest.getRecyclingpoint());
            startSupllyAndRecyles.setNodeType("2");
        } else {
            startSupllyAndRecyles.setRecyleWb(null);
            startSupllyAndRecyles.setNodeType("1");
        }
        startSupllyAndRecyles.setCurrentWb(currentWb);
        startSupllyAndRecyles.setTaskCode(taskCode);
        // 是否开始滚动
        mesRequestService.startSupllyAndRecyles(startSupllyAndRecyles, reqCode);

        return new MesResult();
    }

    // 空上箱点滚动
    public MesResult arriveRecyle(String taskCode, String currentWb, String reqCode) {
        logger.info("任务{}开始请求AGV节点变更", taskCode);

        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(taskCode);

        StartSupllyAndRecyles startSupllyAndRecyles = new StartSupllyAndRecyles();

        startSupllyAndRecyles.setNodeType("2");
        startSupllyAndRecyles.setCurrentWb(currentWb);
        startSupllyAndRecyles.setTaskCode(taskCode);

        mesRequestService.startSupllyAndRecyles(startSupllyAndRecyles, reqCode);

        return new MesResult();
    }

    // 回收点滚动
    public MesResult arriveRecyleBox(String taskCode,String currentWb, String reqCode) {

        logger.info("任务{}开始请求AGV节点变更", taskCode);

        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(taskCode);

        StartSupllyAndRecyles startSupllyAndRecyles = new StartSupllyAndRecyles();

        startSupllyAndRecyles.setNodeType("3");
        startSupllyAndRecyles.setCurrentWb(currentWb);
        startSupllyAndRecyles.setTaskCode(taskCode);

        mesRequestService.startSupllyAndRecyles(startSupllyAndRecyles, reqCode);

        return new MesResult();
    }

    /**
     * iwcs上报 上箱点已回收结果
     * @param rollResultInfo
     * @return
     */
    @RequestMapping("/api/wisdom/autoProductionLine/emptyRecyleTask/srcWb/recyleResult")
    public MesResult recyleResult(@RequestBody TestMesBaseRequest<RollResultInfo> rollResultInfo) {

        RollResultInfo data = rollResultInfo.getData();
        String reqCode = rollResultInfo.getReqcode();
        SupllyUnload supllyUnload = new SupllyUnload();

        Preconditions.checkMesBusinessError(data.getEmptyRecyleNum() == null, "回收空框数量必填", reqCode);
        Preconditions.checkMesBusinessError(StringUtils.isEmpty(data.getTaskCode()), "任务号必填", reqCode);
        Preconditions.checkMesBusinessError(StringUtils.isEmpty(data.getSrcWbCode()), "回收上箱点必填", reqCode);

//        Imitatetest imitatetest = imitateTestMapper.selectByTaskCode(data.getTaskCode());

        supllyUnload.setTaskCode(data.getTaskCode());
        supllyUnload.setCurrentWb(data.getSrcWbCode());
        supllyUnload.setTaskSta("6");


        // 调用滚筒是否离开
        mesRequestService.supllyUnload(supllyUnload, reqCode);

        return new MesResult();
    }

    /**
     * 最终回收结果
     * @param rollResultInfo
     * @return
     */
    @RequestMapping("/api/wisdom/autoProductionLine/emptyRecyleTask/recyleWb/recyleResult")
    public MesResult recyleBoxResult(@RequestBody TestMesBaseRequest<RollResultInfo> rollResultInfo) {

        RollResultInfo data = rollResultInfo.getData();
        String reqCode = rollResultInfo.getReqcode();
        SupllyUnload supllyUnload = new SupllyUnload();

        Preconditions.checkMesBusinessError(StringUtils.isEmpty(data.getEmptyRecyleWb()), "回收点必填", reqCode);

        supllyUnload.setTaskCode(data.getTaskCode());
        supllyUnload.setTaskSta("8");
        supllyUnload.setCurrentWb(data.getEmptyRecyleWb());

        // 调用滚筒是否离开
        mesRequestService.supllyUnload(supllyUnload, reqCode);

        return new MesResult();
    }


    /**
     * 模拟SCADA接收供料结果（供料点上报已接收供料信息）
     */
    @RequestMapping("/api/wisdom/autoProductionLine/supplyAndRecyle/supllyUnload/SupllyAndRecyleInfo")
    public MesResult receiveUpResult(@RequestBody TestMesBaseRequest<MesRequestInfo> mesBaseRequest){
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
    @RequestMapping("/api/wisdom/autoProductionLine/supplyAndRecyle/supllyAndRecyleResults")
    public MesResult receiveDownResult(@RequestBody TestMesBaseRequest<MesRequestInfo> mesBaseRequest){
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
