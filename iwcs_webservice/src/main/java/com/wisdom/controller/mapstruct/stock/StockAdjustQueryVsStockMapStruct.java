package com.wisdom.controller.mapstruct.stock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.dto.StockQueryDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the StockQueryDTO and Stock.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockAdjustQueryVsStockMapStruct extends EntityMapper<StockQueryDTO, Stock> {

}
