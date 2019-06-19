package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BasePodLayerStk;
import com.wisdom.iwcs.domain.base.dto.BasePodLayerStkDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodLayerStk and its DTO BasePodLayerStkDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodLayerStkMapStruct extends EntityMapper<BasePodLayerStkDTO, BasePodLayerStk> {

}
