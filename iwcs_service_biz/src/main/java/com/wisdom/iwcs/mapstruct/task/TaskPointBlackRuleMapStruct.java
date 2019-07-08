package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.TaskPointBlackRule;
import com.wisdom.iwcs.domain.task.dto.TaskPointBlackRuleDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TaskPointBlackRule and its DTO TaskPointBlackRuleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskPointBlackRuleMapStruct extends EntityMapper<TaskPointBlackRuleDTO, TaskPointBlackRule> {

}
