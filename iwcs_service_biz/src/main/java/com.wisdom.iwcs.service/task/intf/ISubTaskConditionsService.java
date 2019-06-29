package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.SubTaskConditionsDTO;

import java.util.List;

public interface ISubTaskConditionsService {
    int insert(SubTaskConditionsDTO record);

    int insertBatch(List<SubTaskConditionsDTO> records);

    SubTaskConditionsDTO selectByPrimaryKey(Integer id);

    List<SubTaskConditionsDTO> selectSelective(SubTaskConditionsDTO record);

    int updateByPrimaryKey(SubTaskConditionsDTO record);

    int updateByPrimaryKeySelective(SubTaskConditionsDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<SubTaskConditionsDTO> selectPage(GridPageRequest gridPageRequest);
}
