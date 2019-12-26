package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BasePod;
import com.wisdom.iwcs.domain.base.dto.ShowPodInfoCondDTO;
import com.wisdom.iwcs.domain.base.dto.ShowPodInfoResultDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:24:05.
 */
@Repository
public interface BasePodMapper extends DeleteLogicMapper<BasePod>, MyMapperAndIds<BasePod> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BasePod> selectPage(Map map);

    /**
     * 根据podCode批量逻辑删除货架信息
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
     * 根据货架号获取有效未删除货架信息
     *
     * @param podCode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    BasePod selectByPodCodeAndValidFlagAndDeleteFlag(@Param("podCode") String podCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据货架号获取有效未删除货架信息
     *
     * @param dymaticSql
     * @return
     */
    List<BasePod> selectByDymaticSql(@Param("dymaticSql") String dymaticSql);

    /**
     * 根据货架号批量更新valid status
     *
     * @param validStatus
     * @param podCodes
     * @return
     */
    int updateValidStatusByPodCodes(@Param("validStatus") Integer validStatus, @Param("podCodes") List<String> podCodes);

    /**
     * 根据货架号获取货架类型
     *
     * @param podCode
     * @return
     */
    List<String> selectPodTypeCodeByPodCode(String podCode);

    /**
     * 工作台拉取货架信息
     *
     * @param param
     * @return
     */
    List<ShowPodInfoResultDTO> showPodInfo(ShowPodInfoCondDTO param);

    BasePod selectByPodCode(String podCode);
}