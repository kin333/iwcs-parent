package com.wisdom.iwcs.mapstruct.stock;

import com.wisdom.iwcs.domain.stock.StockContainer;
import com.wisdom.iwcs.domain.stock.dto.StockContainerDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity StockContainer and its DTO StockContainerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockContainerMapStruct extends EntityMapper<StockContainerDTO, StockContainer> {

}
