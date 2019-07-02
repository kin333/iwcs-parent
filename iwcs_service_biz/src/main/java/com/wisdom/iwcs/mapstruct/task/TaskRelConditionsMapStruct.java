package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.TaskRelCondition;
import com.wisdom.iwcs.domain.task.dto.TaskRelConditionDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface TaskRelConditionsMapStruct extends EntityMapper<TaskRelConditionDTO, TaskRelCondition> {
}
