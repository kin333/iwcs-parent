package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
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

    @Override
    void createTask() {
        List<BaseMapBerth> PodbaseMapBerths = baseMapBerthMapper.selectPodNormalPoint();
        List<BaseMapBerth> noPodbaseMapBerths = baseMapBerthMapper.selectEmptyPodNormalPoint();
        //生成随机数
        Random random = new Random();
        int startNum = random.nextInt(PodbaseMapBerths.size()-1);
        int endNum = random.nextInt(noPodbaseMapBerths.size()-1);
        BaseMapBerth startBerth = PodbaseMapBerths.get(startNum);
        BaseMapBerth endBerth = noPodbaseMapBerths.get(endNum);
        String podCode=startBerth.getPodCode();
    }
}
