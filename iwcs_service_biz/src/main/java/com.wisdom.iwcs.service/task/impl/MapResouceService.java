package com.wisdom.iwcs.service.task.impl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.YZConstants;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.task.intf.IMapResouceService;
import org.jetbrains.annotations.NotNull;
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
     * @param lockMapBerthCondition
     * @return
     */
    @Override
    public Result caculateInspectionAreaEmptyPoint(LockMapBerthCondition lockMapBerthCondition) {
        if(Strings.isNullOrEmpty(lockMapBerthCondition.getMapCode())) {
            return new Result(400,"缺少地图编码");
        }
        //获取检验点空货架
        lockMapBerthCondition.setBizType("");
        lockMapBerthCondition.setBerthTypeValue("");
        lockMapBerthCondition.setBizSecondAreaCode("");
        lockMapBerthCondition.setOperateAreaCode("");
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectEmptyStorageOfInspectionArea(lockMapBerthCondition);
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
     * @param lockStorageDto
     * @return
     */
    @Override
    public Result lockMapBerth(LockStorageDto lockStorageDto) {
        Result validateResult = validateLockParams(lockStorageDto);
        if (validateResult.getReturnCode() != 200){
            return validateResult;
        }
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectBerData(lockStorageDto);
        //锁住选中的点位
        lockStorageDto.setVersion(baseMapBerth.getVersion());
        int count = baseMapBerthMapper.lockMapBerth(lockStorageDto);
        if(count < 1) {
            return new Result(400,"该储位在进行其他操作中，请稍后执行");
        }
        return new Result();
    }



    /**
     * 解锁选中的点位
     * @param lockStorageDto
     * @return
     */
    @Override
    public Result unlockMapBerth(LockStorageDto lockStorageDto) {

        Result validateResult = validateLockParams(lockStorageDto);
        if (validateResult.getReturnCode() != 200){
            return validateResult;
        }
        //乐观锁版本检测
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectBerData(lockStorageDto);
        lockStorageDto.setVersion(baseMapBerth.getVersion());
        //解锁选中的点位
        int count = baseMapBerthMapper.unlockMapBerth(lockStorageDto);
        if(count < 1) {
            return new Result(400,"该储位在进行其他操作中，请稍后执行");
        }
        return new Result();
    }

    /**
     * 获取区域的空闲储位并锁定
     * @param baseMapBerthList
     * @return
     */
    @Override
    public Result lockEmptyStorageByBizTypeList(List<LockMapBerthCondition> baseMapBerthList) {

        LockMapBerthCondition selectLockMapBerthCondition = new LockMapBerthCondition();
        BaseMapBerth selectBaseMapBerth = new BaseMapBerth();

        for (LockMapBerthCondition lockMapBerthCondition:baseMapBerthList) {
            //校验参数是否缺失
            if(Strings.isNullOrEmpty(lockMapBerthCondition.getMapCode())) {
                return new Result(400,"缺少地图编码");
            }
            if(Strings.isNullOrEmpty(lockMapBerthCondition.getBerthTypeValue())) {
                return new Result(400,"缺少berthTypeValue");
            }
            if(Strings.isNullOrEmpty(lockMapBerthCondition.getLockSource())) {
                return new Result(400,"缺少锁定源");
            }

            //根据传入的条件找到符合储位
            List<BaseMapBerth> selectBaseMapBerths = baseMapBerthMapper.selectEmptyStorage(lockMapBerthCondition);
            if(selectBaseMapBerths.size() >= 0) {
                selectLockMapBerthCondition = lockMapBerthCondition;
                selectBaseMapBerth = selectBaseMapBerths.get(0);
                break;
            }
        }
        //锁住选中的储位
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setMapCode(selectBaseMapBerth.getMapCode());
        lockStorageDto.setBerCode(selectBaseMapBerth.getBerCode());
        lockStorageDto.setPodCode(selectLockMapBerthCondition.getPodCode());
        lockStorageDto.setLockSource(selectLockMapBerthCondition.getLockSource());
        Result lockResult = lockMapBerth(lockStorageDto);
        if(lockResult.getReturnCode() != 200) {
            return lockResult;
        }
        return new Result(selectBaseMapBerth);
    }

    /**
     * 参数校验
     * @param baseMapBerth
     * @return
     */
    public Result validateParams(BaseMapBerthDTO baseMapBerth) {
        return getResult(baseMapBerth.getMapCode(), baseMapBerth.getBerCode(), baseMapBerth.getLockSource());
    }

    /**
     * 锁定储位的参数校验
     * @param lockStorageDto
     * @return
     */
    private Result validateLockParams(LockStorageDto lockStorageDto) {
        return getResult(lockStorageDto.getMapCode(), lockStorageDto.getBerCode(), lockStorageDto.getLockSource());
    }


    private Result getResult(String mapCode, String berCode, String lockSource) {
        if(Strings.isNullOrEmpty(mapCode)) {
            return new Result(400,"缺少地图编码");
        }
        if(Strings.isNullOrEmpty(berCode)) {
            return new Result(400,"缺少点位代码");
        }
        if(Strings.isNullOrEmpty(lockSource)) {
            return new Result(400,"缺少锁定源");
        }
        return new Result();
    }


}
