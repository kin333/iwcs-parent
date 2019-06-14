package com.wisdom.iwcs.mapper.instock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.instock.InstockOrder;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderConditionDto;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:26:43.
 */
@Repository
public interface InstockOrderMapper extends MyMapperAndIds<InstockOrder> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InstockOrder> selectPage(Map map);

    InstockOrderDTO checkInstockOrder(String orderNo);

    List<InstockOrder> selectInstockOrder(InstockOrderConditionDto instockOrderConditionDto);

}
