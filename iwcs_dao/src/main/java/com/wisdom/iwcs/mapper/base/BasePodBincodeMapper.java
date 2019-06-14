package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BasePodBincode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-20 16:55:13.
 */
@Repository
public interface BasePodBincodeMapper extends DeleteLogicMapper<BasePodBincode>, MyMapperAndIds<BasePodBincode> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BasePodBincode> selectPage(Map map);

    /**
     * 根据podcode逻辑删除信息
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
     * 根据货架、指定层数获取未删除的有效的货架仓位信息
     *
     * @param podCode
     * @param layer
     * @param validFlag
     * @param deletedFlag
     * @return
     */
    List<BasePodBincode> selectByPodCodeAndLayerAndValidFlagAndDeletedFlag(@Param("podCode") String podCode, @Param("layer") Integer layer, @Param("validFlag") Integer validFlag, @Param("deletedFlag") Integer deletedFlag);

    /**
     * 根据货架货架号取bincodes
     */
    List<String> podConvertBincode(List<String> list);

    /**
     * 根据货架号批量更新valid status
     *
     * @param validStatus
     * @param podCodes
     * @return
     */
    int updateValidStatusByPodCodes(@Param("validStatus") Integer validStatus, @Param("podCodes") List<String> podCodes);

    /**
     * 根据仓位号获取未删除的有效的仓位信息
     *
     * @param bincode
     * @param validFlag
     * @param deletedFlag
     * @return
     */
    BasePodBincode selectByBincodeAndValidFlagAndDeletedFlag(@Param("bincode") String bincode, @Param("validFlag") Integer validFlag, @Param("deletedFlag") Integer deletedFlag);

    /**
     * 根据货架号获取未删除的、有效的任意仓位号
     *
     * @param podCode
     * @param validFlag
     * @param deletedFlag
     * @return
     */
    String selectRandomBincodeByPodCodeAndValidFlagAndDeletedFlag(@Param("podCode") String podCode, @Param("validFlag") Integer validFlag, @Param("deletedFlag") Integer deletedFlag);

    /**
     * 根据货架号列表获取未删除的有效的任意仓位号
     *
     * @param podCode
     * @param validFlag
     * @param deletedFlag
     * @return
     */
    List<String> selectRandomBincodeListByPodCodeListAndValidFlagAndDeleteFlag(@Param("podCodes") List<String> podCode, @Param("validFlag") Integer validFlag, @Param("deletedFlag") Integer deletedFlag);

}