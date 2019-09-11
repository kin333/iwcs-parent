package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.TaskRelAction;
import com.wisdom.iwcs.domain.task.dto.TaskRelActionDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TaskRelAction and its DTO TaskRelActionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskRelActionMapStruct extends EntityMapper<TaskRelActionDTO, TaskRelAction> {

}
