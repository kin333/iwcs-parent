package com.wisdom.iwcs.mapstruct.log;

import com.wisdom.iwcs.domain.log.AgvTaskLog;
import com.wisdom.iwcs.domain.log.dto.AgvTaskLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AgvTaskLog and its DTO AgvTaskLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgvTaskLogMapStruct extends EntityMapper<AgvTaskLogDTO, AgvTaskLog> {

}
