package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-20 16:56:28.
 */
@Repository
public interface BaseBincodeDetailMapper extends DeleteLogicMapper<BaseBincodeDetail>, MyMapperAndIds<BaseBincodeDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseBincodeDetail> selectPage(Map map);

    /**
     * 根据podCode逻辑删除货架信息
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
     * 根据bincode获取有效有效未删除、且已经初始化的托盘信息
     *
     * @param bincode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    BaseBincodeDetail selectByBincodeAndValidFlagAndDeleteFlag(@Param("bincode") String bincode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 按照载货状态、货架号统计数量
     *
     * @param podCode
     * @param capacityStatus
     * @return
     */
    int selectCountByPodCodeAndCargoCapacityStatus(@Param("podCode") String podCode, @Param("capacityStatus") String capacityStatus);

    /**
     * 根据货架号统计非该载货状态的数量
     *
     * @param podCode
     * @param capacityStatus
     * @return
     */
    int selectCountByPodCodeAndNotCargoCapacityStatus(@Param("podCode") String podCode, @Param("capacityStatus") String capacityStatus);

    /**
     * 根据货架号更新锁状态
     *
     * @param podCodes
     * @param lockStat
     * @return
     */
    int updatePodLockByPodCodesAndLockStat(@Param("podCodes") List<String> podCodes, @Param("lockStat") String lockStat);

    /**
     * 根据货架号获取未删除的有效货架信息
     *
     * @param podCode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    List<BaseBincodeDetail> selectByPodCodeAndValidFlagAndDeletedFlag(@Param("podCode") String podCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据仓位编号统计非该载货状态的数量
     *
     * @param bincodes
     * @param capacityStatus
     * @return
     */
    int selectCountByBinCodesAndNotCargoCapacityStatus(@Param("bincodes") List<String> bincodes, @Param("capacityStatus") String capacityStatus);

    /**
     * 根据地图编号更新库区代码
     *
     * @param mapCode
     * @param areaCode
     * @return
     */
    int updateAreaCodeByMapCode(@Param("mapCode") String mapCode, @Param("areaCode") String areaCode);

    /**
     * 根据货架号批量更新valid status
     *
     * @param validStatus
     * @param podCodes
     * @return
     */
    int updateValidStatusByPodCodes(@Param("validStatus") Integer validStatus, @Param("podCodes") List<String> podCodes);

    /**
     * 获取未初始化的货架号
     *
     * @return
     */
    List<String> selectBincodesByMapCodeIsNullAndNotValid();

    /**
     * 根据层数、货架号、载货状态统计数量
     *
     * @param podCode
     * @param capacityStatus
     * @param layer
     * @return
     */
    int selectCountByPodCodeAndCargoCapacityStatusAndLayer(@Param("podCode") String podCode, @Param("capacityStatus") String capacityStatus, @Param("layer") Integer layer);

    /**
     * 根据层数、货架号、非载货状态统计数量
     *
     * @param podCode
     * @param capacityStatus
     * @param layer
     * @return
     */
    int selectCountByPodCodeAndNotCargoCapacityStatusAndLayer(@Param("podCode") String podCode, @Param("capacityStatus") String capacityStatus, @Param("layer") Integer layer);

    BaseBincodeDetail selectByBincode(String binCode);

    List<BaseBincodeDetail> selectByPodCode(String podCode);
}