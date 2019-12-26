package com.wisdom.iwcs.service.task.conditions.strategy;

import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.task.PointPodResource;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 锁定点位操作
 * @author han
 */
@Service
public class LockPointHandler implements IStrategyHandler {
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Override
    public void strategyHandler(Object resource) {
        PointPodResource pointPodResource = (PointPodResource)resource;
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(pointPodResource.getPointAlias());
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setBerCode(baseMapBerth.getBerCode());
        lockStorageDto.setLockSource(pointPodResource.getTaskCode());
        lockStorageDto.setMapCode(baseMapBerth.getBerCode().substring(6, 8));
        mapResouceService.lockMapBer(lockStorageDto);
    }
}
