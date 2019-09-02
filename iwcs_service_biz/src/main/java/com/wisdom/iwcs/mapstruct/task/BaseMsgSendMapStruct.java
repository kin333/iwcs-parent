package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.BaseMsgSend;
import com.wisdom.iwcs.domain.task.dto.BaseMsgSendDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMsgSend and its DTO BaseMsgSendDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMsgSendMapStruct extends EntityMapper<BaseMsgSendDTO, BaseMsgSend> {

}
