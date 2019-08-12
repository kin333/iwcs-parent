package com.wisdom.iwcs.service.task.scheduler;

import org.springframework.stereotype.Component;

@Component
public class TestWcsTaskScheduler extends WcsTaskScheduler {
    @Override
    public void run() {
        // 检查主任务列表，拿到所有可以执行的主任务列表，判断主任务是否可以执行，以主任务当前的子任务是否可以执行为标准
        while (true) {
            this.dispatchMaintask();
            try {
                synchronized (this) {
                    this.wait(10 * 1000 * 1);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
