package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.TaskContextDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TaskContext and its DTO TaskContextDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskContextMapStruct extends EntityMapper<TaskContextDTO, TaskContext> {

}
