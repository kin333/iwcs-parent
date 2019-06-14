package com.wisdom.service.log;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.log.dto.AgvTaskLogDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 15:52
 */
public interface IAgvTaskLogService {
    int insert(AgvTaskLogDTO record);

    int insertBatch(List<AgvTaskLogDTO> records);

    AgvTaskLogDTO selectByPrimaryKey(Integer id);

    List<AgvTaskLogDTO> selectSelective(AgvTaskLogDTO record);

    int updateByPrimaryKey(AgvTaskLogDTO record);

    int updateByPrimaryKeySelective(AgvTaskLogDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<AgvTaskLogDTO> selectPage(GridPageRequest gridPageRequest);
}
