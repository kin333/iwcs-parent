package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BasePodDetail and its DTO BasePodDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasePodDetailMapStruct extends EntityMapper<BasePodDetailDTO, BasePodDetail> {

}
