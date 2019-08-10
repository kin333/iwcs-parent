package com.wisdom.iwcs.netty;

import io.jsonwebtoken.lang.Assert;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 
* Title: NettyClientInitializer
* Description: Netty客户端 过滤器
* Version:1.0.0  
* @author pancm
* @date 2017年10月8日
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    private ReconnectHandler reconnectHandler;

    public NettyClientInitializer(BaseNettyClient baseNettyClient){
        Assert.notNull(baseNettyClient,"baseNettyClient can not be null");
        this.reconnectHandler = new ReconnectHandler(baseNettyClient);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        /**
         *
         * @param maxFrameLength 解码时，处理每个帧数据的最大长度
         * @param lengthFieldOffset 该帧数据中，存放该帧数据的长度的数据的起始位置
         * @param lengthFieldLength 记录该帧数据长度的字段本身的长度
         * @param lengthAdjustment 修改帧数据长度字段中定义的值，可以为负数
         * @param initialBytesToStrip 解析的时候需要跳过的字节数
         * @param failFast 为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异常
         */
        //因为服务端设置的超时时间是5秒，所以设置4秒
//        ch.pipeline().addLast( new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
        //拆包\粘包 inbound 拦截器
        ByteBuf buf = Unpooled.copiedBuffer(",".getBytes("UTF-8"));
        ch.pipeline().addLast(reconnectHandler);
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
        ch.pipeline().addLast(new StringEncoder());
        //byte 转为16进制string
        ch.pipeline().addLast(new InboundByteToHexStrInterceptor());
        //16进制返回转为标准基础response类
        ch.pipeline().addLast(new InboundHexStrToResponseInterceptor());
        //业务处理
        ch.pipeline().addLast(new NettyClientHandler());
    }
}
