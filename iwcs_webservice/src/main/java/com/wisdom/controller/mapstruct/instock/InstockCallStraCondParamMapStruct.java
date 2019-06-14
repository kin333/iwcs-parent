package com.wisdom.controller.mapstruct.instock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.instock.InstockCallStraCondParam;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraCondParamDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockCallStraCondParam and its DTO InstockCallStraCondParamDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockCallStraCondParamMapStruct extends EntityMapper<InstockCallStraCondParamDTO, InstockCallStraCondParam> {

}
