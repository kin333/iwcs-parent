package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.dto.WbAgvTaskDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity WbAgvTask and its DTO WbAgvTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WbAgvTaskMapStruct extends EntityMapper<WbAgvTaskDTO, WbAgvTask> {

}
