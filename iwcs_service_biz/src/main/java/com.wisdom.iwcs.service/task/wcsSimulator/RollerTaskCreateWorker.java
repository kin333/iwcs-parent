package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
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
        //下料点一回收数量
        int downPointTwoRecycleNum = 0;
        //回收点
        String recyclePoint = "";
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

        upPointNum = random.nextInt(2) + 1;

        BaseMapBerth downPoint1Berth = baseMapBerths.get(temp[0]);
         downPointOne=downPoint1Berth.getPointAlias();
        BaseMapBerth downPoint2Berth = baseMapBerths.get(temp[1]);
         downPointTwo=downPoint2Berth.getPointAlias();
        downPointOneNum =random.nextInt(3) + 0;
        if(downPointOneNum==0)
        {
            downPointTwoNum =upPointNum;
        }
        if(downPointOneNum==1)
        {
            downPointTwoNum = upPointNum-1;
        }
        downPointOneRecycleNum =random.nextInt(3) + 0;
        downPointTwoRecycleNum =random.nextInt(3) + 0;

        if((downPointOneRecycleNum+downPointTwoRecycleNum)!=0)
        {

            BaseMapBerth recyclePointBerth = baseMapBerths.get(temp[2]);
            recyclePoint = recyclePointBerth.getPointAlias();
        }


 }



}