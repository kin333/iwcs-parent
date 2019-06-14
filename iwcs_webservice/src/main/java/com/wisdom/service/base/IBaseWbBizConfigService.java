package com.wisdom.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BaseWbBizConfigDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:04
 */
public interface IBaseWbBizConfigService {
    int insert(BaseWbBizConfigDTO record);

    int insertBatch(List<BaseWbBizConfigDTO> records);

    BaseWbBizConfigDTO selectByPrimaryKey(Integer id);

    List<BaseWbBizConfigDTO> selectSelective(BaseWbBizConfigDTO record);

    int updateByPrimaryKey(BaseWbBizConfigDTO record);

    int updateByPrimaryKeySelective(BaseWbBizConfigDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<BaseWbBizConfigDTO> selectPage(GridPageRequest gridPageRequest);
}
