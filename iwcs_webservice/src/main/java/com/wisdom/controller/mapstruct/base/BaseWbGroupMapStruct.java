package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseWbGroup;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWbGroup and its DTO BaseWbGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWbGroupMapStruct extends EntityMapper<BaseWbGroupDTO, BaseWbGroup> {

}
