package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMap;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.domain.task.dto.StrategyHandlerDTO;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.MapPodFilterStrategyMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QueryPointHandler implements IStrategyConditionHandler {

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
    public StrategyHandlerDTO handleCondition(SubTaskCondition subTaskCondition) {

        String startPoint = "";
        String endPoint = "";
        String podCode = "";

        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        String templCode = subTask.getTemplCode();
        TaskRel taskRel = taskRelMapper.selectByTemplCode(templCode);

        // 起点access
        String startAccess = taskRel.getStartPointAccess();
        // 终点access
        String endAccess = taskRel.getEndPointAccess();
        // 货架号access
        String podAccess = taskRel.getPodAccess();

        // 判断策略表里各区域是否为空
        if (!Strings.isNullOrEmpty(startAccess)) {
            startPoint = getStartAndEndPoint(taskRel.getStartPointAccessValue());
        }
        if (!Strings.isNullOrEmpty(endAccess)) {
            endPoint = getStartAndEndPoint(taskRel.getEndPointAccessValue());
        }
        if (!Strings.isNullOrEmpty(podAccess)) {
            podCode = getPodCode(taskRel.getPodAccessValue());
        }

        StrategyHandlerDTO strategyHandlerDTO = new StrategyHandlerDTO();
        strategyHandlerDTO.setStartPoint(startPoint);
        strategyHandlerDTO.setTargetPoint(endPoint);
        strategyHandlerDTO.setPodCode(podCode);

        return strategyHandlerDTO;
    }

    // 计算起点或终点位置
    public String getStartAndEndPoint(String record) {
        List<MapPodFilterStrategy> mapPodFilterStrategy = mapPodFilterStrategyMapper.selectMapPodByCode(record);
        Preconditions.checkBusinessError(mapPodFilterStrategy == null, "该策略号" + record + "下无策略");
        String point = "";
        for (int i = 0; i < mapPodFilterStrategy.size(); i++) {
            List<BaseMapBerth> baseMapBerth = baseMapBerthMapper.selectMapByAreaCode(mapPodFilterStrategy.get(i));

            if (baseMapBerth.size() != 0) {
                point = baseMapBerth.get(1).getBerCode();
                break;
            }
        }
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(point), "该策略号" + record
                + "下无可用地码");
        return point;
    }
    // 计算货架号
    public String getPodCode(String record) {

        String podCode = "";

        List<MapPodFilterStrategy> mapPodFilterStrategies = mapPodFilterStrategyMapper.selectMapPodByCode(record);
        Preconditions.checkBusinessError(mapPodFilterStrategies == null, "该策略号" + record + "下无策略");

        for (int i = 0; i < mapPodFilterStrategies.size(); i++) {
            Integer podStock = mapPodFilterStrategies.get(i).getPodStock();
            List<BaseMapBerth> baseMapBerth = baseMapBerthMapper.selectMapByAreaCode(mapPodFilterStrategies.get(i));
//            根据berCode和podInstock 查询货架表是否存在该货架
            if (baseMapBerth.size() != 0) {
                for (int idx = 0; idx < baseMapBerth.size(); idx++) {
                    String berCode = baseMapBerth.get(i).getBerCode();
                    BasePodDetail basePodDetail = basePodDetailMapper.selectByBerCodeAndStock(berCode, podCode);
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
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(podCode), "该策略号" + record
                + "下无可用货架");
        return podCode;
    }

}
