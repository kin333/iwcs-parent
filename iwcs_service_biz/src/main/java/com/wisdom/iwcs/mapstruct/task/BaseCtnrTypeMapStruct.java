package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.BaseCtnrType;
import com.wisdom.iwcs.domain.task.dto.BaseCtnrTypeDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseCtnrType and its DTO BaseCtnrTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseCtnrTypeMapStruct extends EntityMapper<BaseCtnrTypeDTO, BaseCtnrType> {

}
