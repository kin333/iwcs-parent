package com.wisdom.controller.mapstruct.task;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.task.AgvTaskDetailOutstockProcess;
import com.wisdom.iwcs.domain.task.dto.AgvTaskDetailOutstockProcessDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AgvTaskDetailOutstockProcess and its DTO AgvTaskDetailOutstockProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgvTaskDetailOutstockProcessMapStruct extends EntityMapper<AgvTaskDetailOutstockProcessDTO, AgvTaskDetailOutstockProcess> {

}
