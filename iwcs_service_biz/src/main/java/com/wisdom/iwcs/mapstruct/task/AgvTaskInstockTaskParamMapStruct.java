package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.AgvTaskInstockTaskParam;
import com.wisdom.iwcs.domain.task.dto.AgvTaskInstockTaskParamDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AgvTaskInstockTaskParam and its DTO AgvTaskInstockTaskParamDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgvTaskInstockTaskParamMapStruct extends EntityMapper<AgvTaskInstockTaskParamDTO, AgvTaskInstockTaskParam> {

}
