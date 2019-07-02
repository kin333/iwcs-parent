package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.SubTaskConditionDTO;

import java.util.List;

public interface ISubTaskConditionsService {
    int insert(SubTaskConditionDTO record);

    int insertBatch(List<SubTaskConditionDTO> records);

    SubTaskConditionDTO selectByPrimaryKey(Integer id);

    List<SubTaskConditionDTO> selectSelective(SubTaskConditionDTO record);

    int updateByPrimaryKey(SubTaskConditionDTO record);

    int updateByPrimaryKeySelective(SubTaskConditionDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<SubTaskConditionDTO> selectPage(GridPageRequest gridPageRequest);
}
