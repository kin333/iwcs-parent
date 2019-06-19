package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BaseWb;
import com.wisdom.iwcs.domain.base.dto.BaseWbDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWb and its DTO BaseWbDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWbMapStruct extends EntityMapper<BaseWbDTO, BaseWb> {

}
