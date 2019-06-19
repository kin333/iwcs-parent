package com.wisdom.iwcs.service.log;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.log.dto.InterfaceLogDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 15:52
 */
public interface IInterfaceLogService {
    int insert(InterfaceLogDTO record);

    int insertBatch(List<InterfaceLogDTO> records);

    InterfaceLogDTO selectByPrimaryKey(Integer id);

    List<InterfaceLogDTO> selectSelective(InterfaceLogDTO record);

    int updateByPrimaryKey(InterfaceLogDTO record);

    int updateByPrimaryKeySelective(InterfaceLogDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<InterfaceLogDTO> selectPage(GridPageRequest gridPageRequest);
}
