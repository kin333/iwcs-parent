package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.TsTaskGroupItem;
import com.wisdom.iwcs.domain.task.dto.TsTaskGroupItemDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TsTaskGroupItem and its DTO TsTaskGroupItemDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TsTaskGroupItemMapStruct extends EntityMapper<TsTaskGroupItemDTO, TsTaskGroupItem> {

}
