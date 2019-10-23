package com.wisdom.test;

import com.wisdom.controller.upstream.mes.LineFeedAndRecycleController;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.ImitateTestMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * 超越 创建滚筒接料/回收任务信息线程
 * @author
 */
@Component
public class RollerTaskCreateTestWorker extends BaseAutoTestWorker{

    private final Logger logger = LoggerFactory.getLogger(RollerTaskCreateTestWorker.class);
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    ImitateTestMapper imitateTestMapper;
    @Autowired
    TaskCreateService taskCreateService;
    @Autowired
    LineFeedAndRecycleController lineFeedAndRecycleController;
    @Autowired
    MainTaskMapper mainTaskMapper;


    /**
     * 超越 创建滚筒接料/回收任务
     */
    @Override
    void createTask() {
        //上料点(供料点)
        String upPoint = "";
        //下料点（接料点）
        String downPointOne = "";

        //生成随机数据
        List<BaseMapBerth> baseMapBerths = baseMapBerthMapper.selectAllRollerPoint();
        Random random = new Random();

        HashSet<Integer> hs = new HashSet<>();
        while(hs.size() <4) {
            hs.add(random.nextInt(baseMapBerths.size()));
        }


    }
}