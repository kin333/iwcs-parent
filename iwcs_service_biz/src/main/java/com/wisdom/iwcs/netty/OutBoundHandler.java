package com.wisdom.iwcs.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @Author george
 * @Date 2019/8/3 8:39
 */
public class OutBoundHandler extends ChannelOutboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(OutBoundHandler.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof byte[]) {
            byte[] bytesWrite = (byte[])msg;
            ByteBuf buf = ctx.alloc().buffer(bytesWrite.length);
            System.out.println("before:"+buf.memoryAddress());
            System.out.println("before:"+buf.writerIndex());
            //logger.info("向设备下发的信息为："+AlarmProtocolUtils.bytesToHexString(bytesWrite));

            buf.writeBytes(bytesWrite);

            ctx.writeAndFlush(buf).addListener(new ChannelFutureListener(){
                @Override
                public void operationComplete(ChannelFuture future)
                        throws Exception {
                    logger.info("下发成功！");
                }
            });


        }
    }
}
