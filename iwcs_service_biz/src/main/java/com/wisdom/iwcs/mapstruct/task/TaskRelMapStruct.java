package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.domain.task.dto.TaskRelDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TaskRel and its DTO TaskRelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskRelMapStruct extends EntityMapper<TaskRelDTO, TaskRel> {

}
