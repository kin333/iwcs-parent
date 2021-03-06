package com.wisdom.iwcs.service.robot;

import com.wisdom.iwcs.RobotServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RobotServiceThread implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(RobotServiceThread.class);

    @Autowired
    RobotServerSocket robotServerSocket;

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                try {
                    logger.info("监控：socket推送信息到前端");
                    this.wait(1000);
                    robotServerSocket.startSocketServer("192.168.102.96", 8990);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    logger.info("socket推送信息失败:", e);
                    try {
                        this.wait(15 * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
