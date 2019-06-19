package com.wisdom.iwcs.mapstruct.instock;

import com.wisdom.iwcs.domain.instock.InstockCallStra;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockCallStra and its DTO InstockCallStraDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockCallStraMapStruct extends EntityMapper<InstockCallStraDTO, InstockCallStra> {

}
