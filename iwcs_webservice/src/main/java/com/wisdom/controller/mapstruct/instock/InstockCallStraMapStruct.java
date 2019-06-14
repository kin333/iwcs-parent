package com.wisdom.controller.mapstruct.instock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.instock.InstockCallStra;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockCallStra and its DTO InstockCallStraDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockCallStraMapStruct extends EntityMapper<InstockCallStraDTO, InstockCallStra> {

}
