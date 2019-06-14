package com.wisdom.iwcs.mapper.instock;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.instock.InstockCallStraPri;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-19 18:41:16.
 */
@Repository
public interface InstockCallStraPriMapper extends DeleteLogicMapper<InstockCallStraPri>, MyMapperAndIds<InstockCallStraPri> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InstockCallStraPri> selectPage(Map map);

    List<InstockCallStraPri> selectStaPriByStraCode(String straCode);
}