package com.wisdom.service.outstock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.outstock.dto.OrderActionConfigDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:15
 */
public interface IOrderActionConfigService {
    int insert(OrderActionConfigDTO record);

    int insertBatch(List<OrderActionConfigDTO> records);

    OrderActionConfigDTO selectByPrimaryKey(Integer id);

    List<OrderActionConfigDTO> selectSelective(OrderActionConfigDTO record);

    int updateByPrimaryKey(OrderActionConfigDTO record);

    int updateByPrimaryKeySelective(OrderActionConfigDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<OrderActionConfigDTO> selectPage(GridPageRequest gridPageRequest);
}
