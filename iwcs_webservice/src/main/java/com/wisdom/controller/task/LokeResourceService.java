package com.wisdom.controller.task;

import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.common.utils.taskUtils.LockUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapstruct.base.BaseMapBerthMapStruct;
import com.wisdom.iwcs.mapstruct.base.BasePodDetailMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LokeResourceService {
    @Autowired
    private  BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private  BaseMapBerthMapStruct baseMapBerthMapStruct;
    @Autowired
    private  BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private  BasePodDetailMapStruct basePodDetailMapStruct;
    /**
     * 批量更新上锁，base_map_berth地图
     *
     * @param records {@link List < BaseMapBerthDTO > }
     * @return int
     */
    public int updateBatchLock(List<BaseMapBerthDTO> records){
        List<BaseMapBerth> recordList = baseMapBerthMapStruct.toEntity(records);
        recordList.forEach(record -> {
            if(records.get(0).getInLock()==1){
                record.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.LOCK.getCode()));
                record.setLockSource(LockUtils.getLockSourceCode());
            }
            else{
                record.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
                record.setLockSource(" ");
            }

        });
        int num = baseMapBerthMapper.updateLockSource(recordList);
        return num;
    }
    /**
     * 批量更新上锁，base_pod_detail货架
     *
     * @param records {@link List < BaseMapBerthDTO > }
     * @return int
     */
    public int updatePodBatchLock(List<BasePodDetailDTO> records){
         List<BasePodDetail> recordList =  basePodDetailMapStruct.toEntity(records);
         recordList.forEach(record -> {
             if(records.get(0).getInLock()==1){
                 record.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.LOCK.getCode()));
                 record.setLockSource(LockUtils.getLockSourceCode());
             }
             else{
                 record.setInLock(Integer.valueOf(CompanyFinancialStatusEnum.NO_LOCK.getCode()));
                 record.setLockSource(" ");
             }

         });
         int num = basePodDetailMapper.updatePodLockSource(recordList);
         return num;
     }
}
