package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BaseMatPackageSpecDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 15:59
 */
public interface IBaseMatPackageSpecService {
    int insert(BaseMatPackageSpecDTO record);

    int insertBatch(List<BaseMatPackageSpecDTO> records);

    BaseMatPackageSpecDTO selectByPrimaryKey(Integer id);

    List<BaseMatPackageSpecDTO> selectSelective(BaseMatPackageSpecDTO record);

    int updateByPrimaryKey(BaseMatPackageSpecDTO record);

    int updateByPrimaryKeySelective(BaseMatPackageSpecDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseMatPackageSpecDTO> selectPage(GridPageRequest gridPageRequest);
}
