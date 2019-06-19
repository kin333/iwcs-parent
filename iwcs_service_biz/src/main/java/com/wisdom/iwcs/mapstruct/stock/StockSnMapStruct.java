package com.wisdom.iwcs.mapstruct.stock;

import com.wisdom.iwcs.domain.stock.StockSn;
import com.wisdom.iwcs.domain.stock.dto.StockSnDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity StockSn and its DTO StockSnDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockSnMapStruct extends EntityMapper<StockSnDTO, StockSn> {

}
