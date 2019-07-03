package com.wisdom.iwcs.service.task.impl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.YZConstants;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
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

    /**
     *
     * @param baseMapBerthDTO
     * @return
     */
    @Override
    public Result caculateInspectionAreaEmptyPoint(BaseMapBerthDTO baseMapBerthDTO) {
        if(Strings.isNullOrEmpty(baseMapBerthDTO.getMapCode())) {
            return new Result(400,"缺少地图编码");
        }
        //获取检验点空货架
        baseMapBerthDTO.setBizType("");
        baseMapBerthDTO.setBerthTypeValue("");
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectEmptyStorageOfInspectionArea(baseMapBerthDTO);
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

    /**
     * 锁住选中的点位
     * @param baseMapBerthDTO
     * @return
     */
    @Override
    public Result lockMapBerth(BaseMapBerthDTO baseMapBerthDTO) {
        Result validateResult = validateParams(baseMapBerthDTO);
        if (validateResult.getReturnCode() != 200){
            return validateResult;
        }
        //锁住选中的点位
        baseMapBerthMapper.lockMapBerth(baseMapBerthDTO);
        return new Result();
    }

    /**
     * 解锁选中的点位
     * @param baseMapBerth
     * @return
     */
    @Override
    public Result unlockMapBerth(BaseMapBerthDTO baseMapBerth) {

        Result validateResult = validateParams(baseMapBerth);
        if (validateResult.getReturnCode() != 200){
            return validateResult;
        }
        //解锁选中的点位
        baseMapBerthMapper.unlockMapBerth(baseMapBerth);
        return null;
    }

    /**
     * 获取区域的空闲储位并锁定
     * @param baseMapBerthList
     * @return
     */
    @Override
    public Result lockEmptyStorageByBizTypeList(List<BaseMapBerthDTO> baseMapBerthList) {
        List<BaseMapBerth> lockStorageList = new ArrayList<>();
        for (BaseMapBerthDTO baseMapBerthDTO:baseMapBerthList) {

            //校验参数是否缺失
            if(Strings.isNullOrEmpty(baseMapBerthDTO.getMapCode())) {
                return new Result(400,"缺少地图编码");
            }
            if(Strings.isNullOrEmpty(baseMapBerthDTO.getBizType())) {
                return new Result(400,"缺少区域类型");
            }
            if(Strings.isNullOrEmpty(baseMapBerthDTO.getLockSource())) {
                return new Result(400,"缺少锁定源");
            }

            //根据区域筛选出来空储物列表
            List<BaseMapBerth> selectBaseMapBerths = baseMapBerthMapper.selectEmptyStorage(baseMapBerthDTO);
            if(selectBaseMapBerths.size() == 0) {
                return new Result(400,baseMapBerthDTO.getBizType()+"无空储位");
            }
            BaseMapBerth selectBaseMapBerth = selectBaseMapBerths.get(0);

            //锁住选中的储位
            baseMapBerthDTO.setBerCode(selectBaseMapBerth.getBerCode());
            baseMapBerthMapper.lockMapBerth(baseMapBerthDTO);

            //加入返回列表中
            selectBaseMapBerth.setPodCode(baseMapBerthDTO.getPodCode());
            selectBaseMapBerth.setLockSource(baseMapBerthDTO.getLockSource());
            lockStorageList.add(selectBaseMapBerth);
        }
        return new Result(lockStorageList);
    }

    /**
     * 参数校验
     * @param baseMapBerth
     * @return
     */
    public Result validateParams(BaseMapBerthDTO baseMapBerth) {
        if(Strings.isNullOrEmpty(baseMapBerth.getMapCode())) {
            return new Result(400,"缺少地图编码");
        }
        if(Strings.isNullOrEmpty(baseMapBerth.getBerCode())) {
            return new Result(400,"缺少点位代码");
        }
        if(Strings.isNullOrEmpty(baseMapBerth.getLockSource())) {
            return new Result(400,"缺少锁定源");
        }
        return new Result();
    }




}
