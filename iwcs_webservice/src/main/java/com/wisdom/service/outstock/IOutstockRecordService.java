package com.wisdom.service.outstock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordConditionDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:18
 */
public interface IOutstockRecordService {
    int insert(OutstockRecordDTO record);

    int insertBatch(List<OutstockRecordDTO> records);

    OutstockRecordDTO selectByPrimaryKey(Integer id);

    List<OutstockRecordDTO> selectSelective(OutstockRecordDTO record);

    int updateByPrimaryKey(OutstockRecordDTO record);

    int updateByPrimaryKeySelective(OutstockRecordDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<OutstockRecordDTO> selectPage(GridPageRequest gridPageRequest);

    List<OutstockRecordDTO> selectOutStockRecord(OutstockRecordConditionDTO outstockRecordConditionDTO);
}
