package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.SubTaskDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SubTask and its DTO SubTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubTaskMapStruct extends EntityMapper<SubTaskDTO, SubTask> {

}
