package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseMat;
import com.wisdom.iwcs.domain.base.dto.BaseMatDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMat and its DTO BaseMatDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMatMapStruct extends EntityMapper<BaseMatDTO, BaseMat> {

}
