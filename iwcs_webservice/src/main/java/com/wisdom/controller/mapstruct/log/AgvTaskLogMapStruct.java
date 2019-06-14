package com.wisdom.controller.mapstruct.log;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.log.AgvTaskLog;
import com.wisdom.iwcs.domain.log.dto.AgvTaskLogDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AgvTaskLog and its DTO AgvTaskLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgvTaskLogMapStruct extends EntityMapper<AgvTaskLogDTO, AgvTaskLog> {

}
