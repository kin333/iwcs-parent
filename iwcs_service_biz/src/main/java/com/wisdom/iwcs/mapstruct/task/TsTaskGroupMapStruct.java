package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.TsTaskGroup;
import com.wisdom.iwcs.domain.task.dto.TsTaskGroupDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TsTaskGroup and its DTO TsTaskGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TsTaskGroupMapStruct extends EntityMapper<TsTaskGroupDTO, TsTaskGroup> {

}
