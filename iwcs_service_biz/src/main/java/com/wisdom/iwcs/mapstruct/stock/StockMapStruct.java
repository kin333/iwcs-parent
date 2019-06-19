package com.wisdom.iwcs.mapstruct.stock;

import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.dto.StockDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Stock and its DTO StockDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockMapStruct extends EntityMapper<StockDTO, Stock> {

}
