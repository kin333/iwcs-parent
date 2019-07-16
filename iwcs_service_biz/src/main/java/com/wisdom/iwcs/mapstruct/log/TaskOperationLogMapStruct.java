package com.wisdom.iwcs.mapstruct.log;

import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.log.dto.TaskOperationLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TaskOperationLog and its DTO TaskOperationLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskOperationLogMapStruct extends EntityMapper<TaskOperationLogDTO, TaskOperationLog> {

}
