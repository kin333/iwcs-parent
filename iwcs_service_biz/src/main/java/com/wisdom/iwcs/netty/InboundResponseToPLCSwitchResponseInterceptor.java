package com.wisdom.iwcs.netty;

import com.wisdom.iwcs.common.utils.plcUtils.PlcRespone;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InboundResponseToPLCSwitchResponseInterceptor extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(InboundResponseToPLCSwitchResponseInterceptor.class);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("收到消息");
        if(msg instanceof PlcRespone){
            PlcRespone plcRespone = (PlcRespone) msg;
//            PlcSwitchStatusResponse plcSwitchStatusResponse = new PlcSwitchStatusResponse();
//            plcSwitchStatusResponse.setAddress(plcRespone.getAddress());
//            plcSwitchStatusResponse.setCommandType(plcRespone.getCommandType());
//            plcSwitchStatusResponse.setReturnBodyByteLength(plcRespone.getReturnBodyByteLength());
//            plcSwitchStatusResponse.setReturnBodyBytes(plcRespone.getReturnBodyBytes());
//            ctx.fireChannelRead(plcSwitchStatusResponse);
        }else{
            logger.error("PLC返回数据无法解析");
        }
    }
}
