package com.wisdom.iwcs.service.task;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.WbTaskDetailDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 15:57
 */
public interface IWbTaskDetailService {
    int insert(WbTaskDetailDTO record);

    int insertBatch(List<WbTaskDetailDTO> records);

    WbTaskDetailDTO selectByPrimaryKey(Integer id);

    List<WbTaskDetailDTO> selectSelective(WbTaskDetailDTO record);

    int updateByPrimaryKey(WbTaskDetailDTO record);

    int updateByPrimaryKeySelective(WbTaskDetailDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<WbTaskDetailDTO> selectPage(GridPageRequest gridPageRequest);

    List<WbTaskDetailDTO> selectAgvTaskDetail(WbTaskDetailDTO record);
}
