package com.wisdom.iwcs.service.task.conditions.point;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.conditions.pod.IGetPodStrategic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 载具关联获取
 * @author han
 */
@Component
public class RelatedPodStrategic implements IGetPointStrategic{
    private static final Logger logger = LoggerFactory.getLogger(RelatedPodStrategic.class);
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Override
    public String getPoint(AutoCreateBaseInfo autoCreateBaseInfo) {
        logger.info("主任务{}开始进行载具关联策略", autoCreateBaseInfo.getMainTaskNum());
        //查询货架号
        IGetPodStrategic getPointStrategic = AppContext.getBean(autoCreateBaseInfo.getTaskRel().getPodAccess());
        autoCreateBaseInfo.setValue(autoCreateBaseInfo.getTaskRel().getPodAccessValue());
        String podCode = getPointStrategic.getPod(autoCreateBaseInfo);
        //根据货架查询起点
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(podCode);
        logger.info("主任务{}载具关联策略获取完成,返回点位值为{}", autoCreateBaseInfo.getMainTaskNum(), basePodDetail.getBerCode());
        return basePodDetail.getBerCode();
    }
}
