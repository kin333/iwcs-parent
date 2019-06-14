package com.wisdom.iwcs.mapper.instock;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.instock.InstockOrderDetail;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailConditionDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:27:56.
 */
@Repository
public interface InstockOrderDetailMapper extends DeleteLogicMapper<InstockOrderDetail>, MyMapperAndIds<InstockOrderDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InstockOrderDetail> selectPage(Map map);

    InstockOrderDetail selectInstockOrderDetail(@Param("orderNo") String orderNo, @Param("subOrderNo") String subOrderNo);

    InstockOrderDetail deleteInstockOrder(@Param("orderNo") String orderNo, List<String> subOrders);

    List<InstockOrderDetail> selectInstockDetail(String orderNo);

    List<InstockOrderDetail> selectInstockOrderAllDetail(InstockOrderDetailConditionDTO instockOrderDetailConditionDTO);
}
