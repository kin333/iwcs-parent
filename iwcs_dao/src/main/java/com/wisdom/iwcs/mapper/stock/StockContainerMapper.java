package com.wisdom.iwcs.mapper.stock;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.stock.StockContainer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:18:51.
 */
@Repository
public interface StockContainerMapper extends DeleteLogicMapper<StockContainer>, MyMapperAndIds<StockContainer> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<StockContainer> selectPage(Map map);
}