package com.wisdom.controller.mapstruct.task;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.task.AgvTaskInstockTaskParam;
import com.wisdom.iwcs.domain.task.dto.AgvTaskInstockTaskParamDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AgvTaskInstockTaskParam and its DTO AgvTaskInstockTaskParamDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgvTaskInstockTaskParamMapStruct extends EntityMapper<AgvTaskInstockTaskParamDTO, AgvTaskInstockTaskParam> {

}
