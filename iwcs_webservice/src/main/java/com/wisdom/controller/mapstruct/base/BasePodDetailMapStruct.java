package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodDetail and its DTO BasePodDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodDetailMapStruct extends EntityMapper<BasePodDetailDTO, BasePodDetail> {

}
