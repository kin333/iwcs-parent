package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.MapPodFilterStrategy;
import com.wisdom.iwcs.domain.task.dto.MapPodFilterStrategyDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity MapPodFilterStrategy and its DTO MapPodFilterStrategyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MapPodFilterStrategyMapStruct extends EntityMapper<MapPodFilterStrategyDTO, MapPodFilterStrategy> {

}
