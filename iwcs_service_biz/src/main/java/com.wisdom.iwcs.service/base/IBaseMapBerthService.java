package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.BaseMapUpdateAreaDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 15:58
 */
public interface IBaseMapBerthService {
    int insert(BaseMapBerthDTO record);

    int insertBatch(List<BaseMapBerthDTO> records);

    BaseMapBerthDTO selectByPrimaryKey(Integer id);
    BaseMapBerthDTO selectByPointAlias(String pointAlias);

    List<BaseMapBerthDTO> selectSelective(BaseMapBerthDTO record);

    int updateByPrimaryKey(BaseMapBerthDTO record);

    int updateByPrimaryKeySelective(BaseMapBerthDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseMapBerthDTO> selectPage(GridPageRequest gridPageRequest);

    List<BaseMapBerthDTO> selectAlltorageInfo(BaseMapBerthDTO record);

    int updateByBerCode(List<BaseMapBerthDTO> list);

    int updateMapBerthById(List<BaseMapBerthDTO> baseMapBerthDTO);

    List<BaseMapBerth> selectMapDataByMapCode(BaseMapBerth baseMapBerth);

    BaseMapBerth selectMapDataByBerCode(BaseMapBerth baseMapBerth);

    int updateMapById(BaseMapUpdateAreaDTO record);
}
