package com.wisdom.iwcs.netty;

import io.netty.channel.*;

import java.util.concurrent.TimeUnit;

/**
 * 连接监听
 */
@ChannelHandler.Sharable
public class ReconnectHandler extends ChannelInboundHandlerAdapter {
    private BaseNettyClient baseNettyClient;
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(String.format("Try to reconnect to the server after channel inactive"));

        final EventLoop eventLoop = ctx.channel().eventLoop();
            eventLoop.schedule(() -> {
                System.out.println("Reconnecting ...");
                baseNettyClient.doConnect();
            }, 3000, TimeUnit.MILLISECONDS);
        ctx.fireChannelInactive();
    }

    public ReconnectHandler(BaseNettyClient baseNettyClient) {
        this.baseNettyClient = baseNettyClient;
    }
}
