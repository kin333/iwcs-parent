package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.dto.SubTaskConditionDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TsSubTaskConditions and its DTO TsSubTaskConditionsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubTaskConditionMapStruct extends EntityMapper<SubTaskConditionDTO, SubTaskCondition> {

}
