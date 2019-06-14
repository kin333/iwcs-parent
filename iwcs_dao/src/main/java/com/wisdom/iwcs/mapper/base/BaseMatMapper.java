package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseMat;
import com.wisdom.iwcs.domain.base.dto.BaseMatDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-19 11:42:09.
 */
@Repository
public interface BaseMatMapper extends DeleteLogicMapper<BaseMat>, MyMapperAndIds<BaseMat> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseMat> selectPage(Map map);

    int updateByMatCode(BaseMatDTO record);

    BaseMat selectByMatCode(String matCode);
}