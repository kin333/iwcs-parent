package com.wisdom.iwcs.service.instock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordConditionDto;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordDTO;

import java.util.List;

public interface IInstockRecordService {
    int insert(InstockRecordDTO record);

    int insertBatch(List<InstockRecordDTO> records);

    InstockRecordDTO selectByPrimaryKey(Integer id);

    List<InstockRecordDTO> selectSelective(InstockRecordDTO record);

    int updateByPrimaryKey(InstockRecordDTO record);

    int updateByPrimaryKeySelective(InstockRecordDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<InstockRecordDTO> selectPage(GridPageRequest gridPageRequest);

    List<InstockRecordDTO> selectInstockRecord(InstockRecordConditionDto instockRecordConditionDto);
}
