package com.wisdom.controller.mapstruct.inv;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.inv.InvTaskCondDetail;
import com.wisdom.iwcs.domain.inv.dto.InvTaskCondDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InvTaskCondDetail and its DTO InvTaskCondDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvTaskCondDetailMapStruct extends EntityMapper<InvTaskCondDetailDTO, InvTaskCondDetail> {

}
