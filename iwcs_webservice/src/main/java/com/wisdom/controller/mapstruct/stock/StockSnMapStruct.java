package com.wisdom.controller.mapstruct.stock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.stock.StockSn;
import com.wisdom.iwcs.domain.stock.dto.StockSnDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity StockSn and its DTO StockSnDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockSnMapStruct extends EntityMapper<StockSnDTO, StockSn> {

}
