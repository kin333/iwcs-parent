package com.wisdom.iwcs.netty;

import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.ThirdAppConnectionExecption;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 自动门通讯 客户端
 * @Author george
 * @Date 2019/7/13 15:19
 */
public class DoorNettyClient extends BaseNettyClient implements Runnable {
   static Logger logger = LoggerFactory.getLogger(DoorNettyClient.class);

    private static final DoorNettyClient doorNettyClient = new DoorNettyClient();

    public  String host = "10.50.6.50";
    public  int port = 5000;
    private  Channel ch;
    private Bootstrap bootstrap;
    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是    ServerBootstrap。
     **/
    protected void init() {
        logger.info("线体客户端开始配置");

        // 首先，netty通过ServerBootstrap启动服务端
        bootstrap = new Bootstrap();

        //第1步 定义线程组，处理读写和链接事件，没有了accept事件
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group);
        //第2步 绑定客户端通道
        bootstrap.channel(NioSocketChannel.class);
        //第3步 给NIoSocketChannel初始化handler， 处理读写事件
        bootstrap.handler(new NettyClientInitializer(this));
        //连接到远程节点，阻塞等待直到连接完成
        ChannelFuture f = null;
        doConnect();
    }

    @Override
    protected void doConnect(){
        if (ch != null && ch.isActive()) {
            return;
        }
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.addListener(getConnectionListener());
            ch = channelFuture.channel();
             //阻塞，直到channel关闭
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture futureListener) throws Exception {
                    if (futureListener.isSuccess()) {
                        ch = futureListener.channel();
                        logger.warn("Connect to server successfully: {}:{}",host,port);

                        System.out.println("Connect to server successfully!");
                    } else {
                        logger.warn("Failed to connect to server{}:{}, try connect after 10s",host,port);
                        futureListener.channel().eventLoop().schedule(new Runnable() {
                            @Override
                            public void run() {
                                doConnect();
                            }
                        }, 10, TimeUnit.SECONDS);
                    }
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static DoorNettyClient getInstance() {
        if(doorNettyClient.bootstrap == null){
//            doorNettyClient.init();
        }
        return doorNettyClient;
    }


    /**
     * 发送消息
     * @param msg
     */
    public void sendMsg(byte[] msg){
        if(ch.isActive()){
            ByteBuf buf = ch.alloc().buffer(msg.length);
            buf.writeBytes(msg);
            ch.writeAndFlush(buf);
        }else{
            throw new ThirdAppConnectionExecption(ApplicationErrorEnum.THRIDAPP_CONNECTION_LOST);
        }
    }
    /**
     * 发送消息
     * @param msg
     */
    public void sendStringMsg(String msg){
        sendMsg(msg.getBytes());
    }

    @Override
    public void run() {
        init();
    }

}
