package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BasePodType;
import com.wisdom.iwcs.domain.task.BaseCtnrType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2020-02-06 15:29:14.
 */
@Repository
public interface BaseCtnrTypeMapper extends DeleteLogicMapper<BaseCtnrType>, MyMapperAndIds<BaseCtnrType> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<BaseCtnrType> selectPage(Map map);

    /**
     * 根据容器类型编号获取未删除、有效的货架类型信息
     *
     * @param CtnrTypeCode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    BaseCtnrType selectByCtnrTypeAndValidAndDelete(@Param("CtnrTypeCode") String CtnrTypeCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);
}