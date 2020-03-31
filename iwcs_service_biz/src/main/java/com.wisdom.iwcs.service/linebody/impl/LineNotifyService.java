package com.wisdom.iwcs.service.linebody.impl;

import com.google.common.base.Strings;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.common.utils.plcUtils.CRCUtils;
import com.wisdom.iwcs.common.utils.plcUtils.PlcProtocolUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.linebody.LineBodyReport;
import com.wisdom.iwcs.domain.linebody.LineMsgLog;
import com.wisdom.iwcs.domain.task.CreateMainTaskRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.linebody.LineBodyMapper;
import com.wisdom.iwcs.mapper.linebody.LineMsgLogMapper;
import com.wisdom.iwcs.netty.LineNettyClient;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.PlcMsgType.PLC_SEND;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.*;

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
     * 线体通知WCS 呼叫货架补入
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
        if (baseMapBerth !=null && (baseMapBerth.getInLock() != 0 || !Strings.isNullOrEmpty(baseMapBerth.getPodCode()))){
            msgStatus = "02";
        }else {
            //创建 呼叫货架补入 任务
            List<String> staticViaPaths = new ArrayList<>();
            staticViaPaths.add(workPoint);
            CreateMainTaskRequest createMainTaskRequest = new CreateMainTaskRequest();
            createMainTaskRequest.setReqCode(lineBodyReport.getReqCode());
            String mainTaskNum = CodeBuilder.codeBuilder("M");
            createMainTaskRequest.setTaskCode(mainTaskNum);
            createMainTaskRequest.setPriority(1);
            if (lineBodyReport.getAddress().equals("01")){
                createMainTaskRequest.setMainTaskType(LINEINSERTINGAREACALL);
            }else if (lineBodyReport.getAddress().equals("02")){
                createMainTaskRequest.setMainTaskType(QUAINSPAREACALL);
            }
            createMainTaskRequest.setStaticViaPaths(staticViaPaths);
            TaskCreateService taskCreateService = (TaskCreateService) AppContext.getBean("taskCreateService");
            taskCreateService.publicTaskCreate(createMainTaskRequest);
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
            List<String> staticViaPaths = new ArrayList<>();
            staticViaPaths.add(workPoint);
            CreateMainTaskRequest createMainTaskRequest = new CreateMainTaskRequest();
            createMainTaskRequest.setReqCode(lineBodyReport.getReqCode());
            String mainTaskNum = CodeBuilder.codeBuilder("M");
            createMainTaskRequest.setTaskCode(mainTaskNum);
            createMainTaskRequest.setPriority(1);
            if (lineBodyReport.getAddress().equals("01")){
                createMainTaskRequest.setMainTaskType(LINEINSERTINGAREALEAVE);
            }else if (lineBodyReport.getAddress().equals("02")){
                createMainTaskRequest.setMainTaskType(QUAINSPAREALEAVE);
            }
            createMainTaskRequest.setStaticViaPaths(staticViaPaths);
            TaskCreateService taskCreateService = (TaskCreateService) AppContext.getBean("taskCreateService");
            taskCreateService.publicTaskCreate(createMainTaskRequest);
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
    public void agvStatusIne(String workPoint, String agvTaskType){
        //通过工作点查询那个地址
        String msgCode = lineBodyMapper.selectMsgCode(workPoint);
        //通知线体
        byte[] arriveCommandBinary= this.agvStatusCommandBinary(msgCode, workPoint, agvTaskType);

        LineNettyClient lineNettyClient = LineNettyClient.getInstance();
        lineNettyClient.sendMsg(arriveCommandBinary);
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
        String commandBody = msgCode + "05"  + workPoint + agvTaskType;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s + "2C";

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
        String commandBody = controllerNo + controllerType + msgStatus;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s + "2C";

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

    /**
     * 发送消息test
     */
    public void testSend(){
        //通知线体 成功
        byte[] leaveCommandBinary= this.lineMsgReturnCommandBinary("01", "05", "01", "01");
        LineNettyClient lineNettyClient = LineNettyClient.getInstance();
        lineNettyClient.sendMsg(leaveCommandBinary);
    }

    /**
     * 发送消息test
     */
    public void notifyOneLineTest(String agvTaskType){
        //通知线体 成功
        agvStatusIne("01",agvTaskType);
    }

    /**
     * 发送消息test
     */
    public void notifyTwoLineTest(String agvTaskType){
        //通知线体 成功
        agvStatusIne("02",agvTaskType);
    }
}
