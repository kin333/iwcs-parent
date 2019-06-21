package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.TaskGroupItem;
import com.wisdom.iwcs.domain.task.dto.TaskGroupItemDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TaskGroupItem and its DTO TaskGroupItemDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskGroupItemMapStruct extends EntityMapper<TaskGroupItemDTO, TaskGroupItem> {

}
