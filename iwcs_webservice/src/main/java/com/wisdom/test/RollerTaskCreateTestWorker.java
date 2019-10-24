package com.wisdom.test;

import com.wisdom.controller.upstream.mes.LineFeedAndRecycleController;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.Imitatetest;
import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.ImitateTestMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        //下料点回收数量
        int downPointOneRecycleNum = 0;
        //回收点
        String recyclePoint = "";

        //生成随机数据
        List<BaseMapBerth> baseMapBerths = baseMapBerthMapper.selectAllRollerPoint();
        Random random = new Random();

        HashSet<Integer> hs = new HashSet<>();
        while(hs.size() <3) {
            hs.add(random.nextInt(baseMapBerths.size()));
        }
        Integer[] temp = hs.toArray(new Integer[] {});
        BaseMapBerth upPointBerth = baseMapBerths.get(temp[2]);
        upPoint=upPointBerth.getPointAlias();
        BaseMapBerth downPoint1Berth = baseMapBerths.get(temp[0]);
        downPointOne=downPoint1Berth.getPointAlias();
        downPointOneRecycleNum =random.nextInt(3) + 0;
        //判断是否设置回收点
        if(downPointOneRecycleNum!=0) {
            BaseMapBerth recyclePointBerth = baseMapBerths.get(temp[1]);
            recyclePoint = recyclePointBerth.getPointAlias();
        }

        String  taskcode= CodeBuilder.codeBuilder("M");
        Imitatetest imitateTest= new Imitatetest();
        imitateTest.setTaskcode(taskcode);
        imitateTest.setOutskupoint(upPoint);
        imitateTest.setInskupoint1(downPointOne);
        imitateTest.setInskupoint1Recyclingquantity(downPointOneRecycleNum);
        imitateTest.setRecyclingpoint(recyclePoint);
        imitateTestMapper.insertSelective(imitateTest);

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        List<CreateTaskRequest> createTaskRequests =  new ArrayList<CreateTaskRequest>();
        createTaskRequest.setTaskCode(taskcode);
        createTaskRequest.setSupplyLoadWb(upPoint);
        createTaskRequest.setSupplyUnLoadWb(downPointOne);
        createTaskRequest.setTaskPri("urgent");
        createTaskRequests.add(createTaskRequest);
        MesBaseRequest<List<CreateTaskRequest>> mesBaseRequest=new MesBaseRequest("1001",createTaskRequests);

        if (mainTaskMapper.selectStartSupplyAndRecycleTaskCount() < 1) {
            lineFeedAndRecycleController.createTask(mesBaseRequest);
        } else
        {
            logger.warn("正在执行的滚筒上料回收任务");
        }

    }
}