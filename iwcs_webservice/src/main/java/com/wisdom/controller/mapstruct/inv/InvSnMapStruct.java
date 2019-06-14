package com.wisdom.controller.mapstruct.inv;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.inv.InvSn;
import com.wisdom.iwcs.domain.inv.dto.InvSnDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InvSn and its DTO InvSnDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvSnMapStruct extends EntityMapper<InvSnDTO, InvSn> {

}
