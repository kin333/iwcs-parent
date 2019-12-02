package com.wisdom.iwcs.service.plc;

import com.wisdom.iwcs.common.utils.plcUtils.PlcRespone;
import com.wisdom.iwcs.domain.door.DoorMsgLog;
import com.wisdom.iwcs.domain.elevator.EleMsgLog;
import com.wisdom.iwcs.domain.elevator.Elevator;
import com.wisdom.iwcs.domain.elevator.ElevatorReport;
import com.wisdom.iwcs.domain.linebody.LineBodyReport;
import com.wisdom.iwcs.domain.linebody.LineMsgLog;
import com.wisdom.iwcs.domain.task.EleControlTask;
import com.wisdom.iwcs.mapper.door.DoorMsgLogMapper;
import com.wisdom.iwcs.mapper.door.DoorReport;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.mapper.elevator.EleMsgLogMapper;
import com.wisdom.iwcs.mapper.elevator.ElevatorMapper;
import com.wisdom.iwcs.mapper.linebody.LineMsgLogMapper;
import com.wisdom.iwcs.service.door.impl.DoorNotifyService;
import com.wisdom.iwcs.service.elevator.impl.ElevatorNotifyService;
import com.wisdom.iwcs.service.linebody.impl.LineNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.PlcMsgType.PLC_RECEIVE;

/**
 * PLC 消息处理
 * @Author george
 * @Date 2019/7/23 9:57
 */
@Service
public class PLCControlService {
    Logger logger = LoggerFactory.getLogger(PLCControlService.class);

    @Autowired
    private EleMsgLogMapper eleMsgLogMapper;
    @Autowired
    private LineMsgLogMapper lineMsgLogMapper;
    @Autowired
    private ElevatorNotifyService elevatorNotifyService;
    @Autowired
    private LineNotifyService lineNotifyService;
    @Autowired
    private ElevatorMapper elevatorMapper;
    @Autowired
    private EleControlTaskMapper eleControlTaskMapper;
    @Autowired
    private DoorNotifyService doorNotifyService;
    @Autowired
    private DoorMsgLogMapper doorMsgLogMapper;

    public String testService(String str){
        logger.info("BusinessService.testService({})...........", str);
        return str;
    }

    public String dealPlcMsg(PlcRespone plcRespone){
        logger.info("接收到PLC Server消息..........."+ plcRespone.getAddress()+":"+plcRespone.getReturnBodyBytes());
        //按地址和命令类型码处理信息
        String sendAddr = plcRespone.getAddress();
        String commandType = plcRespone.getCommandType();
        String msgBody = plcRespone.getReturnBodyBytes();
        String reqCode = msgBody.substring(4,8);
        if (commandType.equals("03")){
            //TODO 电梯状态
            ElevatorReport elevatorReport = new ElevatorReport();
            String elevatorStatus = msgBody.substring(12,14);
            String floor = msgBody.substring(14,16);
            String allowEnterEle = msgBody.substring(16,18);
            String isInStock = msgBody.substring(18,20);
            if (allowEnterEle.equals("01") && isInStock.equals("02")){
                logger.info("电梯通知{}：允许进入电梯"+ plcRespone.getAddress()+":"+allowEnterEle);
                elevatorNotifyService.eleNotifyCheckResult(elevatorReport);
            }else{
                logger.info("电梯通知{}：不允许进去电梯"+ plcRespone.getAddress()+":"+allowEnterEle);
            }
            EleControlTask eleControlTask = new EleControlTask();
            eleControlTask.setEleTaskCode(reqCode);

            eleControlTaskMapper.updateTaskInfo(eleControlTask);

            //更新电梯状态
            Elevator elevator = new Elevator();
            elevator.setEleStatus(elevatorStatus.substring(1,2));
            elevator.setStatusUpdateTime(new Date());
            elevator.setCurrentFloor(Integer.valueOf(floor));
            elevator.setFloorUpdateTime(new Date());
            elevatorMapper.updateElevatorInfo(elevator);

            //insert line_msg_log
            EleMsgLog eleMsgLog = new EleMsgLog();
            eleMsgLog.setCreatedTime(new Date());
            eleMsgLog.setSendAddr(sendAddr);
            eleMsgLog.setMsgBody(msgBody);
            eleMsgLog.setMsgType(PLC_RECEIVE);
            eleMsgLog.setReqCode(reqCode);
            eleMsgLogMapper.insertSelective(eleMsgLog);
        }else if(commandType.equals("06")) {
            //TODO 线体状态
            DoorReport doorReport = new DoorReport();
            doorReport.setAddress(sendAddr);
            doorReport.setDeviceType(commandType);
            doorReport.setReqCode(reqCode);
            String doorStatus = msgBody.substring(8,10);
            String doorWorkType = msgBody.substring(10,12);
            String doorModel = msgBody.substring(12,14);
            //doorNotifyService.doorReportState(doorReport);

            //insert door_msg_log
            DoorMsgLog doorMsgLog = new DoorMsgLog();
            doorMsgLog.setDoorModel(doorModel);
            doorMsgLog.setDoorReportWorkType(doorWorkType);
            doorMsgLog.setDoorStatus(doorStatus);
            doorMsgLog.setMsgBody(msgBody);
            doorMsgLogMapper.insertSelective(doorMsgLog);

        }else{
            //TODO 线体状态
            LineBodyReport lineBodyReport = new LineBodyReport();
            lineBodyReport.setAddress(sendAddr);
            lineBodyReport.setDeviceType(commandType);
            lineBodyReport.setReqCode(reqCode);
            String workType = msgBody.substring(12,14);
            String workPoint = msgBody.substring(10,12);
            if (workType.equals("01")){
                logger.info("线体通知{}：呼叫空货架"+ plcRespone.getAddress()+":"+workPoint);
                lineBodyReport.setWorkPoint(workPoint);
                lineNotifyService.lineCallEmptyPod(lineBodyReport);
            }else {
                logger.info("线体通知{}：呼叫货架离开"+ plcRespone.getAddress()+":"+workPoint);
                lineBodyReport.setWorkPoint(workPoint);
                lineNotifyService.lineCallAgvPickPod(lineBodyReport);
            }
            //insert line_msg_log
            LineMsgLog lineMsgLog = new LineMsgLog();
            lineMsgLog.setCreatedTime(new Date());
            lineMsgLog.setSendAddr(sendAddr);
            lineMsgLog.setMsgBody(msgBody);
            lineMsgLog.setMsgType(PLC_RECEIVE);
            lineMsgLog.setReqCode(reqCode);
            lineMsgLogMapper.insertSelective(lineMsgLog);
        }


        return "成功";
    }
}
