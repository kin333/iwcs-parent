package com.wisdom.test;

import com.wisdom.controller.upstream.mes.EmptyRecyleTaskController;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.Imitatetest;
import com.wisdom.iwcs.domain.upstream.mes.AgvHandlingTaskCreateRequest;
import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
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
    @Autowired
    EmptyRecyleTaskController emptyRecyleTaskController;
    @Autowired
    MainTaskMapper mainTaskMapper;


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
            endNum++;
        }
        String startpoint = startBerth.getPointAlias();
        BaseMapBerth endBerth = baseMapBerths.get(endNum);
        String Endpoint = endBerth.getPointAlias();
        //随机生成回收空框数量
        int emptyNum = random.nextInt(2) + 1;
        String taskcode = CodeBuilder.codeBuilder("M");
        Imitatetest imitateTest = new Imitatetest();
        imitateTest.setTaskcode(taskcode);
        imitateTest.setEmptyboxpoint(startpoint);
        imitateTest.setRecyclingpoint(Endpoint);
        imitateTest.setEmptyboxnumber(emptyNum);
        imitateTestMapper.insertSelective(imitateTest);

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
      List<CreateTaskRequest> createTaskRequests = new ArrayList<CreateTaskRequest>();

        createTaskRequest.setTaskCode(taskcode);
        createTaskRequest.setSrcWbCode(startpoint);
        createTaskRequest.setTargetEmptyRecyleWb(Endpoint);
        createTaskRequest.setEmptyRecyleNum(emptyNum);
        createTaskRequest.setTaskPri("urgent");
        createTaskRequests.add(createTaskRequest);

        MesBaseRequest<List<CreateTaskRequest>> mesBaseRequest = new MesBaseRequest("1000", createTaskRequests);


        if (mainTaskMapper.selectStartEmptyRecycleTask() < 3) {
            emptyRecyleTaskController.taskCreate(mesBaseRequest);
        } else {
            logger.warn("已创建未执行的和正在执行的回收空料箱任务已达到三条");
        }

    }
}
