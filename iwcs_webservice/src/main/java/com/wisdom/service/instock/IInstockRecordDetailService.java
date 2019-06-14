package com.wisdom.service.instock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordDetailDTO;

import java.util.List;

public interface IInstockRecordDetailService {
    int insert(InstockRecordDetailDTO record);

    int insertBatch(List<InstockRecordDetailDTO> records);

    InstockRecordDetailDTO selectByPrimaryKey(Integer id);

    List<InstockRecordDetailDTO> selectSelective(InstockRecordDetailDTO record);

    int updateByPrimaryKey(InstockRecordDetailDTO record);

    int updateByPrimaryKeySelective(InstockRecordDetailDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<InstockRecordDetailDTO> selectPage(GridPageRequest gridPageRequest);

    List<InstockRecordDetailDTO> selectRecordDetailByRecordId(Integer instockRecordId);


}
