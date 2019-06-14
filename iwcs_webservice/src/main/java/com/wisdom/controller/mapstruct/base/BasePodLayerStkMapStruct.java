package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BasePodLayerStk;
import com.wisdom.iwcs.domain.base.dto.BasePodLayerStkDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodLayerStk and its DTO BasePodLayerStkDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodLayerStkMapStruct extends EntityMapper<BasePodLayerStkDTO, BasePodLayerStk> {

}
