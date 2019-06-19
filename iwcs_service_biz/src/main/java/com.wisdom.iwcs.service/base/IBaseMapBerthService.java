package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 15:58
 */
public interface IBaseMapBerthService {
    int insert(BaseMapBerthDTO record);

    int insertBatch(List<BaseMapBerthDTO> records);

    BaseMapBerthDTO selectByPrimaryKey(Integer id);

    List<BaseMapBerthDTO> selectSelective(BaseMapBerthDTO record);

    int updateByPrimaryKey(BaseMapBerthDTO record);

    int updateByPrimaryKeySelective(BaseMapBerthDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseMapBerthDTO> selectPage(GridPageRequest gridPageRequest);
}
