package com.wisdom.iwcs.service.base;


import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseWaMapDTO;
import com.wisdom.iwcs.domain.base.dto.MapCodeAndAreaCodeRelationDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:03
 */
public interface IBaseWaMapService {
    int insert(BaseWaMapDTO record);

    int insertBatch(List<BaseWaMapDTO> records);

    BaseWaMapDTO selectByPrimaryKey(Integer id);

    List<BaseWaMapDTO> selectSelective(BaseWaMapDTO record);

    int updateByPrimaryKey(BaseWaMapDTO record);

    int updateByPrimaryKeySelective(BaseWaMapDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseWaMapDTO> selectPage(GridPageRequest gridPageRequest);

    Result configMapCodeAndAreaCodeRelation(MapCodeAndAreaCodeRelationDTO mapCodeAndAreaCodeRelationDTO);
}
