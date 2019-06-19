package com.wisdom.iwcs.service.outstock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordDetailDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:17
 */
public interface IOutstockRecordDetailService {
    int insert(OutstockRecordDetailDTO record);

    int insertBatch(List<OutstockRecordDetailDTO> records);

    OutstockRecordDetailDTO selectByPrimaryKey(Integer id);

    List<OutstockRecordDetailDTO> selectSelective(OutstockRecordDetailDTO record);

    int updateByPrimaryKey(OutstockRecordDetailDTO record);

    int updateByPrimaryKeySelective(OutstockRecordDetailDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<OutstockRecordDetailDTO> selectPage(GridPageRequest gridPageRequest);

    List<OutstockRecordDetailDTO> selectOutstockRecordDetail(Integer outstockRecordId);
}
