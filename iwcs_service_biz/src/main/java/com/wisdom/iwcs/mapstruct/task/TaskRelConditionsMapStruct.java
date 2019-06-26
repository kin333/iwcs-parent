package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.TaskRelConditions;
import com.wisdom.iwcs.domain.task.dto.TaskRelConditionsDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface TaskRelConditionsMapStruct extends EntityMapper<TaskRelConditionsDTO, TaskRelConditions> {
}
