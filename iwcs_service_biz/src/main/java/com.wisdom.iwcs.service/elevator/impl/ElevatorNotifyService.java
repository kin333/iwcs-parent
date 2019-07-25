package com.wisdom.iwcs.service.elevator.impl;

import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.plcUtils.CRCUtils;
import com.wisdom.iwcs.common.utils.plcUtils.PlcProtocolUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.elevator.ElevatorReport;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.elevator.EleMsgLogMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 梯控 service
 * @Author george
 * @Date 2019/7/19 14:02
 */
public class ElevatorNotifyService {
    private final Logger logger = LoggerFactory.getLogger(ElevatorNotifyService.class);

    private static Channel ch;

    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private EleMsgLogMapper eleMsgLogMapper;

    /**
     * 自动 筛选可进行上下楼的货架
     * 条件：1楼对应楼层的满料缓存点空
     * 创建电梯任务
     * 创建梯控任务：通知电梯到达目标层
     * @param
     * @return
     */
    public void selectCrossFloorTask(ElevatorReport elevatorReport){

        //查询一楼满料缓存区空闲点位
        BaseMapBerth startBaseMapBerth = baseMapBerthMapper.selectByPointAlias("");
        Preconditions.checkBusinessError(startBaseMapBerth == null, "根据起点点位编号获取点位信息为空");

        //筛选对应楼层的电梯缓存区是否有货架
        logger.info("1楼满料缓存区对应2楼区空，呼叫二楼货架下楼");

        //正常，创建电梯任务，创建调度任务
        return;
    }

    /**
     * agvCallBack调用
     * 货架到达检验点,通知电梯检验
     * @param
     * @return
     */
    public void notifyEleCheckPod(){

    }

    /**
     * 电梯调用，通知wcs
     * 检验成功,进电梯
     * @param
     * @return
     */
    public void eleNotify(){

    }
    /**
     * wcs 调用 通知电梯 agv 离开，
     * 通知电梯，目标楼层，wcs呼叫目标楼层agv
     */
    public void notifyEleAgvLeave(){

    }

    /**
     * wcs 调用 通知电梯
     * 小车到达检验点，是否可进入接货（同货架到达检验点校验）
     */
//    public void notifyEleCheckPod(){
//
//    }

    /**
     * 通知电梯 到达目标楼层
     * @param
     * @return
     */
    private String notifyEleSourceFloorCommandStr(String controllerNo, String controllerType, String floor){
        //获取随机码
        String randomNum = iCommonService.randomHexString(8);
        //写死询命令
        String commandBody = controllerNo + controllerType + randomNum + floor + "00 00";
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s;
        return commandComplete;
    }
    private byte[] notifyEleSourceFloorBinary(String controllerNo, String controllerType, String floor){
        String generatorQueryCommandStr = this.notifyEleSourceFloorCommandStr(controllerNo, controllerType, floor);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * 通知电梯 业务已就绪，可以检验
     * @param
     * @return
     */
    private String notifyEleBizReadyCommandStr(String controllerNo, String controllerType, String floor){
        //获取随机码
        String randomNum = iCommonService.randomHexString(8);
        //写死询命令
        String commandBody = controllerNo + controllerType + randomNum + floor + "00 01";
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s;
        return commandComplete;
    }
    private byte[] notifyEleBizReadyCommandBinary(String controllerNo, String controllerType, String floor){
        String generatorQueryCommandStr = this.notifyEleBizReadyCommandStr(controllerNo, controllerType, floor);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * 通知电梯 AGV已出电梯
     * @param
     * @return
     */
    private String agvLeaveEleCommandStr(String controllerNo, String controllerType, String floor){
        //获取随机码
        String randomNum = iCommonService.randomHexString(8);
        //写死询命令
        String commandBody = controllerNo + controllerType + randomNum + floor + "01 00";
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s;
        return commandComplete;
    }
    private byte[] agvLeaveEleCommandBinary(String controllerNo, String controllerType, String floor){
        String generatorQueryCommandStr = this.agvLeaveEleCommandStr(controllerNo, controllerType, floor);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

    /**
     * 电梯上报返回结果
     * msgStatus 0失败，1成功
     */
    private String eleMsgReturnCommandStr(String controllerNo, String controllerType, String msgStatus, String randomNum){
        //写死询命令
        String commandBody = controllerNo + controllerType + msgStatus + randomNum ;
        byte[] str16Tobyte = CRCUtils.hexStringToBytes(commandBody);
        String s = CRCUtils.Make_CRC(str16Tobyte);
        String commandComplete = commandBody + s;
        return commandComplete;
    }
    private byte[] eleMsgReturnCommandBinary(String controllerNo, String controllerType, String msgStatus, String randomNum){
        String generatorQueryCommandStr = this.eleMsgReturnCommandStr(controllerNo, controllerType, msgStatus, randomNum);
        return PlcProtocolUtils.hexStrToBinaryStr(generatorQueryCommandStr);
    }

}
