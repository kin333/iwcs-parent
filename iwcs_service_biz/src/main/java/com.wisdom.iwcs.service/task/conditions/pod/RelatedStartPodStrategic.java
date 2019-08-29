package com.wisdom.iwcs.service.task.conditions.pod;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.task.conditions.point.IGetPointStrategic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 起点关联获取策略
 * @author han
 */
@Component
public class RelatedStartPodStrategic implements IGetPodStrategic{
    private static final Logger logger = LoggerFactory.getLogger(RelatedStartPodStrategic.class);

    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Override
    public String getPod(AutoCreateBaseInfo autoCreateBaseInfo) {
        logger.info("主任务{}开始进行起点关联获取策略", autoCreateBaseInfo.getMainTaskNum());
        //首先查询起点
        IGetPointStrategic getPointStrategic = AppContext.getBean(autoCreateBaseInfo.getTaskRel().getStartPointAccess());
        autoCreateBaseInfo.setValue(autoCreateBaseInfo.getTaskRel().getStartPointAccessValue());
        String startPoint = getPointStrategic.getPoint(autoCreateBaseInfo);
        //查询货架
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(startPoint);

        logger.info("主任务{}起点关联获取策略获取完成,返回点位值为{}", autoCreateBaseInfo.getMainTaskNum(), baseMapBerth.getPodCode());
        return baseMapBerth.getPodCode();
    }
}
