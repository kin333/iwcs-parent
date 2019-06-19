package com.wisdom.iwcs.mapstruct.stock;

import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.dto.StockQueryDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the StockQueryDTO and Stock.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockAdjustQueryVsStockMapStruct extends EntityMapper<StockQueryDTO, Stock> {

}
