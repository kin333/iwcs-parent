package com.wisdom.iwcs.service.door.impl;

import com.alibaba.fastjson.JSON;
import com.wisdom.iwcs.common.utils.plcUtils.CRCUtils;
import com.wisdom.iwcs.common.utils.plcUtils.PlcProtocolUtils;
import com.wisdom.iwcs.domain.door.AutoDoor;
import com.wisdom.iwcs.domain.door.DoorMsgLog;
import com.wisdom.iwcs.mapper.door.AutoDoorMapper;
import com.wisdom.iwcs.mapper.door.DoorMsgLogMapper;
import com.wisdom.iwcs.mapper.door.DoorReport;
import com.wisdom.iwcs.netty.DoorNettyClient;
import com.wisdom.iwcs.service.base.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.DoorMsgType.DOOR_CLOSE;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PlcMsgType.PLC_SEND;

/**
 * 自动门 service
 * @Author george
 * @Date 2019/11/26 13:54
 */
@Service
public class DoorNotifyService {
    private final Logger logger = LoggerFactory.getLogger(DoorNotifyService.class);

    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private DoorMsgLogMapper doorMsgLogMapper;
    @Autowired
    private AutoDoorMapper autoDoorMapper;

    /**
     * plc 上报门的状态
     * @param
     * @return
     */
    public void doorReportState(DoorReport doorReport){
        logger.info("门上报消息{}"+JSON.toJSONString(doorReport));
        //TODO 通知rcs 门已经开启/关闭
        String doorStatus = doorReport.getDoorStatus();
        String doorModel = doorReport.getDoorModel();
        String taskStatus = doorReport.getDoorWorkType();

        AutoDoor autoDoor = new AutoDoor();
        autoDoor.setMsgCode(doorReport.getAddress());
        autoDoor.setTaskStatus(taskStatus);
        autoDoor.setDoorModel(doorModel);
        autoDoor.setDoorStatus(doorStatus);
        autoDoorMapper.updateDoorInfo(autoDoor);
    }

    /**
     * 通知 门开启/关闭
     * @param
     * @return
     */
    public void notifyDoorOpenOrClose(String workType){
        if (workType.equals(DOOR_CLOSE)){
            workType = "02";
        }else {
            workType = "01";
        }

        //通知门 开、关
        byte[] leaveCommandBinary= this.agvNotifyCommandBinary(workType);
        DoorNettyClient doorNettyClient = DoorNettyClient.getInstance();
        doorNettyClient.sendMsg(leaveCommandBinary);
    }

    /**
     * Agv搬运货架到达/离开线体工作点
     * @param
     * @return
     */
    private String agvStatusCommandStr(String workType){
        //获取随机码
        String randomNum = iCommonService.randomHexString(8);
        //写死询命令
        String commandBody = "01" + "07" + randomNum + workType;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s + "2C";

        //写入line_msg_log
        this.insertDoorMsgLog("",commandBody,PLC_SEND,randomNum);
        return commandComplete;
    }
    private byte[] agvNotifyCommandBinary(String workType){
        String generatorQueryCommandStr = this.agvStatusCommandStr(workType);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * common insert insertDoorMsgLog
     */
    private void insertDoorMsgLog(String sendAddr, String msgBody, String msgType, String reqCode){
        DoorMsgLog doorMsgLog = new DoorMsgLog();
        doorMsgLog.setCreatedTime(new Date());
        doorMsgLog.setSendAddr(sendAddr);
        doorMsgLog.setMsgBody(msgBody);
        doorMsgLog.setMsgType(msgType);
        doorMsgLog.setReqCode(reqCode);
        doorMsgLogMapper.insertSelective(doorMsgLog);
    }

    /**
     * 发送消息test
     */
    public void notifyDoorTest(String workType){
        //通知门 成功
        notifyDoorOpenOrClose(workType);
    }
}
