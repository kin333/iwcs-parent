package com.wisdom.iwcs.service.door.impl;

import com.wisdom.iwcs.common.utils.plcUtils.CRCUtils;
import com.wisdom.iwcs.common.utils.plcUtils.PlcProtocolUtils;
import com.wisdom.iwcs.mapper.door.DoorReport;
import com.wisdom.iwcs.netty.DoorNettyClient;
import com.wisdom.iwcs.service.base.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * plc 上报门的状态
     * @param
     * @return
     */
    public void doorReportState(DoorReport doorReport){

    }

    /**
     * 通知 门开启/关闭
     * @param
     * @return
     */
    public void notifyDoorOpenOrClose(String workType){
        //通知线体 是否成功
        byte[] leaveCommandBinary= this.agvNotifyCommandBinary("01","01","01");
        DoorNettyClient lineNettyClient = DoorNettyClient.getInstance();
        lineNettyClient.sendMsg(leaveCommandBinary);
    }

    /**
     * Agv搬运货架到达/离开线体工作点
     * @param
     * @return
     */
    private String agvStatusCommandStr(String msgCode, String workPoint, String agvTaskType){
        //获取随机码
        String randomNum = iCommonService.randomHexString(8);
        //写死询命令
        String commandBody = msgCode + "05" + randomNum + workPoint + agvTaskType;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s + "2C";

        //写入line_msg_log
        //this.insertLineMsgLog(msgCode,commandComplete,PLC_SEND,randomNum);
        return commandComplete;
    }
    private byte[] agvNotifyCommandBinary(String msgCode, String workPoint, String agvTaskType){
        String generatorQueryCommandStr = this.agvStatusCommandStr(msgCode, workPoint, agvTaskType);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }
}
