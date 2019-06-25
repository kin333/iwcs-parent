package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.MainTaskType;
import com.wisdom.iwcs.domain.task.dto.MainTaskTypeDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity MainTaskType and its DTO MainTaskTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MainTaskTypeMapStruct extends EntityMapper<MainTaskTypeDTO, MainTaskType> {

}
