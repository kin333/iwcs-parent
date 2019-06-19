package com.wisdom.iwcs.service.instock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailConditionDTO;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailDTO;

import java.util.List;

public interface IInstockOrderDetailService {
    int insert(InstockOrderDetailDTO record);

    int insertBatch(List<InstockOrderDetailDTO> records);

    InstockOrderDetailDTO selectByPrimaryKey(Integer id);

    List<InstockOrderDetailDTO> selectSelective(InstockOrderDetailDTO record);

    int updateByPrimaryKey(InstockOrderDetailDTO record);

    int updateByPrimaryKeySelective(InstockOrderDetailDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<InstockOrderDetailDTO> selectPage(GridPageRequest gridPageRequest);

    List<InstockOrderDetailDTO> selectInstockOrderDetail(String orderNo);

    List<InstockOrderDetailDTO> selectInstockOrderAllDetail(InstockOrderDetailConditionDTO instockOrderDetailConditionDTO);
}
