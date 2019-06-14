package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BasePodType;
import com.wisdom.iwcs.domain.base.dto.BasePodTypeDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodType and its DTO BasePodTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodTypeMapStruct extends EntityMapper<BasePodTypeDTO, BasePodType> {

}
