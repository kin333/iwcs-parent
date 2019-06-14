package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseMapSection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-19 11:41:46.
 */
@Repository
public interface BaseMapSectionMapper extends DeleteLogicMapper<BaseMapSection>, MyMapperAndIds<BaseMapSection> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseMapSection> selectPage(Map map);

    /**
     * 根据存储区代码获取有效未删除存储区信息
     *
     * @param secCode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    BaseMapSection selectBySecCodeAndValidFlagAndDeleteFlag(@Param("secCode") String secCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据地图编号更新库区代码
     *
     * @param mapCode
     * @param areaCode
     * @return
     */
    int updateAreaCodeByMapCode(@Param("mapCode") String mapCode, @Param("areaCode") String areaCode);
}