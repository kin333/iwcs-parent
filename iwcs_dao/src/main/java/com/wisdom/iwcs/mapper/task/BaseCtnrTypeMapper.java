package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.BaseCtnrType;
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
}