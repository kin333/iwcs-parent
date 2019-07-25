package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.LineMsgLog;
import com.wisdom.iwcs.domain.task.dto.LineMsgLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity LineMsgLog and its DTO LineMsgLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LineMsgLogMapStruct extends EntityMapper<LineMsgLogDTO, LineMsgLog> {

}
