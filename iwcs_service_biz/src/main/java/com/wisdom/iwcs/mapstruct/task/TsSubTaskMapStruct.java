package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.TsSubTask;
import com.wisdom.iwcs.domain.task.dto.TsSubTaskDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TsSubTask and its DTO TsSubTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TsSubTaskMapStruct extends EntityMapper<TsSubTaskDTO, TsSubTask> {

}
