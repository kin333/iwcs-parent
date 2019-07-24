package com.wisdom.iwcs.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InboundByteToHexStrInterceptor extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(InboundByteToHexStrInterceptor.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("收到消息,byte 转为16进制string");
        if(msg instanceof String){
            String str = (String) msg;
            ctx.fireChannelRead(str);

        }else if(msg instanceof ByteBuf){
            ByteBuf btf = (ByteBuf) msg;
            int startIndex = btf.readerIndex();
            int i = btf.readableBytes();
            byte[] bt = new byte[i];
            btf.readBytes(bt);
            if(bt.length > 0){
                String hexStr = ByteBufUtil.hexDump(bt);
                ctx.fireChannelRead(hexStr);
            }
        }
    }
}
