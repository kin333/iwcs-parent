package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.linebody.LineMsgLog;
import com.wisdom.iwcs.domain.linebody.dto.LineMsgLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity LineMsgLog and its DTO LineMsgLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LineMsgLogMapStruct extends EntityMapper<LineMsgLogDTO, LineMsgLog> {

}
