package com.wisdom.iwcs.service.outstock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.outstock.dto.OutstockOrderSnDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:17
 */
public interface IOutstockOrderSnService {
    int insert(OutstockOrderSnDTO record);

    int insertBatch(List<OutstockOrderSnDTO> records);

    OutstockOrderSnDTO selectByPrimaryKey(Integer id);

    List<OutstockOrderSnDTO> selectSelective(OutstockOrderSnDTO record);

    int updateByPrimaryKey(OutstockOrderSnDTO record);

    int updateByPrimaryKeySelective(OutstockOrderSnDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<OutstockOrderSnDTO> selectPage(GridPageRequest gridPageRequest);

    List<OutstockOrderSnDTO> selectOutStockOrderDetailByGenCode(String orderMatGenCode);
}
