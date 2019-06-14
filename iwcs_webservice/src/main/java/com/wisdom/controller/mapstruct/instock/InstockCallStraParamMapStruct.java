package com.wisdom.controller.mapstruct.instock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.instock.InstockCallStraParam;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraParamDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockCallStraParam and its DTO InstockCallStraParamDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockCallStraParamMapStruct extends EntityMapper<InstockCallStraParamDTO, InstockCallStraParam> {

}
