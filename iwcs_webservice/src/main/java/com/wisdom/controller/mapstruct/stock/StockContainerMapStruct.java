package com.wisdom.controller.mapstruct.stock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.stock.StockContainer;
import com.wisdom.iwcs.domain.stock.dto.StockContainerDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity StockContainer and its DTO StockContainerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockContainerMapStruct extends EntityMapper<StockContainerDTO, StockContainer> {

}
