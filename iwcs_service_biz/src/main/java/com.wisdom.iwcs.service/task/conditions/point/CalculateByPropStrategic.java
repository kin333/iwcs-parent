package com.wisdom.iwcs.service.task.conditions.point;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.MapPodFilterStrategy;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.MapPodFilterStrategyMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.service.task.conditions.pod.IGetPodStrategic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 通过属性计算策略
 */
@Component
public class CalculateByPropStrategic implements IGetPointStrategic, IGetPodStrategic {
    private static final Logger logger = LoggerFactory.getLogger(CalculateByPropStrategic.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskRelMapper taskRelMapper;
    @Autowired
    MapPodFilterStrategyMapper mapPodFilterStrategyMapper;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Override
    public String  getPoint(AutoCreateBaseInfo autoCreateBaseInfo) {
        logger.info("主任务{}开始进行通过属性计算策略", autoCreateBaseInfo.getMainTaskNum());

        // 点位
        String point = "";

        String pointAccess = autoCreateBaseInfo.getValue();
        // 判断策略表里各区域是否为空

        List<MapPodFilterStrategy> mapPodFilterStrategy = mapPodFilterStrategyMapper.selectMapPodByCode(pointAccess);
        Preconditions.checkBusinessError(mapPodFilterStrategy == null, "该策略号" + pointAccess + "下无策略");

        for (int i = 0; i < mapPodFilterStrategy.size(); i++) {
            List<BaseMapBerth> baseMapBerth = baseMapBerthMapper.selectMapByAreaCode(mapPodFilterStrategy.get(i));

            if (baseMapBerth.size() != 0) {
                point = baseMapBerth.get(0).getBerCode();
                break;
            }
        }
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(point), "该策略号" + pointAccess
                + "下无可用地码");
        logger.info("主任务{}通过属性计算策略获取完成,返回点位值为{}", autoCreateBaseInfo.getMainTaskNum(), "");
        return point;
    }

    @Override
    public String getPod(AutoCreateBaseInfo autoCreateBaseInfo) {
        String podCode = "";

        String podAccess = autoCreateBaseInfo.getValue();
        List<MapPodFilterStrategy> mapPodFilterStrategies = mapPodFilterStrategyMapper.selectMapPodByCode(podAccess);
        Preconditions.checkBusinessError(mapPodFilterStrategies == null, "该策略号" + podAccess + "下无策略");

        for (int i = 0; i < mapPodFilterStrategies.size(); i++) {

            Integer podStock = mapPodFilterStrategies.get(i).getPodStock();
            List<BaseMapBerth> baseMapBerth = baseMapBerthMapper.selectMapByAreaCode(mapPodFilterStrategies.get(i));
//            根据berCode和podInstock 查询货架表是否存在该货架
            if (baseMapBerth.size() != 0) {
                for (int idx = 0; idx < baseMapBerth.size(); idx++) {
                    String berCode = baseMapBerth.get(i).getBerCode();
                    BasePodDetail basePodDetail = basePodDetailMapper.selectByBerCodeAndStock(berCode, podStock);
                    if (basePodDetail != null) {
                        podCode = basePodDetail.getPodCode();
                        break;
                    }
                }
            }
            // 如果存在这个货架 就停止循环
            if (!Strings.isNullOrEmpty(podCode)) {
                break;
            }
        }
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode), "该策略号" + podAccess
                + "下无可用货架");
        return podCode;
    }

}
