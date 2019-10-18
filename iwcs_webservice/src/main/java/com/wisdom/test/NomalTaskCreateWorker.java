package com.wisdom.test;

import com.wisdom.controller.upstream.mes.AgvHandlingTaskController;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.Imitatetest;
import com.wisdom.iwcs.domain.upstream.mes.AgvHandlingTaskCreateRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.ImitateTestMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class NomalTaskCreateWorker extends BaseAutoTestWorker  {
    private final Logger logger = LoggerFactory.getLogger(RollerEmptyTaskCreateWorker.class);
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    ImitateTestMapper imitateTestMapper;
    @Autowired
    AgvHandlingTaskController agvHandlingTaskController;
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Override
    void createTask()
    {
        List<BaseMapBerth> PodbaseMapBerths = baseMapBerthMapper.selectPodNormalPoint();
        List<BaseMapBerth> noPodbaseMapBerths = baseMapBerthMapper.selectEmptyPodNormalPoint();
        //生成随机数
        Random random = new Random();
        int startNum = random.nextInt(PodbaseMapBerths.size());
        int endNum = random.nextInt(noPodbaseMapBerths.size());

       // BaseMapBerth startBerth = baseMapBerthMapper.selectByPointAlias("306");
       BaseMapBerth startBerth = PodbaseMapBerths.get(startNum);
        String startpoint=startBerth.getPointAlias();
        BaseMapBerth endBerth = noPodbaseMapBerths.get(endNum);
        String Endpoint=endBerth.getPointAlias();
        String podCode=startBerth.getPodCode();
        String  taskcode= CodeBuilder.codeBuilder("M");
        Imitatetest imitateTest = new Imitatetest();
        imitateTest.setTaskcode(taskcode);
        imitateTest.setStartingpoint(startpoint);
        imitateTest.setEndpoint(Endpoint);
        imitateTest.setShelfnumber(podCode);
        imitateTestMapper.insertSelective(imitateTest);

        AgvHandlingTaskCreateRequest createTaskRequest = new AgvHandlingTaskCreateRequest();
        List<AgvHandlingTaskCreateRequest> createTaskRequests = new ArrayList<AgvHandlingTaskCreateRequest>();

        createTaskRequest.setTaskCode(taskcode);
        createTaskRequest.setPodCode(podCode);
        createTaskRequest.setSrcWb(startpoint);
        createTaskRequest.setDestWb(Endpoint);
        createTaskRequest.setTaskPri("urgent");
        createTaskRequests.add(createTaskRequest);
        MesBaseRequest<List<AgvHandlingTaskCreateRequest>>  mesBaseRequest=new MesBaseRequest("1002",createTaskRequests);

        if (mainTaskMapper.selectStartUSpTopTaskCount() <3) {
            agvHandlingTaskController.createTask(mesBaseRequest);
        } else {
            logger.warn("正在执行的点到点创建任务已达到三条");
        }
    }
}
