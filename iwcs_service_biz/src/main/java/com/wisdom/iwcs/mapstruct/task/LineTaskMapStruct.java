package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.LineTask;
import com.wisdom.iwcs.domain.task.dto.LineTaskDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity LineTask and its DTO LineTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LineTaskMapStruct extends EntityMapper<LineTaskDTO, LineTask> {

}
