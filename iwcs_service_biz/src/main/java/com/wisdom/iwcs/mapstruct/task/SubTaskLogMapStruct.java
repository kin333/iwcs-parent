package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.SubTaskLog;
import com.wisdom.iwcs.domain.task.dto.SubTaskLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SubTaskLog and its DTO SubTaskLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubTaskLogMapStruct extends EntityMapper<SubTaskLogDTO, SubTaskLog> {

}
