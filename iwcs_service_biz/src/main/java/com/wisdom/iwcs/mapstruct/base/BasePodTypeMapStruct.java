package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BasePodType;
import com.wisdom.iwcs.domain.base.dto.BasePodTypeDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodType and its DTO BasePodTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodTypeMapStruct extends EntityMapper<BasePodTypeDTO, BasePodType> {

}
