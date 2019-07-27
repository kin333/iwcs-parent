package com.wisdom.iwcs.service.linebody.impl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.plcUtils.CRCUtils;
import com.wisdom.iwcs.common.utils.plcUtils.PlcProtocolUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.linebody.LineBodyReport;
import com.wisdom.iwcs.domain.linebody.LineMsgLog;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.linebody.LineBodyMapper;
import com.wisdom.iwcs.mapper.linebody.LineMsgLogMapper;
import com.wisdom.iwcs.netty.LineNettyClient;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.AgingAreaPriorityProp.AUTO_FIRST;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PlcMsgType.PLC_SEND;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.PLAUTOWBCALLPOD;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.PLTOAGING;

/**
 * 线控 service
 * @Author george
 * @Date 2019/7/16 16:17 
 */
@Service
public class LineNotifyService {
    private final Logger logger = LoggerFactory.getLogger(LineNotifyService.class);

    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private LineMsgLogMapper lineMsgLogMapper;
    @Autowired
    private ITaskCreateService iTaskCreateService;
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private LineBodyMapper lineBodyMapper;

    /**
     * 线体通知WCS 呼叫空货架
     * @param
     * @return
     */
    public void lineCallEmptyPod(LineBodyReport lineBodyReport){
        //01 正常
        String msgStatus = "01";
        //接收到信号，拆分，获取点位
        String workPoint = lineBodyReport.getWorkPoint();
        //查询这点有货架或没有完结的任务没
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(workPoint);
        if (baseMapBerth.getInLock() != 0 || !Strings.isNullOrEmpty(baseMapBerth.getPodCode())){
            msgStatus = "02";
        }else {
            //创建 呼叫空货架 任务
            TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
            taskCreateRequest.setTaskTypeCode(PLAUTOWBCALLPOD);
            taskCreateRequest.setTargetPointAlias(lineBodyReport.getWorkPoint());
            taskCreateRequest.setAreaCode(baseMapBerth.getAreaCode());
            iTaskCreateService.creatTask(taskCreateRequest);
        }

        //通知线体 是否成功
        byte[] leaveCommandBinary= this.lineMsgReturnCommandBinary(lineBodyReport.getAddress(), lineBodyReport.getDeviceType(), msgStatus, lineBodyReport.getReqCode());
        LineNettyClient lineNettyClient = LineNettyClient.getInstance();
        lineNettyClient.sendMsg(leaveCommandBinary);
    }

    /**
     * 线体上报WCS 搬走货架
     * @param
     * @return
     */
    public void lineCallAgvPickPod(LineBodyReport lineBodyReport){
        //01 正常
        String msgStatus = "01";
        //接收到信号，拆分，获取点位
        String workPoint = lineBodyReport.getWorkPoint();
        //查询这点有货架没
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(workPoint);
        if (Strings.isNullOrEmpty(baseMapBerth.getPodCode())){
            msgStatus = "02";
        }else{
            //创建 线体到老化区 任务
            //目标点，2楼全自动，随机选，3楼自动区域
            TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
            taskCreateRequest.setTaskTypeCode(PLTOAGING);
            taskCreateRequest.setTargetPointAlias(lineBodyReport.getWorkPoint());
            taskCreateRequest.setSubTaskBizProp(AUTO_FIRST);
            taskCreateRequest.setAreaCode(baseMapBerth.getAreaCode());
            taskCreateRequest.setPodCode(baseMapBerth.getPodCode());
            taskCreateRequest.setStartPointAlias(baseMapBerth.getPointAlias());
            iTaskCreateService.creatTask(taskCreateRequest);
        }

        //通知线体 是否成功
        byte[] leaveCommandBinary= this.lineMsgReturnCommandBinary(lineBodyReport.getAddress(), lineBodyReport.getDeviceType(), msgStatus, lineBodyReport.getReqCode());
        LineNettyClient lineNettyClient = LineNettyClient.getInstance();
        lineNettyClient.sendMsg(leaveCommandBinary);
    }

    /**
     * WCS 调用
     * Agv搬运货架到达/离开线体工作点
     * agvTaskType 到达01 离开02
     * @param workPoint, agvTaskType
     * @return
     */
    public void agvStatusine(String workPoint, String agvTaskType){
        //通过工作点查询那个地址
        String msgCode = lineBodyMapper.selectMsgCode(workPoint);
        //通知线体
        byte[] arriveCommandBinary= this.agvStatusCommandBinary(msgCode, workPoint, agvTaskType);

        LineNettyClient lineNettyClient = LineNettyClient.getInstance();
        lineNettyClient.sendMsg(arriveCommandBinary);
    }

    /**
     * Agv搬运货架离开线体工作点
     * @param
     * @return
     */
    private String agvStatusCommandStr(String msgCode, String workPoint, String agvTaskType){
        //获取随机码
        String randomNum = iCommonService.randomHexString(8);
        //写死询命令
        String commandBody = msgCode + "03" + randomNum + workPoint + agvTaskType;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s;

        //写入line_msg_log
        this.insertLineMsgLog(msgCode,commandComplete,PLC_SEND,randomNum);
        return commandComplete;
    }
    private byte[] agvStatusCommandBinary(String msgCode, String workPoint, String agvTaskType){
        String generatorQueryCommandStr = this.agvStatusCommandStr(msgCode, workPoint, agvTaskType);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * 线体通知成功或失败
     * msgStatus 0失败，1成功
     */
    private String lineMsgReturnCommandStr(String controllerNo, String controllerType, String msgStatus, String randomNum){
        //写死询命令
        String commandBody = controllerNo + controllerType + msgStatus + randomNum ;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s;

        //写入line_msg_log
        this.insertLineMsgLog(controllerNo,commandComplete,PLC_SEND,randomNum);

        return commandComplete;
    }
    private byte[] lineMsgReturnCommandBinary(String controllerNo, String controllerType, String msgStatus, String randomNum){
        String generatorQueryCommandStr = this.lineMsgReturnCommandStr(controllerNo, controllerType, msgStatus, randomNum);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * common insert line_msg_log
     */
    private void insertLineMsgLog(String sendAddr, String msgBody, String msgType, String reqCode){
        LineMsgLog lineMsgLog = new LineMsgLog();
        lineMsgLog.setCreatedTime(new Date());
        lineMsgLog.setSendAddr(sendAddr);
        lineMsgLog.setMsgBody(msgBody);
        lineMsgLog.setMsgType(msgType);
        lineMsgLog.setReqCode(reqCode);
        lineMsgLogMapper.insertSelective(lineMsgLog);
    }
}
