package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BaseWbGroup;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWbGroup and its DTO BaseWbGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWbGroupMapStruct extends EntityMapper<BaseWbGroupDTO, BaseWbGroup> {

}
