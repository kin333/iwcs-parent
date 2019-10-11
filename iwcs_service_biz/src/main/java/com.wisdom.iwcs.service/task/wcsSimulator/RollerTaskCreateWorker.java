package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    }
}
