package com.wisdom.iwcs.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 
* Title: NettyClientHandler
* Description: 客户端业务逻辑实现
* Version:1.0.0  
* @author pancm
* @date 2017年10月8日
 */
public class NettyClientHandler extends  ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    /** 客户端请求的心跳命令  */
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hb_request",  
            CharsetUtil.UTF_8));  

    /** 空闲次数 */
    private int idle_count = 1; 

    /** 发送次数 */
    private int count = 1;  

    /**循环次数 */ 
    private int fcount = 1;  

     /**
     * 建立连接时
     */
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("建立连接时："+new Date());
        ctx.fireChannelActive();
        logger.info("服务端[{}]连接", ctx.channel().id());
        logger.info("CLIENT"+getRemoteAddress(ctx)+" 接入连接");
        //往channel map中添加channel信息
        NettyClient.getCurConnectedChannelsMap().put(getIPString(ctx), ctx.channel());
        NettyClient.getCurConnectedCtxMap().put(getIPString(ctx), ctx);
    }  

     /**
      * 关闭连接时
      */
    @Override  
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("关闭连接时："+new Date());
        logger.info("服务端[{}]断开", ctx.channel().id());
        //删除Channel Map中的失效Client
        NettyClient.getCurConnectedChannelsMap().remove(getIPString(ctx));
    }  

    /**
     * 心跳请求处理
     * 每4秒发送一次心跳请求;
     * 
     */
    @Override  
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        logger.info("循环请求的时间："+new Date()+"，次数"+fcount);
        if (obj instanceof IdleStateEvent) {   
            IdleStateEvent event = (IdleStateEvent) obj;
            //如果写通道处于空闲状态,就发送心跳命令
            if (IdleState.WRITER_IDLE.equals(event.state())) {
                //设置发送次数
                if(idle_count <= 3){
                    idle_count++;  
                    ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());  
                }else{  
                    System.out.println("不再发送心跳请求了！");
                }
                fcount++;
            }  
        }  
    }  

    /**
     * 业务逻辑处理   
     */
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        System.out.println("第"+count+"次"+",客户端接受的消息:"+msg);
        count++;
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
}
