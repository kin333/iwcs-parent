package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BasePodType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:01:46.
 */
@Repository
public interface BasePodTypeMapper extends DeleteLogicMapper<BasePodType>, MyMapperAndIds<BasePodType> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BasePodType> selectPage(Map map);

    /**
     * 根据货架类型编号获取未删除、有效的货架类型信息
     *
     * @param podTypeCode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    BasePodType selectByPodTypeCodeAndValidFlagAndDeleteFlag(@Param("podTypeCode") String podTypeCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);
}