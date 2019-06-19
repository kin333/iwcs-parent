package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDetailDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:04
 */
public interface IBaseWbGroupDetailService {
    int insert(BaseWbGroupDetailDTO record);

    int insertBatch(List<BaseWbGroupDetailDTO> records);

    BaseWbGroupDetailDTO selectByPrimaryKey(Integer id);

    List<BaseWbGroupDetailDTO> selectSelective(BaseWbGroupDetailDTO record);

    int updateByPrimaryKey(BaseWbGroupDetailDTO record);

    int updateByPrimaryKeySelective(BaseWbGroupDetailDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<BaseWbGroupDetailDTO> selectPage(GridPageRequest gridPageRequest);

    List<String> selectMutexTypeWbByWbCode(String wbCode);

    List<String> selectInventoryTypeWbByWbCode(String wbCode);
}
