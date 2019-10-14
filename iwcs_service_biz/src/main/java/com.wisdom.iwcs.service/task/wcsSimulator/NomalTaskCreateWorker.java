package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.Imitatetest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.ImitateTestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class NomalTaskCreateWorker extends BaseAutoTestWorker  {
    private final Logger logger = LoggerFactory.getLogger(RollerEmptyTaskCreateWorker.class);
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    ImitateTestMapper imitateTestMapper;

    @Override
    void createTask()
    {
        List<BaseMapBerth> PodbaseMapBerths = baseMapBerthMapper.selectPodNormalPoint();
        List<BaseMapBerth> noPodbaseMapBerths = baseMapBerthMapper.selectEmptyPodNormalPoint();
        //生成随机数
        Random random = new Random();
        int startNum = random.nextInt(PodbaseMapBerths.size());
        int endNum = random.nextInt(noPodbaseMapBerths.size());
        BaseMapBerth startBerth = PodbaseMapBerths.get(startNum);
        String startpoint=startBerth.getPointAlias();
        BaseMapBerth endBerth = noPodbaseMapBerths.get(endNum);
        String Endpoint=endBerth.getPointAlias();
        String podCode=startBerth.getPodCode();
        String  taskcode= CodeBuilder.codeBuilder("M");

        Imitatetest imitateTest= new Imitatetest();
        imitateTest.setTaskcode(taskcode);
        imitateTest.setStartingpoint(startpoint);
        imitateTest.setEndpoint(Endpoint);
        imitateTest.setShelfnumber(podCode);
        imitateTestMapper.insertSelective(imitateTest);
    }

}
