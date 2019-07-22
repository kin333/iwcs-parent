package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.SubConditionRouteKey;
import com.wisdom.iwcs.domain.task.dto.SubConditionRouteKeyDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SubConditionRouteKey and its DTO SubConditionRouteKeyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubConditionRouteKeyMapStruct extends EntityMapper<SubConditionRouteKeyDTO, SubConditionRouteKey> {

}
