package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.SubConditionEventLog;
import com.wisdom.iwcs.domain.task.dto.SubConditionEventLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SubConditionEventLog and its DTO SubConditionEventLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubConditionEventLogMapStruct extends EntityMapper<SubConditionEventLogDTO, SubConditionEventLog> {

}
