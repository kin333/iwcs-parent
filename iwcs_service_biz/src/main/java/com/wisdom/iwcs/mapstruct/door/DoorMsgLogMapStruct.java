package com.wisdom.iwcs.mapstruct.door;

import com.wisdom.iwcs.domain.door.DoorMsgLog;
import com.wisdom.iwcs.domain.door.dto.DoorMsgLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity DoorMsgLog and its DTO DoorMsgLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DoorMsgLogMapStruct extends EntityMapper<DoorMsgLogDTO, DoorMsgLog> {

}
