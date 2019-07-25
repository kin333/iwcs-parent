package com.wisdom.iwcs.service.plc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PLC 消息处理
 * @Author george
 * @Date 2019/7/23 9:57
 */
public class PLCControlService {
    Logger logger = LoggerFactory.getLogger(PLCControlService.class);

    public String testService(String str){
        logger.info("BusinessService.testService({})...........", str);
        return str;
    }

//    public String dealAlarmSwitchStatus(PlcSwitchStatusResponse alarmSwitchStatusResponse){
//        logger.info("接收到开关状态返回..........."+ alarmSwitchStatusResponse.getAddress()+":"+alarmSwitchStatusResponse.getReturnBodyBytes());
//        //alarmDataCollection.updateAlarmData(alarmSwitchStatusResponse);
//        return "成功";
//    }
}
