package com.wisdom.controller.task;
import com.wisdom.config.AspectConfiguration;
import com.wisdom.controller.upstream.mes.AgvHandlingTaskController;
import com.wisdom.iwcs.domain.mes.ArriveDestWbWaitPortRequest;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.upstream.mes.ConWaitToDestWbRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController()
public class MesnoticeController {
    Logger logger = LoggerFactory.getLogger(AspectConfiguration.class);

     @Autowired
     MesRequestService mesRequestService;
     @Autowired
     MainTaskMapper mainTaskMapper;
     @Autowired
    AgvHandlingTaskController agvHandlingTaskController;
        //到达起点
    @RequestMapping("/N2/http/interface.ms?model=AGV&method=arriveSrcWb")
    MesResult arriveSrcWb()
    {
     logger.info("小车到达起点");
        return new MesResult() ;
    }
    //离开起点
    @RequestMapping("/N2/http/interface.ms?model=AGV&method=leaveSrcWb")
    MesResult leaveSrcWb()
    {
        logger.info("小车离开起点");
        return new MesResult();
    }
    //到达终点
    @RequestMapping("/N2/http/interface.ms?model=AGV&method=arriveDestWb")
    MesResult arriveDestWb()
    {
        logger.info("小车到达终点");
        return new MesResult();
    }
   //AGV到达（机械臂）等待点(通知开/关围栏)
    @RequestMapping("/N2/http/interface.ms?model=AGV&method=arriveDestWbWaitPort")
    MesResult arriveDestWbWaitPort()
    {
        logger.info("小车到达等待点");
        return new MesResult() ;
    }
    //MES通知可从等待点进入终点（围栏已开/围栏已关）
//    @PostMapping("/conWaitToDestWb")
//    MesResult conWaitToDestWb(@RequestBody MesBaseRequest<ArriveDestWbWaitPortRequest> mesBaseRequest)
//    {
//        logger.info("MES接受的数据:{}",mesBaseRequest.getData().toString());
//        ConWaitToDestWbRequest conWaitToDestWbRequest = new ConWaitToDestWbRequest();
//        conWaitToDestWbRequest.setTaskCode(mesBaseRequest.getData().getTaskCode());
//        String taskcode  =conWaitToDestWbRequest.getTaskCode();
//        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(taskcode);
//
//        if(mesBaseRequest.getData().getDoorAction().equals("1"))
//        {
//            conWaitToDestWbRequest.setDoorStatus("1");
//            logger.info("MES设置围栏状态已开");
//        }
//        if(mesBaseRequest.getData().getDoorAction().equals("2"))
//        {
//            conWaitToDestWbRequest.setDoorStatus("2");
//        }
//        MesBaseRequest<ConWaitToDestWbRequest> mesBaseRequest1=new MesBaseRequest("10006",conWaitToDestWbRequest);
//        MesResult mesResult =agvHandlingTaskController.conWaitToDestWb(mesBaseRequest1);
//        // MesResult mesResult = mesRequestService.conWaitToDestWb( conWaitToDestWbRequest,"10006");
//        logger.info("任务{}通知可从等待点进入终点的返回值为：{}",taskcode,mesResult.toString());
//        return  mesResult;
//
//    }
}


