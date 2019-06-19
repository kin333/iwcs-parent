package com.wisdom.iwcs.service.stock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.stock.dto.StockAbnormalAdjustRecordDTO;
import com.wisdom.iwcs.domain.stock.dto.StockAdjustRequestDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/21 15:29
 */
public interface IStockAbnormalAdjustRecordService {
    int insert(StockAbnormalAdjustRecordDTO record);

    int insertBatch(List<StockAbnormalAdjustRecordDTO> records);

    StockAbnormalAdjustRecordDTO selectByPrimaryKey(Integer id);

    List<StockAbnormalAdjustRecordDTO> selectSelective(StockAbnormalAdjustRecordDTO record);

    int updateByPrimaryKey(StockAbnormalAdjustRecordDTO record);

    int updateByPrimaryKeySelective(StockAbnormalAdjustRecordDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<StockAbnormalAdjustRecordDTO> selectPage(GridPageRequest gridPageRequest);

    Result stockAdjust(StockAdjustRequestDTO stockAdjustRequestDTO);
}
