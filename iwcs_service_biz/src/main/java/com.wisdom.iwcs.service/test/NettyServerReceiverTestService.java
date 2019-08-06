package com.wisdom.iwcs.service.test;

import com.wisdom.iwcs.common.utils.plcUtils.CRCUtils;
import com.wisdom.iwcs.common.utils.plcUtils.PlcProtocolUtils;
import com.wisdom.iwcs.domain.elevator.EleMsgLog;
import com.wisdom.iwcs.domain.linebody.LineMsgLog;
import com.wisdom.iwcs.mapper.elevator.EleMsgLogMapper;
import com.wisdom.iwcs.mapper.linebody.LineMsgLogMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.PlcMsgType.PLC_RECEIVE;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PlcMsgType.PLC_SEND;

@Service
public class NettyServerReceiverTestService {

    ChannelHandlerContext ctx;

    @Autowired
    private EleMsgLogMapper eleMsgLogMapper;
    @Autowired
    private LineMsgLogMapper lineMsgLogMapper;
    @Autowired
    private ICommonService iCommonService;

    /**
     * 线体呼叫 空货架
     * @param
     * @return
     */
    public void lineCallEmptyPod(String berCode){
        byte[] lineMsgReturnCommandBinary= this.notifyLineClientBinary(berCode,"01");
        ctx.writeAndFlush(lineMsgReturnCommandBinary);
    }

    /**
     * 线体呼叫 去老化区
     * @param
     * @return
     */
    public void lineCallPodLeave(String berCode){
        byte[] lineMsgReturnCommandBinary= this.notifyLineClientBinary(berCode,"03");
        ctx.writeAndFlush(lineMsgReturnCommandBinary);
    }

    /**
     * 通知wcs
     * 允许进电梯
     * @param
     * @return
     */
    public void enterEle(String randomNum, String floor){
        byte[] eleMsgReturnCommandBinary= this.notifyEleClientBinary(randomNum,floor);
        ctx.writeAndFlush(eleMsgReturnCommandBinary);
    }

    /**
     * 接收成功，返回
     */
    public void plcReceiver(String sendAddr, String reqCode, String commandType, String msgBody){
        if ("06".equals(commandType)){
            this.insertEleMsgLog(sendAddr,msgBody,PLC_RECEIVE,reqCode);
        }else{
            this.insertEleMsgLog(sendAddr,msgBody,PLC_RECEIVE,reqCode);
        }
        byte[] eleMsgReturnCommandBinary= this.msgReturnCommandBinary(commandType,"01",reqCode);
        ctx.writeAndFlush(eleMsgReturnCommandBinary);
    }

    /**
     * 命令
     * 通知 电梯client
     * @param
     * @return
     */
    private String notifyEleClientCommandStr(String randomNum, String floor){
        //TODO 地址、命令码类型做查询，配置在电梯表
        //写死询命令
        String commandBody = "01" + "03" + randomNum +"01" + floor + "01" + "02";
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s;
        //写入日志 msg_log
        this.insertEleMsgLog("01",commandComplete,PLC_SEND,randomNum);

        return commandComplete;
    }
    private byte[] notifyEleClientBinary(String randomNum, String floor){
        String generatorQueryCommandStr = this.notifyEleClientCommandStr(randomNum, floor);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * 命令
     * 通知 线体client
     * @param
     * @return
     */
    private String notifyLineClientCommandStr(String berCode, String workType){
        //TODO 地址、命令码类型做查询，配置在电梯表
        //写死询命令
        String randomNum =  iCommonService.randomHexString(8);
        String commandBody = "01" + "04" + randomNum +"01" + berCode + workType;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s;
        //写入日志 msg_log
        this.insertLineMsgLog("01",commandComplete,PLC_SEND,randomNum);

        return commandComplete;
    }
    private byte[] notifyLineClientBinary(String randomNum, String floor){
        String generatorQueryCommandStr = this.notifyLineClientCommandStr(randomNum, floor);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * 命令
     * client 上报返回结果
     * msgStatus 0失败，1成功
     */
    private String msgReturnCommandStr(String device, String msgStatus, String randomNum){

        //写死询命令
        String commandBody = "01" + device + msgStatus + randomNum ;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s;
        //写入日志 msg_log
        if ("06".equals(device)){
            this.insertEleMsgLog("01",commandComplete,PLC_SEND,randomNum);
        }else{
            this.insertLineMsgLog("01",commandComplete,PLC_SEND,randomNum);
        }
        return commandComplete;
    }
    private byte[] msgReturnCommandBinary(String device,String msgStatus, String randomNum){
        String generatorQueryCommandStr = this.msgReturnCommandStr(device,msgStatus, randomNum);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * common insert line_msg_log
     */
    private void insertEleMsgLog(String sendAddr, String msgBody, String msgType, String reqCode){
        EleMsgLog eleMsgLog = new EleMsgLog();
        eleMsgLog.setCreatedTime(new Date());
        eleMsgLog.setSendAddr(sendAddr);
        eleMsgLog.setMsgBody(msgBody);
        eleMsgLog.setMsgType(msgType);
        eleMsgLog.setReqCode(reqCode);
        eleMsgLogMapper.insertSelective(eleMsgLog);
    }

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
