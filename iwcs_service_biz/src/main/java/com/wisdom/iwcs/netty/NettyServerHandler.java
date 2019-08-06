package com.wisdom.iwcs.netty;

import com.wisdom.iwcs.common.utils.ApplicationContextUtils;
import com.wisdom.iwcs.common.utils.plcUtils.PlcRespone;
import com.wisdom.iwcs.service.plc.PLCBusinessService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("客户端[{}]连接", ctx.channel().id());
        logger.info("CLIENT"+getRemoteAddress(ctx)+" 接入连接");
        //往channel map中添加channel信息
        NettyServer.getCurConnectedChannelsMap().put(getIPString(ctx), ctx.channel());
        NettyServer.getCurConnectedCtxMap().put(getIPString(ctx), ctx);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.info("客户端[{}]断开", ctx.channel().id());
        //删除Channel Map中的失效Client
        NettyServer.getCurConnectedChannelsMap().remove(getIPString(ctx));
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        PLCBusinessService plcBusinessService = ApplicationContextUtils.getBean("plcBusinessService", PLCBusinessService.class);
        if(msg instanceof PlcRespone){
            plcBusinessService.dealPlcClientMsg((PlcRespone)msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.info(ctx.channel().id() + " : " + cause.getMessage());
    }

    public static String getIPString(ChannelHandlerContext ctx){
        String ipString = "";
        String socketString = ctx.channel().remoteAddress().toString();
        int colonAt = socketString.indexOf(":");
        ipString = socketString.substring(1, colonAt);
        return ipString;
    }


    public static String getRemoteAddress(ChannelHandlerContext ctx){
        String socketString = "";
        socketString = ctx.channel().remoteAddress().toString();
        return socketString;
    }


    private String getKeyFromArray(byte[] addressDomain) {
        StringBuffer sBuffer = new StringBuffer();
        for(int i=0;i<5;i++){
            sBuffer.append(addressDomain[i]);
        }
        return sBuffer.toString();
    }

    protected String to8BitString(String binaryString) {
        int len = binaryString.length();
        for (int i = 0; i < 8-len; i++) {
            binaryString = "0"+binaryString;
        }
        return binaryString;
    }

    protected static byte[] combine2Byte(byte[] bt1, byte[] bt2){
        byte[] byteResult = new byte[bt1.length+bt2.length];
        System.arraycopy(bt1, 0, byteResult, 0, bt1.length);
        System.arraycopy(bt2, 0, byteResult, bt1.length, bt2.length);
        return byteResult;
    }

}
