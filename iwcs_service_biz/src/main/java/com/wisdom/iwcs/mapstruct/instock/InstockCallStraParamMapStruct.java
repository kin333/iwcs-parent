package com.wisdom.iwcs.mapstruct.instock;

import com.wisdom.iwcs.domain.instock.InstockCallStraParam;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraParamDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockCallStraParam and its DTO InstockCallStraParamDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockCallStraParamMapStruct extends EntityMapper<InstockCallStraParamDTO, InstockCallStraParam> {

}
