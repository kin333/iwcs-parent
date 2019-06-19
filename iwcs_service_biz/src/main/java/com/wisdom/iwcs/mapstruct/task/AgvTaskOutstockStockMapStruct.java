package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.AgvTaskOutstockStock;
import com.wisdom.iwcs.domain.task.dto.AgvTaskOutstockStockDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AgvTaskOutstockStock and its DTO AgvTaskOutstockStockDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgvTaskOutstockStockMapStruct extends EntityMapper<AgvTaskOutstockStockDTO, AgvTaskOutstockStock> {

}
