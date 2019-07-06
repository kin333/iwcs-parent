package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.AgvCallback;
import com.wisdom.iwcs.domain.task.dto.AgvCallbackDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AgvCallback and its DTO AgvCallbackDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgvCallbackMapStruct extends EntityMapper<AgvCallbackDTO, AgvCallback> {

}
