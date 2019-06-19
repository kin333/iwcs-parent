package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.AgvTaskDetailOutstockProcess;
import com.wisdom.iwcs.domain.task.dto.AgvTaskDetailOutstockProcessDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AgvTaskDetailOutstockProcess and its DTO AgvTaskDetailOutstockProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgvTaskDetailOutstockProcessMapStruct extends EntityMapper<AgvTaskDetailOutstockProcessDTO, AgvTaskDetailOutstockProcess> {

}
