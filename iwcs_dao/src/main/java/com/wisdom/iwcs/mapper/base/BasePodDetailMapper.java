package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.commonDto.fliterCondition.PodFliterCondition;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockPodCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:01:21.
 */
@Repository
public interface BasePodDetailMapper extends DeleteLogicMapper<BasePodDetail>, MyMapperAndIds<BasePodDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BasePodDetail> selectPage(Map map);

    /**
     * 根据货架编号逻辑删除信息
     *
     * @param podCodes
     * @param deleteFlag
     * @return
     */
    int deleteLogicByPodCodes(@Param("podCodes") List<String> podCodes, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据货架编号批量删除
     *
     * @param podCodes
     * @return
     */
    int deleteByPodCodes(List<String> podCodes);

    /**
     * 根据podCode获取未删除的有效货架信息
     *
     * @param podCode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    BasePodDetail selectByPodCodeAndValidFlagAndDeleteFlag(@Param("podCode") String podCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据货架号更新货架任务情况
     *
     * @param podCodes
     * @param lockStatValue
     * @return
     */
    int updatePodTaskLockByPodCodesAndLockStat(@Param("podCodes") List<String> podCodes, @Param("lockStat") Integer lockStatValue);

    List<BasePodDetail> selectByTypeAndAreaCodeAndStkStatus(@Param("podTypeCode") String podTypeCode, @Param("areaCode") String areaCode, @Param("stkStat") String stkStat, @Param("count") Integer count);

    List<BasePodDetail> selectEmptyPodByTypeAndAreaCodeAndStkStatus(@Param("podTypeCode") String podTypeCode, @Param("areaCode") String areaCode, @Param("stkStat") String stkStat, @Param("count") Integer count, @Param("excludeLock") Integer excludeLock);


    /**
     * 根据货架号批量更新valid status
     *
     * @param validStatus
     * @param podCodes
     * @return
     */
    int updateValidStatusByPodCodes(@Param("validStatus") Integer validStatus, @Param("podCodes") List<String> podCodes);

    /**
     * 根据地图编号更新库区代码
     *
     * @param mapCode
     * @param areaCode
     * @return
     */
    int updateAreaCodeByMapCode(@Param("mapCode") String mapCode, @Param("areaCode") String areaCode);

    /**
     * 任务结束时移除任务
     *
     * @param podCodes
     * @param lockStatValue
     * @return
     */
    int removePodTaskLockByPodCodesAndLockStat(@Param("podCodes") List<String> podCodes, @Param("lockStat") Integer lockStatValue);

    /**
     * 根据货架筛选条件筛选货架
     *
     * @param podFliterCondition
     * @return
     */
    List<BasePodDetail> selectPodByPodFliterCon(PodFliterCondition podFliterCondition);

    /**
     * 根据锁定货架的条件查询出符合要求的货架
     * @param lockPodCondition
     * @return
     */
    List<BasePodDetail> selectByLockPodConfigtion(LockPodCondition lockPodCondition);

    /**
     * 根据货架信息的ID锁定货架
     * @param id
     * @param lockSource 锁定源
     * @return
     */
    int lockPod(@Param("id") Integer id,@Param("lockSource") String lockSource);

    /**
     * 根据货架号查询货架信息
     * @param
     * @return
     */
    BasePodDetail selectPodByPodCode(String podCode);
    /**
     * 根据子任务编号解锁货架
     * @param subTaskName
     * @return
     */
    int unlockPod(String subTaskName);

    //更新货架空满
    int updatePodInStock(@Param("podCode") String podCode,@Param("InStock") Integer InStock);
}