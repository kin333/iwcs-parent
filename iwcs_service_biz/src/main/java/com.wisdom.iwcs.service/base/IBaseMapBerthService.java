package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTOD;
import com.wisdom.iwcs.domain.base.dto.BaseMapUpdateAreaDTO;
import com.wisdom.iwcs.domain.base.dto.MapBerthAndPodDetailInfo;

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

    int updatePonitAlise(BaseMapBerthDTO baseMapBerthDTO);

    int updateMapBerthById(List<BaseMapBerthDTO> baseMapBerthDTO);

    List<BaseMapBerth> selectMapDataByMapCode(BaseMapBerth baseMapBerth);

    List<BaseMapBerthDTOD> selectMapList(BaseMapBerth baseMapBerth);

    BaseMapBerth selectMapDataByBerCode(BaseMapBerth baseMapBerth);

    int updateMapById(BaseMapUpdateAreaDTO record);

    Result updateMapByBerCode(BaseMapBerthDTO record);

    Result saveMapPodPosition(String podCode,String pointAlias);

    MapBerthAndPodDetailInfo selectMapDataAndPodInfoByPodCode(String podCode, String pointAlias);

    Result cleanMapPod(String pointAlias);
}
