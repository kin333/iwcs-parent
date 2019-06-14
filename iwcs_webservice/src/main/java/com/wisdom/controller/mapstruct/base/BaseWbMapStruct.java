package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseWb;
import com.wisdom.iwcs.domain.base.dto.BaseWbDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWb and its DTO BaseWbDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWbMapStruct extends EntityMapper<BaseWbDTO, BaseWb> {

}
