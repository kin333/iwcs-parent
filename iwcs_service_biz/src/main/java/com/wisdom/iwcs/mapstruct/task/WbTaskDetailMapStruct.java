package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.domain.task.dto.WbTaskDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity WbTaskDetail and its DTO WbTaskDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WbTaskDetailMapStruct extends EntityMapper<WbTaskDetailDTO, WbTaskDetail> {

}
