package com.wisdom.iwcs.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * netty 通讯 客户端
 * @Author george
 * @Date 2019/7/13 15:19
 */
public class NettyClient extends  Thread{
    Logger logger = LoggerFactory.getLogger(NettyClient.class);

    public static String host = "127.0.0.1";
    public static int port = 9876;
    private static Channel ch;

    /**
     * 保存连接中的channel,key为id
     */
    private static Map<String, Channel> curConnectedChannelsMap = new ConcurrentHashMap<String, Channel>();
    /**
     *  保存连接中的ChannelHandlerContext
     */
    private static Map<String, ChannelHandlerContext> curConnectedCtxMap = new ConcurrentHashMap<String, ChannelHandlerContext>();

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是    ServerBootstrap。
     **/
    @Override
    public void run() {

        System.out.println("客户端成功启动...");

        // 首先，netty通过ServerBootstrap启动服务端
        Bootstrap client = new Bootstrap();

        //第1步 定义线程组，处理读写和链接事件，没有了accept事件
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);

        //第2步 绑定客户端通道
        client.channel(NioSocketChannel.class);

        //第3步 给NIoSocketChannel初始化handler， 处理读写事件
        client.handler(new NettyClientInitializer());
        //连接到远程节点，阻塞等待直到连接完成
        ChannelFuture f = null;
        try {
            f = client.connect().sync();
            //阻塞，直到channel关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void star() throws IOException{
        String str="Hello Netty";
        ch.writeAndFlush(str);
        System.out.println("客户端发送数据:"+str);
    }

    public static Map<String, Channel> getCurConnectedChannelsMap() {
        return curConnectedChannelsMap;
    }

    public static void setCurConnectedChannelsMap(Map<String, Channel> curConnectedChannelsMap) {
        NettyClient.curConnectedChannelsMap = curConnectedChannelsMap;
    }

    public static Map<String, ChannelHandlerContext> getCurConnectedCtxMap() {
        return curConnectedCtxMap;
    }

    public static void setCurConnectedCtxMap(Map<String, ChannelHandlerContext> curConnectedCtxMap) {
        NettyClient.curConnectedCtxMap = curConnectedCtxMap;
    }
}
