package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-22 11:09:17.
 */
@Repository
public interface BaseMapBerthMapper extends DeleteLogicMapper<BaseMapBerth>, MyMapperAndIds<BaseMapBerth> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseMapBerth> selectPage(Map map);

    /**
     * 根据地图编号删除地图地码信息
     *
     * @param mapCode
     * @return
     */
    int deleteByMapCode(String mapCode);

    /**
     * 根据元素编号查询
     *
     * @param bercode
     * @return
     */
    BaseMapBerth selectOneByBercode(String bercode);

    List<BaseMapBerth> selectEmptyStorageOfInspectionArea(LockMapBerthCondition  lockMapBerthCondition);

    int lockMapBerth(LockStorageDto lockStorageDto);

    int unlockMapBerth(LockStorageDto lockStorageDto);

    List<BaseMapBerth> selectEmptyStorage(LockMapBerthCondition lockMapBerthCondition);


    List<BaseMapBerth> selectAlltorageByMapCode(String mapCode);

    BaseMapBerth selectBerData(LockStorageDto lockStorageDto);

    BaseMapBerth selectByPointAlias(String pointAlias);

    /**
     * 根据地图编号获取地码列表
     * @param mapCode
     * @return
     */
    List<BaseMapBerth> selectBerthCodeByMapCode(String mapCode);

    /**
     * 批量更新
     * @param baseMapBerthList
     * @return
     */
    int updateList(List<BaseMapBerth> baseMapBerthList);

    /**
     * 批量删除
     * @param berCodeList
     * @param mapCode
     * @return
     */
    int deleteByBerCodeListAndMapCode(@Param("berCodeList") List<String> berCodeList, @Param("mapCode") String mapCode);


    /**
     * 通过berCode更新特定的字段
     * @param baseMapBerthDTO
     * @return
     */
    int updateListByBerCode(BaseMapBerthDTO baseMapBerthDTO);
}