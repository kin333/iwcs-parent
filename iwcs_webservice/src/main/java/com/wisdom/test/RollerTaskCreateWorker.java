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
 * 创建滚筒接料/回收任务信息线程
 * @author han
 */
@Component
public class RollerTaskCreateWorker extends BaseAutoTestWorker{

    private final Logger logger = LoggerFactory.getLogger(RollerTaskCreateWorker.class);
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
     * 创建滚筒接料/回收任务
     */
    @Override
    void createTask() {
        //上料点
        String upPoint = "";
        //上料数量
        int upPointNum = 0;
        //下料点一
        String downPointOne = "";
        //下料点一数量
        int downPointOneNum = 0;
        //下料点一回收数量
        int downPointOneRecycleNum = 0;
        //下料点二
        String downPointTwo = "";
        //下料点二数量
        int downPointTwoNum = 0;
        //下料点二回收数量
        int downPointTwoRecycleNum = 0;
        //回收点
        String recyclePoint = "";
        if (mainTaskMapper.selectStartSupplyAndRecycleTaskCount() < 3) {
        //生成随机数据
        List<BaseMapBerth> baseMapBerths = baseMapBerthMapper.selectAllRollerPoint();

        Random random = new Random();
        HashSet<Integer> hs = new HashSet<>();
        while(hs.size() <4) {
            hs.add(random.nextInt(baseMapBerths.size()));
        }
        Integer[] temp = hs.toArray(new Integer[] {});
        BaseMapBerth upPointBerth = baseMapBerths.get(temp[3]);
        upPoint=upPointBerth.getPointAlias();
            int inpointNumber = random.nextInt(2) + 1;

            if(inpointNumber==2)
            {
                upPointNum=2;
            }
            if(inpointNumber==1) {
                upPointNum = random.nextInt(2) + 1;
            }
        BaseMapBerth downPoint1Berth = baseMapBerths.get(temp[0]);
         downPointOne=downPoint1Berth.getPointAlias();
   if(inpointNumber==2)
   {
       BaseMapBerth downPoint2Berth = baseMapBerths.get(temp[1]);
       downPointTwo = downPoint2Berth.getPointAlias();
   }

        if(inpointNumber==2)
        {
            downPointOneNum = downPointTwoNum = 1;
        }
        if(inpointNumber==1)
        {
               downPointOneNum=upPointNum;
                downPointTwoNum = 0;
        }
        downPointOneRecycleNum =random.nextInt(3) + 0;
        //只有下料点数是2的时候，第二上料才回收空框，否则为默认值零
        if(inpointNumber==2) {
            if(downPointOneRecycleNum == 0)
            {
                downPointTwoRecycleNum = random.nextInt(3) + 0;
            }
            if(downPointOneRecycleNum == 1)
            {
                downPointTwoRecycleNum = random.nextInt(2) + 0;
            }
            if(downPointOneRecycleNum == 2)
            {
                downPointTwoRecycleNum = 0;
            }
        }
        //判断是否设置回收点
        if((downPointOneRecycleNum+downPointTwoRecycleNum)!=0) {
            BaseMapBerth recyclePointBerth = baseMapBerths.get(temp[2]);
            recyclePoint = recyclePointBerth.getPointAlias();
        }
       String  taskcode= CodeBuilder.codeBuilder("M");

        Imitatetest imitateTest= new Imitatetest();
        imitateTest.setTaskcode(taskcode);
        imitateTest.setOutskupoint(upPoint);
        imitateTest.setFeedingquantity(upPointNum);
        imitateTest.setInskupoint1(downPointOne);
        imitateTest.setInskupoint1Inskuquantity(downPointOneNum);
        imitateTest.setInskupoint1Recyclingquantity(downPointOneRecycleNum);
        imitateTest.setInskupoint2(downPointTwo);
        imitateTest.setInskupoint2(downPointTwo);
        imitateTest.setInskupoint2Inskuquantity(downPointTwoNum);
        imitateTest.setInskupoint2Recyclingquantity(downPointTwoRecycleNum);
        imitateTest.setRecyclingpoint(recyclePoint);
        imitateTestMapper.insertSelective(imitateTest);

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
      List<CreateTaskRequest> createTaskRequests =  new ArrayList<CreateTaskRequest>();
        createTaskRequest.setTaskCode(taskcode);
        createTaskRequest.setSupplyLoadWb(upPoint);
        createTaskRequest.setTaskPri("urgent");
       createTaskRequests.add(createTaskRequest);
        MesBaseRequest<List<CreateTaskRequest>> mesBaseRequest=new MesBaseRequest("1001",createTaskRequests);
            lineFeedAndRecycleController.taskCreate(mesBaseRequest);
        } else
            {
           logger.warn("正在执行的滚筒上料回收任务已达到三条");
       }
    }
}