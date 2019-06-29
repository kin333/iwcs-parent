package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.WbAgvTaskDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 15:56
 */
public interface IWbAgvTaskService {
    int insert(WbAgvTaskDTO record);

    int insertBatch(List<WbAgvTaskDTO> records);

    WbAgvTaskDTO selectByPrimaryKey(Integer id);

    List<WbAgvTaskDTO> selectSelective(WbAgvTaskDTO record);

    int updateByPrimaryKey(WbAgvTaskDTO record);

    int updateByPrimaryKeySelective(WbAgvTaskDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<WbAgvTaskDTO> selectPage(GridPageRequest gridPageRequest);

    List<WbAgvTaskDTO> selectWbAgvTaskInfo(WbAgvTaskDTO record);

}
