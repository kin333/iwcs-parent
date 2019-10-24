package com.wisdom.controller.task;

import com.wisdom.test.BaseAutoTestWorker;
import com.wisdom.test.NomalTaskCreateWorker;
import com.wisdom.test.RollerEmptyTaskCreateWorker;
import com.wisdom.test.RollerTaskCreateWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController1 {
    @Autowired
    NomalTaskCreateWorker  nomalTaskCreateWorker1;
    @Autowired
    RollerEmptyTaskCreateWorker rollerEmptyTaskCreateWorker;
    @Autowired
    RollerTaskCreateWorker rollerTaskCreateWorker;

    @RequestMapping("/haha")
    String  test1() {
        Thread thread1 = new Thread(nomalTaskCreateWorker1);
       Thread thread2=new Thread(rollerEmptyTaskCreateWorker);
        Thread thread3=new Thread(rollerTaskCreateWorker);
        thread1.start();
        thread2.start();
       thread3.start();
      return "ok";
    }
}
