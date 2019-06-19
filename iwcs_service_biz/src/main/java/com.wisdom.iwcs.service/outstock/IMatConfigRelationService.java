package com.wisdom.iwcs.service.outstock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.outstock.dto.MatConfigRelationDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:14
 */
public interface IMatConfigRelationService {
    int insert(MatConfigRelationDTO record);

    int insertBatch(List<MatConfigRelationDTO> records);

    MatConfigRelationDTO selectByPrimaryKey(Integer id);

    List<MatConfigRelationDTO> selectSelective(MatConfigRelationDTO record);

    int updateByPrimaryKey(MatConfigRelationDTO record);

    int updateByPrimaryKeySelective(MatConfigRelationDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<MatConfigRelationDTO> selectPage(GridPageRequest gridPageRequest);
}
