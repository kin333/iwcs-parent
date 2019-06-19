package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseWbDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:05
 */
public interface IBaseWbService {
    int insert(BaseWbDTO record);

    int insertBatch(List<BaseWbDTO> records);

    BaseWbDTO selectByPrimaryKey(Integer id);

    List<BaseWbDTO> selectSelective(BaseWbDTO record);

    int updateByPrimaryKey(BaseWbDTO record);

    int updateByPrimaryKeySelective(BaseWbDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseWbDTO> selectPage(GridPageRequest gridPageRequest);

    /**
     * 获取空闲点位信息
     *
     * @return
     */
    Result getFreeWbInfo();
}
