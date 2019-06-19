package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BasePod;
import com.wisdom.iwcs.domain.base.dto.BasePodDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePod and its DTO BasePodDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodMapStruct extends EntityMapper<BasePodDTO, BasePod> {

}
