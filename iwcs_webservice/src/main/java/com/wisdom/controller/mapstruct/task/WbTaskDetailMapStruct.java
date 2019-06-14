package com.wisdom.controller.mapstruct.task;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.domain.task.dto.WbTaskDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity WbTaskDetail and its DTO WbTaskDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WbTaskDetailMapStruct extends EntityMapper<WbTaskDetailDTO, WbTaskDetail> {

}
