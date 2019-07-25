package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.EleControlTask;
import com.wisdom.iwcs.domain.task.dto.EleControlTaskDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity EleControlTask and its DTO EleControlTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EleControlTaskMapStruct extends EntityMapper<EleControlTaskDTO, EleControlTask> {

}
