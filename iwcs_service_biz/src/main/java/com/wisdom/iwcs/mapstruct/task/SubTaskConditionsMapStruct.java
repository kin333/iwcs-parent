package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.SubTaskConditions;
import com.wisdom.iwcs.domain.task.dto.SubTaskConditionsDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TsSubTaskConditions and its DTO TsSubTaskConditionsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubTaskConditionsMapStruct extends EntityMapper<SubTaskConditionsDTO, SubTaskConditions> {

}
