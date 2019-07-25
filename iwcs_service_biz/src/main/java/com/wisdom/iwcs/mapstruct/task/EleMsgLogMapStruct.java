package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.elevator.EleMsgLog;
import com.wisdom.iwcs.domain.elevator.dto.EleMsgLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity EleMsgLog and its DTO EleMsgLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EleMsgLogMapStruct extends EntityMapper<EleMsgLogDTO, EleMsgLog> {

}
