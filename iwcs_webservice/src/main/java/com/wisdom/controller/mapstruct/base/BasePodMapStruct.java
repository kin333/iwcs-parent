package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BasePod;
import com.wisdom.iwcs.domain.base.dto.BasePodDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePod and its DTO BasePodDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodMapStruct extends EntityMapper<BasePodDTO, BasePod> {

}
