package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseStgType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:02:46.
 */
@Repository
public interface BaseStgTypeMapper extends DeleteLogicMapper<BaseStgType>, MyMapperAndIds<BaseStgType> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseStgType> selectPage(Map map);

    /**
     * 根据存储类型编号获取有效、未删除存储类型信息
     *
     * @param stgTypeCode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    BaseStgType selectByStgTypeCodeAndValidFlagAndDeleteFlag(@Param("stgTypeCode") String stgTypeCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据地图编号更新库区代码
     *
     * @param mapCode
     * @param areaCode
     * @return
     */
    int updateAreaCodeByMapCode(@Param("mapCode") String mapCode, @Param("areaCode") String areaCode);
}