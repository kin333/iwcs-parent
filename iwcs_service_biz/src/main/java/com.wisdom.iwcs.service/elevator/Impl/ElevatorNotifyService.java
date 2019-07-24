package com.wisdom.iwcs.service.elevator.Impl;

import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.line.LineNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 梯控 service
 * @Author george
 * @Date 2019/7/19 14:02
 */
public class ElevatorNotifyService {
    private final Logger logger = LoggerFactory.getLogger(ElevatorNotifyService.class);

    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;

    /**
     * 筛选可进行上下楼的货架
     * 条件：1楼对应楼层的满料缓存点空
     * @param
     * @return
     */
    public void selectCrossFloorTask(){

        //查询一楼满料缓存区空闲点位
        BaseMapBerth startBaseMapBerth = baseMapBerthMapper.selectByPointAlias("");
        Preconditions.checkBusinessError(startBaseMapBerth == null, "根据起点点位编号获取点位信息为空");

        //筛选对应楼层的电梯缓存区是否有货架
        logger.info("1楼满料缓存区对应2楼区空，呼叫二楼货架下楼");

        //正常，创建电梯任务，创建调度任务
        return;
    }

    /**
     * 货架到达检验点
     * @param
     * @return
     */
    public void y(){}

}
