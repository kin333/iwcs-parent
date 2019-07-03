package com.wisdom.iwcs.service.task.impl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class MapResouceService implements IMapResouceService {

    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;

    @Override
    public Result caculateInspectionAreaEmptyPoint(BaseMapBerth baseMapBerth) {
        if(Strings.isNullOrEmpty(baseMapBerth.getMapCode())) {
            return new Result(400,"缺少地图编码");
        }
        //获取检验点空货架
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectEmptyPointOfInspectionArea(baseMapBerth);
        if(baseMapBerthList.size() <= 0){
            return new Result(400,"检验点暂无空位置");
        }
        //根据获取空位置计算最优位置
        BaseMapBerth emptyPoit=calculatingOptimalLocation(baseMapBerthList);
        return new Result(emptyPoit);
    }

    /**
     * 计算最优的点位(优先选择一组全空的点位，然后再比较距离)
     * @param baseMapBerthList
     * @return
     */
    public BaseMapBerth calculatingOptimalLocation(List<BaseMapBerth> baseMapBerthList) {
        //将空点位进行分组，比较并且筛选出组包含最多点位的点位列表
        List<BaseMapBerth> berGroupList = calculateMaxAndEmptyGroupBer(baseMapBerthList);
        //根据点位坐标计算出最优点位
        BaseMapBerth baseMapBerth = distanceRule(berGroupList);
        return baseMapBerth;
    }

    /**
     * 计算组包含最多点位
     * @param baseMapBerthList
     * @return
     */
    public List<BaseMapBerth> calculateMaxAndEmptyGroupBer(List<BaseMapBerth> baseMapBerthList){
        //进行点位分组
        Map<String,List<BaseMapBerth>> berGroupMap = baseMapBerthList.stream().collect(Collectors.groupingBy(BaseMapBerth::getBerGroup));

        List<BaseMapBerth> berGroupList = new ArrayList<>();

        //计算分组里面最多的点位数
        int max = 0;
        for ( List<BaseMapBerth> mapValue:berGroupMap.values()) {
            if(mapValue.size() > max) {
                max = mapValue.size();
            }
        }
        for ( List<BaseMapBerth> mapValue:berGroupMap.values()) {
            if(mapValue.size() == max) {
                berGroupList.add(mapValue.get(0));
            }
        }
        return berGroupList;
    }

    /**
     * 计算x值最小的位置
     * @param baseMapBerthList
     * @return
     */
    public BaseMapBerth distanceRule(List<BaseMapBerth> baseMapBerthList) {
        Optional<BaseMapBerth> minMapBerth = baseMapBerthList.stream().min((a,b) -> a.getCoox().compareTo(b.getCoox()));
        return minMapBerth.get();
    }



}
