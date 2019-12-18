package com.wisdom.iwcs;

import com.wisdom.iwcs.WebSocketServer;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.Socket;
import java.util.Date;

/**
 * nio socket服务端
 */
@Component
public class RobotServerSocket {
    WebSocketServer webSocketServer = new WebSocketServer();
    public String strPool = "";
    public long lastReceiveTime = 0;
    public void startSocketServer(String ip, Integer port) {
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("客户端启动成功");

            // 向服务端程序发送数据

            socket.getOutputStream().write(hexTobytes("04 19 05 52 45 41 44 59 0B 53 6F 63 6B 65 74 2D 54 79 70 65 00 00 00 03 53 55 42"));
            socket.getOutputStream().write(hexTobytes("04 19 05 52 45 41 44 59 0B 53 6F 63 6B 65 74 2D 54 79 70 65 00 00 00 03 53 55 42"));
            socket.getOutputStream().write(hexTobytes("04 19 05 52 45 41 44 59 0B 53 6F 63 6B 65 74 2D 54 79 70 65 00 00 00 03 53 55 42"));

InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024 * 1024 * 2];
            Integer len;
            while (true) {
                try {
                    this.wait(1 * 1000);
                    len = inputStream.read(bytes);
                    //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                    if (len == 0) {
                        long nowTime = new Date().getTime() / 1000;
                        if (nowTime - lastReceiveTime > 2) {
                            socket.getOutputStream().write(hexTobytes("04 19 05 52 45 41 44 59 0B 53 6F 63 6B 65 74 2D 54 79 70 65 00 00 00 03 53 55 42"));
                            System.out.println("数据中断" + nowTime);
                            System.out.println("重新连接");
                        }
                    } else {
                        lastReceiveTime = new Date().getTime() / 1000;
                    }
                    formatData(new String(bytes, 0, len,"UTF-8"));
                } catch (Exception e)
                {
                    socket = new Socket(ip, port);
                    System.out.println("异常");
                }
            }

            //inputStream.close();
            //socket.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public byte[] hexTobytes(String hex) {
        hex = hex.replace(" ", "");
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i = i + 2) {
            String subStr = hex.substring(i, i + 2);
            boolean flag = false;
            int intH = Integer.parseInt(subStr, 16);
            if (intH > 127) flag = true;
            if (intH == 128) {
                intH = -128;
            } else if (flag) {
                intH = 0 - (intH & 0x7F);
            }
            byte b = (byte) intH;
            bytes[i / 2] = b;
        }
        return bytes;
    }

    private void formatData(String newStr) {
        byte[] zero = new byte[1];
        zero[0] = (byte) 0;
        String s = new String(zero);
        newStr = newStr.replace(s, "");
        strPool += newStr.replaceAll("(\\r|\\n|[\\u0002-\\u000e])", " ");
        Integer i, j;
        while ((i = this.strPool.indexOf("<?xml version=")) != -1 && (j = this.strPool.indexOf("</Message>")) != -1 && this.strPool.indexOf("<?xml version=") < j)
        {
            String result = strPool.substring(i, j + 10);
            // send to wss:
            strPool = strPool.substring(j + 10);
            try {
                webSocketServer.sendInfo(result);
            } catch (Exception e) {
                continue;
            }

            //System.out.println("处理数据:" + result.length());
        }
    }
}