package com.wisdom.iwcs.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 
* Title: NettyServerInitializer
* Description: Netty客户端 过滤器
* Version:1.0.0  
* @author pancm
* @date 2017年10月8日
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

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
        //拆包\粘包 inbound 拦截器
        ByteBuf buf = Unpooled.copiedBuffer(",".getBytes());
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new StringEncoder());
        //byte 转为16进制string
        ch.pipeline().addLast(new InboundByteToHexStrInterceptor());
        //16进制返回转为标准基础response类
        //ch.pipeline().addLast(new InboundHexStrToResponseInterceptor());
        //业务处理
        //ch.pipeline().addLast(new NettyClientHandler());
    }
}
