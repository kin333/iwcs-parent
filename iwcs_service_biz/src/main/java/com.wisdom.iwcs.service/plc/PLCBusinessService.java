package com.wisdom.iwcs.service.plc;

import com.wisdom.iwcs.common.utils.plcUtils.PlcRespone;
import com.wisdom.iwcs.service.test.NettyServerReceiverTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PLCBusinessService {
    Logger logger = LoggerFactory.getLogger(PLCBusinessService.class);

    @Autowired
    private NettyServerReceiverTestService nettyServerReceiverTestService;

    public String dealPlcClientMsg(PlcRespone plcRespone){
        logger.info("接收到PLC Client消息..........."+ plcRespone.getAddress()+":"+plcRespone.getReturnBodyBytes());
        //按地址和命令类型码处理信息
        String sendAddr = plcRespone.getAddress();
        String commandType = plcRespone.getCommandType();
        String msgBody = plcRespone.getReturnBodyBytes();
        String reqCode = msgBody.substring(4,8);
        nettyServerReceiverTestService.plcReceiver(sendAddr,reqCode,commandType,msgBody);
        return "成功";
    }
}
