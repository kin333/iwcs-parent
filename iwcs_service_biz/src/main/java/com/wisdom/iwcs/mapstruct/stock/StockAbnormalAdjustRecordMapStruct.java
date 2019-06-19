package com.wisdom.iwcs.mapstruct.stock;

import com.wisdom.iwcs.domain.stock.StockAbnormalAdjustRecord;
import com.wisdom.iwcs.domain.stock.dto.StockAbnormalAdjustRecordDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity StockAbnormalAdjustRecord and its DTO StockAbnormalAdjustRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockAbnormalAdjustRecordMapStruct extends EntityMapper<StockAbnormalAdjustRecordDTO, StockAbnormalAdjustRecord> {

}
