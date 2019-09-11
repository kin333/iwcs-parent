package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.SubTaskAction;
import com.wisdom.iwcs.domain.task.dto.SubTaskActionDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SubTaskAction and its DTO SubTaskActionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubTaskActionMapStruct extends EntityMapper<SubTaskActionDTO, SubTaskAction> {

}
