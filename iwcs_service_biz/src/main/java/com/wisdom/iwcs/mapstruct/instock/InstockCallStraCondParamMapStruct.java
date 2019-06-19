package com.wisdom.iwcs.mapstruct.instock;

import com.wisdom.iwcs.domain.instock.InstockCallStraCondParam;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraCondParamDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockCallStraCondParam and its DTO InstockCallStraCondParamDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockCallStraCondParamMapStruct extends EntityMapper<InstockCallStraCondParamDTO, InstockCallStraCondParam> {

}
