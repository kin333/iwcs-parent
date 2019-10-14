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

/**
 * 创建滚筒回收任务信息线程
 */
@Component
public class RollerEmptyTaskCreateWorker extends BaseAutoTestWorker {

    private final Logger logger = LoggerFactory.getLogger(RollerEmptyTaskCreateWorker.class);
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    ImitateTestMapper imitateTestMapper;


    /**
     * 创建滚筒回收任务
     */
    @Override
    void createTask() {
        List<BaseMapBerth> baseMapBerths = baseMapBerthMapper.selectAllRollerPoint();
        //生成随机数
        Random random = new Random();
        int startNum = random.nextInt(baseMapBerths.size());
        int endNum = random.nextInt(baseMapBerths.size() - 1);
        //获取上空框和下空框点位
        BaseMapBerth startBerth = baseMapBerths.get(startNum);
        if (endNum == startNum) {
            endNum ++;
        }
        String startpoint=startBerth.getPointAlias();
        BaseMapBerth endBerth = baseMapBerths.get(endNum);
        String Endpoint =endBerth.getPointAlias();
        //随机生成回收空框数量
        int emptyNum = random.nextInt(2) + 1;
        String  taskcode= CodeBuilder.codeBuilder("M");
        Imitatetest imitateTest= new Imitatetest();
        imitateTest.setTaskcode(taskcode);
        imitateTest.setEmptyboxpoint(startpoint);
        imitateTest.setRecyclingpoint(Endpoint);
        imitateTest.setEmptyboxnumber(emptyNum);
        imitateTestMapper.insertSelective(imitateTest);

    }
}
