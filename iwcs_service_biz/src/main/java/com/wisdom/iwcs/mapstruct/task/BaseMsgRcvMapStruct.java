package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.BaseMsgRcv;
import com.wisdom.iwcs.domain.task.dto.BaseMsgRcvDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMsgRcv and its DTO BaseMsgRcvDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMsgRcvMapStruct extends EntityMapper<BaseMsgRcvDTO, BaseMsgRcv> {

}
