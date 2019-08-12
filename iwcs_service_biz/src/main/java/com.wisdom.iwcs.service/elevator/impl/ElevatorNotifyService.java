package com.wisdom.iwcs.service.elevator.impl;

import com.wisdom.iwcs.common.utils.FloorMapEnum;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.plcUtils.CRCUtils;
import com.wisdom.iwcs.common.utils.plcUtils.PlcProtocolUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.control.ContinueTaskRequestDTO;
import com.wisdom.iwcs.domain.elevator.EleMsgLog;
import com.wisdom.iwcs.domain.elevator.Elevator;
import com.wisdom.iwcs.domain.elevator.ElevatorReport;
import com.wisdom.iwcs.domain.task.EleControlTask;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.mapper.elevator.EleMsgLogMapper;
import com.wisdom.iwcs.mapper.elevator.ElevatorMapper;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.netty.ElevatorNettyClient;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.IContinueTaskService;
import com.wisdom.iwcs.service.test.NettyServerReceiverTestService;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskAgvAction.AGV_SEND;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskStatus.*;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PlcMsgType.PLC_SEND;

/**
 * 梯控 service
 * @Author george
 * @Date 2019/7/19 14:02
 */
@Service
public class ElevatorNotifyService {
    private final Logger logger = LoggerFactory.getLogger(ElevatorNotifyService.class);

    private static Channel ch;

    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private ElevatorMapper elevatorMapper;
    @Autowired
    private EleMsgLogMapper eleMsgLogMapper;
    @Autowired
    private EleControlTaskMapper eleControlTaskMapper;
    @Autowired
    private SubTaskConditionMapper subTaskConditionMapper;
    @Autowired
    private IContinueTaskService iContinueTaskService;
    @Autowired
    private NettyServerReceiverTestService nettyServerReceiverTestService;

    /**
     * wcs调用
     * 创建任务时, 通知电梯到达目标层
     * @param
     * @return
     */
    public void selectCrossFloorTask(ElevatorReport elevatorReport){
        //更新梯控任务
        EleControlTask eleControlTask = new EleControlTask();
        eleControlTask.setCallEleArrFloor("1");
        eleControlTaskMapper.updateTaskInfo(eleControlTask);

        //通知电梯到目标楼层
        byte[] arriveCommandBinary= this.notifyEleBinary(elevatorReport.getReqCode(),elevatorReport.getFloor(),"00", "00");
        ElevatorNettyClient elevatorNettyClient = ElevatorNettyClient.getInstance();
        elevatorNettyClient.sendMsg(arriveCommandBinary);

        return;
    }

    /**
     * wcs调用  agvCallBack
     * 货架到达检验点,通知电梯检验
     * 小车到达检验点，是否可进入接货（同货架到达检验点校验）
     * reqCode 梯控任务编号, berCode 地码编号, eleFloor 通知电梯起始楼层source_floor或目标楼层dest_floor待检验
     * @param
     * @return
     */
    public void notifyEleCheckPod(String reqCode, String berCode, String eleFloor){
        //查询那个楼层
        BaseMapBerth startBaseMapBerth = baseMapBerthMapper.selectOneByBercode(berCode);
        Integer floorNum = FloorMapEnum.returnMapValueByType(startBaseMapBerth.getMapCode());
        String floor = "0" +floorNum;

        //更新梯控任务
        EleControlTask eleControlTask = new EleControlTask();
        if ("source_floor".equals(eleFloor)){
            eleControlTask.setWcsNotifyEntrySource("1");
        }else{
            eleControlTask.setWcsNotifyEntryDest("1");
        }
        eleControlTask.setEleTaskCode(reqCode);
        eleControlTaskMapper.updateTaskInfo(eleControlTask);

        //更新电梯表
        Elevator elevator = new Elevator();
        elevator.setStatusUpdateTime(new Date());
        elevatorMapper.updateElevatorInfo(elevator);

        //通知电梯 业务已就绪，可以检验
        byte[] arriveCommandBinary= this.notifyEleBinary(reqCode,floor,"00", "01");
        ElevatorNettyClient elevatorNettyClient = ElevatorNettyClient.getInstance();
        elevatorNettyClient.sendMsg(arriveCommandBinary);

        nettyServerReceiverTestService.enterEle(reqCode,eleFloor);
    }

    /**
     * 电梯调用，
     * 通知wcs 检验结果
     * 成功：进电梯
     * 失败：TODO 等待or 接走
     * @param
     * @return
     */
    public void eleNotifyCheckResult(ElevatorReport elevatorReport){
        //获取楼层
        String floor = elevatorReport.getFloor();
        String eleTaskCode = elevatorReport.getReqCode();
        //查询梯控任务
        EleControlTask eleControlTask = eleControlTaskMapper.selectTaskInfo(eleTaskCode);

        //校验plc传入的楼层是否是货架进的楼层
//        if (Integer.parseInt(floor) != eleControlTask.getSourceFloor()){
//
//        }
        //TODO 不一致处理

        //调用rcs继续执行接口 进电梯任务（从检验点到吊箱）
        ContinueTaskRequestDTO continueTaskRequestDTO = new ContinueTaskRequestDTO();
        continueTaskRequestDTO.setTaskCode(eleControlTask.getMainTaskNum());
        iContinueTaskService.continueTask(continueTaskRequestDTO);

        //更新梯控任务
        if (Integer.valueOf(eleControlTask.getTaskStatus()) < 2){
            //电梯到达起始楼层还未进电梯
            eleControlTask.setTaskStatus(ENTER_ELE);
            eleControlTask.setPlcNotifyEntrySource("1");
        }else{
            //电梯到达目标楼层还未进电梯
            eleControlTask.setTaskStatus(ENTER_ELE);
            eleControlTask.setPlcNotifyEntryDest("1");
        }
        eleControlTaskMapper.updateTaskInfo(eleControlTask);

        //更新电梯表
        Elevator elevator = new Elevator();
        elevator.setStatusUpdateTime(new Date());
        elevator.setCurrentFloor(Integer.valueOf(floor));
        elevator.setFloorUpdateTime(new Date());
        elevatorMapper.updateElevatorInfo(elevator);

        //通知返回
        byte[] eleMsgReturnCommandBinary= this.eleMsgReturnCommandBinary("01",elevatorReport.getReqCode());
        ElevatorNettyClient elevatorNettyClient = ElevatorNettyClient.getInstance();
        elevatorNettyClient.sendMsg(eleMsgReturnCommandBinary);
    }

    /**
     * wcs 调用
     * 接：通知电梯 agv离开
     * 送：通知电梯 agv离开，呼叫目标楼层
     * TODO wcs呼叫目标楼层agv
     * reqCode 梯控任务编号, mapCode 地图编号, agvAction 接/送
     */
    public void notifyEleAgvLeave(String reqCode,String mapCode, String agvAction){

        //根据areaCode查询那个是哪个楼层
        Integer floorNum = FloorMapEnum.returnMapValueByType(mapCode);
        String floor = "0"+floorNum;

        //更新梯控任务
        EleControlTask eleControlTask = new EleControlTask();
        if (AGV_SEND.equals(agvAction)){
            eleControlTask.setDestFloor(floorNum);
            eleControlTask.setTaskStatus(ELE_WORKING);
            eleControlTask.setAgvLeaveSource("1");
        }else{
            eleControlTask.setTaskStatus(OUT_ELE);
            eleControlTask.setAgvLeaveDest("1");
            eleControlTask.setTaskStatus(ELE_TASK_END);
        }
        eleControlTask.setEleTaskCode(reqCode);
        eleControlTaskMapper.updateTaskInfo(eleControlTask);

        //更新电梯表
        Elevator elevator = new Elevator();
        elevator.setStatusUpdateTime(new Date());
        elevatorMapper.updateElevatorInfo(elevator);

        //通知电梯 任务楼层的AGV离开
        byte[] arriveCommandBinary= this.notifyEleBinary(reqCode,floor, "01","00");
        ElevatorNettyClient elevatorNettyClient = ElevatorNettyClient.getInstance();
        elevatorNettyClient.sendMsg(arriveCommandBinary);
    }

    /**
     * 命令
     * 通知电梯
     * 到达目标楼层00 00; 业务已就绪，可以检验00 01;AGV已出电梯01 00
     * @param
     * @return
     */
    private String notifyEleCommandStr(String randomNum, String floor, String agvStatus, String checkPod){
        //TODO 地址、命令码类型做查询，配置在电梯表
        //写死询命令
        String commandBody = "01" + "06" + randomNum + floor + agvStatus + checkPod;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s + "2C";
        //写入日志 msg_log
        this.insertEleMsgLog("01",commandBody,PLC_SEND,randomNum);

        return commandComplete;
    }
    private byte[] notifyEleBinary(String randomNum, String floor, String agvStatus, String checkPod){
        String generatorQueryCommandStr = this.notifyEleCommandStr(randomNum, floor, agvStatus, checkPod);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * 命令
     * 电梯上报返回结果
     * msgStatus 0失败，1成功
     */
    private String eleMsgReturnCommandStr(String msgStatus, String randomNum){
        //TODO 地址、命令码类型做查询，配置在电梯表
        //写死询命令
        String commandBody = "01" + "03" + msgStatus + randomNum ;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s + "2C";
        //写入日志 msg_log
        this.insertEleMsgLog("01",commandBody,PLC_SEND,randomNum);

        return commandComplete;
    }
    private byte[] eleMsgReturnCommandBinary(String msgStatus, String randomNum){
        String generatorQueryCommandStr = this.eleMsgReturnCommandStr(msgStatus, randomNum);
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

}
