package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BaseWhArea;
import com.wisdom.iwcs.domain.base.dto.BaseWhAreaDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWhArea and its DTO BaseWhAreaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWhAreaMapStruct extends EntityMapper<BaseWhAreaDTO, BaseWhArea> {

}
