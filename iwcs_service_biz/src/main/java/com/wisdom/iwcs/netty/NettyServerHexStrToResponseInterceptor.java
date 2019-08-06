package com.wisdom.iwcs.netty;

import com.wisdom.iwcs.common.utils.plcUtils.PlcRespone;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerHexStrToResponseInterceptor extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(NettyServerHexStrToResponseInterceptor.class);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("收到消息,16进制返回转为标准基础response类");
        if(msg instanceof String){
            String msgStr = (String) msg;
            PlcRespone plcRespone = new PlcRespone();
            //01 06 01 02 03 04 01 01 01,
            String address = msgStr.substring(0, 2);
            String deviceType = msgStr.substring(2,4);
            int dataLength10 = 1;
            if (deviceType.equals("06")){
                dataLength10 = 18;
            }else {
                dataLength10 = 16;
            }
            String dataBody = msgStr.substring(4,dataLength10);
            plcRespone.setAddress(address.toUpperCase());
            plcRespone.setCommandType(deviceType);
            plcRespone.setReturnBodyBytes(dataBody);
            plcRespone.setReturnBodyByteLength(2*dataLength10);
            ctx.fireChannelRead(plcRespone);
        }else{
            logger.error("PLC返回数据无法解析");
        }
    }
}
