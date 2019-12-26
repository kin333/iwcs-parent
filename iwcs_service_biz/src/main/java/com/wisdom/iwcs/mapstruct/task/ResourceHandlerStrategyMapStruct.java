package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.ResourceHandlerStrategy;
import com.wisdom.iwcs.domain.task.dto.ResourceHandlerStrategyDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity ResourceHandlerStrategy and its DTO ResourceHandlerStrategyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ResourceHandlerStrategyMapStruct extends EntityMapper<ResourceHandlerStrategyDTO, ResourceHandlerStrategy> {

}
