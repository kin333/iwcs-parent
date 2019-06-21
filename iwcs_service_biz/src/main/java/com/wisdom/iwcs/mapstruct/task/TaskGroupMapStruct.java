package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.TaskGroup;
import com.wisdom.iwcs.domain.task.dto.TaskGroupDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TaskGroup and its DTO TaskGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskGroupMapStruct extends EntityMapper<TaskGroupDTO, TaskGroup> {

}
