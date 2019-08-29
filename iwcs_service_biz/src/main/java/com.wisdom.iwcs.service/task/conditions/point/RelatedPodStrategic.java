package com.wisdom.iwcs.service.task.conditions.point;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.conditions.pod.IGetPodStrategic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 载具关联获取
 * @author han
 */
@Component
public class RelatedPodStrategic implements IGetPointStrategic{
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Override
    public String getPoint(AutoCreateBaseInfo autoCreateBaseInfo) {
        //查询货架号
        IGetPodStrategic getPointStrategic = AppContext.getBean(autoCreateBaseInfo.getTaskRel().getPodAccess());
        String podCode = getPointStrategic.getPod(autoCreateBaseInfo);
        //根据货架查询起点
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(podCode);
        return basePodDetail.getBerCode();
    }
}
